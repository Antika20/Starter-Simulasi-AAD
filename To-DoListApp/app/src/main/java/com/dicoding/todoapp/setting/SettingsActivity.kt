package com.dicoding.todoapp.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.*
import com.dicoding.todoapp.R
import com.dicoding.todoapp.notification.NotificationWorker
import com.dicoding.todoapp.utils.NOTIFICATION_CHANNEL_ID
import java.util.concurrent.TimeUnit

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private lateinit var workManager:WorkManager
        private  var periodicWorkRequest: PeriodicWorkRequest?= null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            workManager = WorkManager.getInstance(requireActivity()) // pengganti conteks

            val prefNotification = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
            prefNotification?.setOnPreferenceChangeListener { preference, newValue ->
                val channelName = getString(R.string.notify_channel_name)

                //TODO 13 : Schedule and cancel daily reminder using WorkManager with data channelName
                when(newValue){
                    true -> PeriodicSchedulerReminder(channelName)
                    false -> cancelPeriodicTask()
                }
                true
            }
        }

        private fun PeriodicSchedulerReminder(channelName:String){
            val data = Data.Builder()
                .putString(NOTIFICATION_CHANNEL_ID,channelName)
                .build()
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

              periodicWorkRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java,1,TimeUnit.DAYS)
                .setInputData(data)
                .setConstraints(constraints)
                .build()
            workManager.enqueue(periodicWorkRequest!!)
        }

        fun cancelPeriodicTask() {
            periodicWorkRequest?.id?.let { workManager.cancelWorkById(it) }
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
}
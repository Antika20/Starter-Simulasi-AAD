package com.dicoding.courseschedule.ui.setting

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.NightMode
import java.util.*
@RequiresApi(Build.VERSION_CODES.M)
class SettingsFragment : PreferenceFragmentCompat() {

//    private lateinit var dailyReminder: DailyReminder
        private val dailyReminder by lazy { DailyReminder() }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        updateThemeCourseListPreference()

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        scheduleAndCancelNotifReminder()
    }
    private fun updateThemeCourseListPreference(){
        val listPreferenceCourse = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        listPreferenceCourse?.setOnPreferenceChangeListener { _,newValueCourse ->

            Log.d("TAG", "updateThemeCourseListPreference: $newValueCourse")
            newValueCourse?.let {
                val selectedModeCourse =
                    when((it as String).toUpperCase(Locale.ROOT)) {
                        NightMode.ON.name -> NightMode.ON
                        NightMode.OFF.name -> NightMode.OFF
                        else -> NightMode.AUTO
                    }
                updateTheme(selectedModeCourse.value)
            }
            true
        }
    }

    private fun scheduleAndCancelNotifReminder(){
    val switchPreferenceNotif = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val dailyReminder = DailyReminder()
        switchPreferenceNotif?.setOnPreferenceChangeListener { _,newValueNotif ->
            newValueNotif?.let {
                when(it as Boolean){
//                  true ->   context?.let { it1 -> dailyReminder.setDailyReminder(it1) }
//                    false -> context?.let { it2 -> dailyReminder.cancelAlarm(it2)}
                    true -> dailyReminder.setDailyReminder(requireActivity())
                    false -> dailyReminder.cancelAlarm(requireActivity())
                }
            }
            true
        }

    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}
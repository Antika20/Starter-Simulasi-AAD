package com.dicoding.habitapp.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.helper.widget.MotionEffect.AUTO
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.habitapp.R
import com.dicoding.habitapp.utils.DarkMode
import java.util.*

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
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //TODO 11 : Update theme based on value in ListPreference
            updateThemeListPreference()
        }

        private fun updateThemeListPreference(){
            val listPreferenceCourse = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            listPreferenceCourse?.setOnPreferenceChangeListener { _,newValueCourse ->

                Log.d("TAG", "updateThemeCourseListPreference: $newValueCourse")
                newValueCourse?.let {
                    val selectedModeCourse =
                        when((it as String).toUpperCase(Locale.ROOT)) {
                            DarkMode.ON.name -> DarkMode.ON
                            DarkMode.OFF.name -> DarkMode.OFF
                            else -> DarkMode.FOLLOW_SYSTEM
                        }
                    updateTheme(selectedModeCourse.value)
                }
                true
            }

        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
}
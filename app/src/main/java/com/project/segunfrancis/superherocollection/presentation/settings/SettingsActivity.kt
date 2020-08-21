package com.project.segunfrancis.superherocollection.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    private val binding: SettingsActivityBinding by lazy {
        SettingsActivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        binding.toolbar.title = resources.getString(R.string.title_activity_settings)
        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}
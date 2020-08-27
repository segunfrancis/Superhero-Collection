package com.project.segunfrancis.superherocollection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.SHARED_PREF_KEY
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by SegunFrancis
 */

@HiltAndroidApp
class Injection : Application() {

    val sharedPreferences: SharedPreferences
        get() = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    val settingsPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)
}
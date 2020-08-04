package com.project.segunfrancis.superherocollection.presesntation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class DetailActivityViewModel : ViewModel() {
    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float>
        get() = _progress
    val endProgress = MutableLiveData<Float>(0.0F)
    private var startProgress = 0F

    suspend fun getProgress() {
        Log.d("Progress", "End Progress: ${endProgress.value}")
        while (startProgress <= endProgress.value!!) {
            _progress.postValue(startProgress)
            startProgress++
            if (endProgress.value!! < 50)
                delay(50)
            else
                delay(15)
        }
    }
}
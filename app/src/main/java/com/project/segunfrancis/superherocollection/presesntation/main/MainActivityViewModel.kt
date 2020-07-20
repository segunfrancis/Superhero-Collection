package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.core.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.presesntation.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _superHeroes = MutableLiveData<Resource<SuperHeroEntity>>()
    val superHeroes: LiveData<Resource<SuperHeroEntity>>
        get() = _superHeroes

    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float>
        get() = _progress
    private var startProgress = 0F
    private val endProgress = 65F

    init {
        fetchSuperHeroes()
        viewModelScope.launch {
            getProgress()
        }
    }

    private fun fetchSuperHeroes() {
        _superHeroes.value = Resource.Loading("Loading...")
        compositeDisposable.add(
            repository.getSuperHeroesRemote().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe({ superHeroes ->
                    _superHeroes.value = Resource.Success(superHeroes, "Success")
                }, { t: Throwable? ->
                    _superHeroes.value = Resource.Error(t!!, t.localizedMessage!!)
                })
        )
    }

    private suspend fun getProgress() {
        while (startProgress < endProgress) {
            _progress.value = startProgress
            startProgress++
            delay(15)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
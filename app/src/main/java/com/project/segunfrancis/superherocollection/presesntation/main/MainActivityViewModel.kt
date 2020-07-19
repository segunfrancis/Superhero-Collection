package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.segunfrancis.core.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.framework.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _superHeroes = MutableLiveData<SuperHeroEntity>()
    val superHeroes: LiveData<SuperHeroEntity>
        get() = _superHeroes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        fetchSuperHeroes()
    }

    private fun fetchSuperHeroes() {
        compositeDisposable.add(
            repository.getSuperHeroesRemote().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ superHeroes ->
                    _superHeroes.value = superHeroes
                }, { t: Throwable? ->
                    _error.value = t?.localizedMessage
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
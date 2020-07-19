package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.segunfrancis.core.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.presesntation.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _superHeroes = MutableLiveData<Resource<SuperHeroEntity>>()
    val superHeroes: LiveData<Resource<SuperHeroEntity>>
        get() = _superHeroes

    init {
        fetchSuperHeroes()
    }

    private fun fetchSuperHeroes() {
        _superHeroes.value = Resource.Loading("Loading...")
        compositeDisposable.add(
            repository.getSuperHeroesRemote().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ superHeroes ->
                    _superHeroes.value = Resource.Success(superHeroes, "Success")
                }, { t: Throwable? ->
                    _superHeroes.value = Resource.Error(t!!, t.localizedMessage!!)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
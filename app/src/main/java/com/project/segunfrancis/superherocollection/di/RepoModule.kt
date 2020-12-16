package com.project.segunfrancis.superherocollection.di

import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun repository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}

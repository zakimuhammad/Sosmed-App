package com.zaki.sosmedapp.di

import com.zaki.sosmedapp.network.repository.SosmedRepository
import com.zaki.sosmedapp.network.repository.SosmedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideSosmedRepository(sosmedRepositoryImpl: SosmedRepositoryImpl): SosmedRepository
}
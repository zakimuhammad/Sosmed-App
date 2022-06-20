package com.zaki.sosmedapp.fakerepo

import com.zaki.sosmedapp.network.repository.SosmedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FakeRepositoryModule {
    @Binds
    abstract fun provideSosmedRepository(sosmedRepositoryImpl: FakeSosmedRepositoryImpl): SosmedRepository
}
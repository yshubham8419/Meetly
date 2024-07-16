package com.shubham.kumar.meetly.di

import com.shubham.kumar.meetly.data.firebase.auth.FirebaseAuthRepositoryImpl
import com.shubham.kumar.meetly.model.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesAuthRepository():AuthRepository {
        return FirebaseAuthRepositoryImpl()
    }
}
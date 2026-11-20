package com.example.ecommerce.ui.theme.feature.di

import com.example.ecommerce.ui.theme.feature.auth.data.datasource.local.AuthLocalDataSource
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.local.AuthLocalDataSourceImpl
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.remote.AuthRemoteDataSource
import com.example.ecommerce.ui.theme.feature.auth.data.datasource.remote.AuthRemoteDataSourceImpl
import com.example.ecommerce.ui.theme.feature.auth.data.repository.AuthRepositoryImpl
import com.example.ecommerce.ui.theme.feature.auth.domain.repository.AuthRepository
import com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.remote.home.HomeRemoteDataSource
import com.example.ecommerce.ui.theme.feature.commerce.date.date_sources.remote.home.HomeRemoteDataSourceImpl
import com.example.ecommerce.ui.theme.feature.commerce.domain.repository.HomeRepository
import com.example.ecommerce.ui.theme.feature.commerce.domain.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommerceModule {

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository


    @Binds
    abstract fun bindHomeDataSource(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl
    ): HomeRemoteDataSource


    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindAuthLocalDataSource(
        authLocalDataSourceImpl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource
}
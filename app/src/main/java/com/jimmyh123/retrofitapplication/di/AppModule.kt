package com.jimmyh123.retrofitapplication.di

import com.jimmyh123.retrofitapplication.di.common.Constants
import com.jimmyh123.retrofitapplication.data.remote.UnsplashApi
import com.jimmyh123.retrofitapplication.data.repository.UnsplashRepositoryImpl
import com.jimmyh123.retrofitapplication.domain.repository.UnsplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUnsplashRepository(api: UnsplashApi): UnsplashRepository {
        return UnsplashRepositoryImpl(api)
    }
}
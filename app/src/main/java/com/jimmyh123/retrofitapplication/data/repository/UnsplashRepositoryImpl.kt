package com.jimmyh123.retrofitapplication.data.repository

import com.jimmyh123.retrofitapplication.data.remote.UnsplashApi
import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDetailDto
import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDto
import com.jimmyh123.retrofitapplication.domain.repository.UnsplashRepository
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashApi
) : UnsplashRepository {

    override suspend fun getPhotos(): List<UnsplashDto> {
        return api.getPhotos(1,100)
    }

    override suspend fun getPhotoById(unsplashId: String): UnsplashDetailDto {
        return api.getPhotoById(unsplashId)
    }
}
package com.jimmyh123.retrofitapplication.domain.repository

import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDetailDto
import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDto

interface UnsplashRepository {

    suspend fun getPhotos(): List<UnsplashDto>

    suspend fun getPhotoById(unsplashId: String): UnsplashDetailDto
}
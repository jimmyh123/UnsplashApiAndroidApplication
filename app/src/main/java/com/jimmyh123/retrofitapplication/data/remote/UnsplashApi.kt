package com.jimmyh123.retrofitapplication.data.remote

import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDetailDto
import com.jimmyh123.retrofitapplication.data.remote.dto.UnsplashDto
import com.jimmyh123.retrofitapplication.di.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Authorization: Client-ID ${Constants.PRIVATE_KEY}")
    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashDto>

    @Headers("Authorization: Client-ID ${Constants.PRIVATE_KEY}")
    @GET("/photos/{photoId}")
    suspend fun getPhotoById(@Path("photoId") photoId: String): UnsplashDetailDto
}
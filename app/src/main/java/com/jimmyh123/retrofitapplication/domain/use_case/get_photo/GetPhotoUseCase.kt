package com.jimmyh123.retrofitapplication.domain.use_case.get_photo

import com.jimmyh123.retrofitapplication.di.common.Resource
import com.jimmyh123.retrofitapplication.data.remote.dto.toUnsplashDetail
import com.jimmyh123.retrofitapplication.domain.model.UnsplashDetail
import com.jimmyh123.retrofitapplication.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val repository: UnsplashRepository
){
    // overriding this so we can call GetPhotosUseCase like a function
    operator fun invoke(photoId: String): Flow<Resource<UnsplashDetail>> = flow {
        try {
            emit(Resource.Loading<UnsplashDetail>())
            val photo = repository.getPhotoById(photoId).toUnsplashDetail()
            emit(Resource.Success<UnsplashDetail>(photo))
        } catch(e: HttpException) {
            emit(Resource.Error<UnsplashDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<UnsplashDetail>("Couldn't reach server, check your internet connection"))
        }
    }
}
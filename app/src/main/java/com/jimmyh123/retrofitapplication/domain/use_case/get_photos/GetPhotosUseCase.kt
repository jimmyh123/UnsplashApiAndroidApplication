package com.jimmyh123.retrofitapplication.domain.use_case.get_photos

import com.jimmyh123.retrofitapplication.di.common.Resource
import com.jimmyh123.retrofitapplication.data.remote.dto.toUnsplash
import com.jimmyh123.retrofitapplication.domain.model.Unsplash
import com.jimmyh123.retrofitapplication.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: UnsplashRepository
){
    // overriding this so we can call GetPhotosUseCase like a function
    operator fun invoke(): Flow<Resource<List<Unsplash>>> = flow {
        try {
            emit(Resource.Loading<List<Unsplash>>())
            val photos = repository.getPhotos().map { it.toUnsplash() }
            emit(Resource.Success<List<Unsplash>>(photos))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Unsplash>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Unsplash>>("Couldn't reach server, check your internet connection"))
        }
    }
}
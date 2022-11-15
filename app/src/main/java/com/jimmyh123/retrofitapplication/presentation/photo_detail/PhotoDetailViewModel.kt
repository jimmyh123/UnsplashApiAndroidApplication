package com.jimmyh123.retrofitapplication.presentation.photo_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimmyh123.retrofitapplication.di.common.Constants
import com.jimmyh123.retrofitapplication.di.common.Resource
import com.jimmyh123.retrofitapplication.domain.use_case.get_photo.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(PhotoDetailState())
    val state: State<PhotoDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_UNSPLASH_ID)?.let { unsplashId ->
            getPhoto(unsplashId)
        }
    }

    private fun getPhoto(unsplashId: String) {
        getPhotoUseCase(unsplashId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = PhotoDetailState(photo = result.data)
                }
                is Resource.Error -> {
                    _state.value = PhotoDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PhotoDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
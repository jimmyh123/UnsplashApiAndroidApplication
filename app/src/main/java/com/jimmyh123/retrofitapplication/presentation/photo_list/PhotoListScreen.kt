package com.jimmyh123.retrofitapplication.presentation.photo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jimmyh123.retrofitapplication.presentation.Screen
import com.jimmyh123.retrofitapplication.presentation.components.PhotoListItem

@Composable
fun PhotoListScreen(
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            columns = GridCells.Adaptive(minSize = 128.dp),
        ) {
            items(state.photos.size){ photo ->
                val currentPhotoId = state.photos[photo].id
                PhotoListItem(
                    photo = state.photos[photo],
                    onItemClick = { navController.navigate(Screen.PhotoDetailScreen.route + "/${currentPhotoId}")})
            }
        }

        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
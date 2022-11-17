package com.jimmyh123.retrofitapplication.presentation.photo_list

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jimmyh123.retrofitapplication.presentation.bottom_nav.BottomTabScreen
//import com.jimmyh123.retrofitapplication.presentation.Screen
import com.jimmyh123.retrofitapplication.presentation.components.PhotoListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoListScreen(
    navController: NavController,
    viewModel: PhotoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){

        val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_PORTRAIT) {
            StaggeredGridCells.Fixed(2)
        } else StaggeredGridCells.Fixed(3)

        LazyVerticalStaggeredGrid(
            columns = cellConfiguration,
//            columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(state.photos.size){ photo ->
                val currentPhotoId = state.photos[photo].id
                PhotoListItem(
                    photo = state.photos[photo],
                    onItemClick = { navController.navigate(BottomTabScreen.PhotoDetailScreen.route + "/${currentPhotoId}")})
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
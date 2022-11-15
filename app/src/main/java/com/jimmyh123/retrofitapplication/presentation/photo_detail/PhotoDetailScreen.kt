package com.jimmyh123.retrofitapplication.presentation.photo_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.jimmyh123.retrofitapplication.presentation.components.DisplayImageDetail
import com.jimmyh123.retrofitapplication.presentation.components.PhotoTag

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){

        state.photo?.let { photo ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ){
                item {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {

                    DisplayImageDetail(
                        likes = photo.likes,
                        username = photo.user?.username,
                        links = photo.links,
                        profileImage = photo.user?.profileImage,
                        dateCreated = photo.createdAt,
                        dateUpdated = photo.updatedAt,
                        description = photo.description,
                        urls = photo.urls
                    )

                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.h6
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            photo.tags.forEach { tag ->
                                PhotoTag(tag = tag.title)
                            }
                        }

//                    }
                }
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
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
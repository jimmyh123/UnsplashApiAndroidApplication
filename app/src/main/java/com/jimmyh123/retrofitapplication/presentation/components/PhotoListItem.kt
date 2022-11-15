package com.jimmyh123.retrofitapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jimmyh123.retrofitapplication.domain.model.Unsplash

@Composable
fun PhotoListItem(
    photo: Unsplash,
    onItemClick: (Unsplash) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick(photo) }
            .fillMaxWidth()
            .padding(0.dp)
    ){
        photo.urls?.let { DisplayImage(it.regular) }
    }

}
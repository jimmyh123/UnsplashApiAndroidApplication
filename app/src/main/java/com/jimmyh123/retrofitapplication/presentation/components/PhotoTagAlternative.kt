package com.jimmyh123.retrofitapplication.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.Tag

@Composable
fun PhotoTagAlternative(
    tag: Tag,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tag.title,
            style = MaterialTheme.typography.h4
        )
    }
    
}
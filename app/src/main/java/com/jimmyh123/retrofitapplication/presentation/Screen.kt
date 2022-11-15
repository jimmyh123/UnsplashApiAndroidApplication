package com.jimmyh123.retrofitapplication.presentation

sealed class Screen(val route: String){
    object PhotoListScreen: Screen("photo_list_screen")
    object PhotoDetailScreen: Screen("photo_detail_screen")
}

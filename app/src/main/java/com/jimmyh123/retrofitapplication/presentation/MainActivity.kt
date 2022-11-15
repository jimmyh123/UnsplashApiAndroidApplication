package com.jimmyh123.retrofitapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jimmyh123.retrofitapplication.presentation.photo_detail.PhotoDetailScreen
import com.jimmyh123.retrofitapplication.presentation.photo_list.PhotoListScreen
import com.jimmyh123.retrofitapplication.presentation.ui.theme.RetrofitApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PhotoListScreen.route
                    ){
                        composable(route = Screen.PhotoListScreen.route){
                            PhotoListScreen(navController)
                        }
                        composable(route = Screen.PhotoDetailScreen.route + "/{photoId}"){
                            PhotoDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

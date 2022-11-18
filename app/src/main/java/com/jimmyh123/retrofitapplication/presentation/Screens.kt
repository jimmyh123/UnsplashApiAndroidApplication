package com.jimmyh123.retrofitapplication.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.jimmyh123.retrofitapplication.R
import com.jimmyh123.retrofitapplication.presentation.bottom_nav.TabScreenOne
import com.jimmyh123.retrofitapplication.presentation.bottom_nav.TabScreenThree
import com.jimmyh123.retrofitapplication.presentation.bottom_nav.TabScreenTwo

//sealed class Screen(val route: String){
//    object PhotoListScreen: Screen("photo_list_screen")
//    object PhotoDetailScreen: Screen("photo_detail_screen")
//}

// define bottom bar navigation
sealed class BottomTabScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val badgeCount: Int = 0
) {
    object Home : BottomTabScreen("Home", R.string.section_home, Icons.Default.Home,0)
    object Search : BottomTabScreen("Search", R.string.section_search, Icons.Default.Search, 0)
    object Favourites : BottomTabScreen("Favourites", R.string.section_favourites, Icons.Default.Favorite,12)
    object PhotoListScreen: BottomTabScreen("PhotoListScreen", R.string.section_photo_list, Icons.Default.Home)
    object PhotoDetailScreen: BottomTabScreen("PhotoDetailScreen", R.string.section_photo_detail, Icons.Default.Home)
}

// define tab navigation
typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(val icon: ImageVector, var title: String, var screen: ComposableFun) {
    object TabScreenOne : TabItem(Icons.Default.Email, "Tab One", { TabScreenOne() })
    object TabScreenTwo : TabItem(Icons.Default.Edit, "Tab Two", { TabScreenTwo() })
    object TabScreenThree : TabItem(Icons.Default.Call, "Tab Three", { TabScreenThree() })
}
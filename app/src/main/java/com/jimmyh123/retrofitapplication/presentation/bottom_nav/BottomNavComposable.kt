package com.jimmyh123.retrofitapplication.presentation.bottom_nav

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
//import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.jimmyh123.retrofitapplication.R
import com.jimmyh123.retrofitapplication.presentation.BottomTabScreen
import com.jimmyh123.retrofitapplication.presentation.TabItem
import com.jimmyh123.retrofitapplication.presentation.photo_detail.PhotoDetailScreen
import com.jimmyh123.retrofitapplication.presentation.photo_list.PhotoListScreen
import kotlinx.coroutines.launch


@Composable
fun TopBarComposable(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: String,
) {
    TopAppBar(
        title = { Text(currentScreen) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationComposable(
    modifier: Modifier = Modifier,
//    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
//    val uiState by mainViewModel.uiState.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    val currentScreenIndex = topBarRoutes.indexOf(
        navBackStackEntry?.destination?.route ?: BottomTabScreen.PhotoListScreen.route
    )
    val currentScreen = topBarRoutes[currentScreenIndex]

    // tabs
    val tabs = listOf(TabItem.TabScreenOne, TabItem.TabScreenTwo, TabItem.TabScreenThree)
    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            TopBarComposable(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        },
        bottomBar = {
            BottomNavigation (
                modifier = modifier,
                elevation = 2.dp
            ){
                val currentDestination = navBackStackEntry?.destination
                bottomBarItems.forEach{ screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                if(screen.badgeCount > 0) {
                                    BadgedBox(
                                        badge = {
                                            Text(screen.badgeCount.toString())
                                        }
                                    ) {
                                        Icon(imageVector = screen.icon, contentDescription = screen.route)
                                    }
                                } else {
                                    Icon(imageVector = screen.icon, contentDescription = screen.route)
                                }
                                if (selected){
                                    Text(
                                        text = screen.route,
                                        textAlign = TextAlign.Center,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        },
                        selected = selected,
                        selectedContentColor = Color.Yellow,
                        unselectedContentColor = Color.Gray,
                        onClick = {
                            navController.navigate(screen.route) {
                                // pop up to start destination of the graph
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                // avoid multiple copies when reselecting same item
                                launchSingleTop = true
                                // restore state when reselecting previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            val currentDestination = navBackStackEntry?.destination
            if (currentDestination?.route==BottomTabScreen.PhotoListScreen.route){
                AddTabScreenFab(onClick = { navController.navigate(ScreenNavigationLocations.FabScreenOne.name) })
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomTabScreen.PhotoListScreen.route,
            modifier = modifier.padding(innerPadding)
        ){

            // bottom bar item 1 -
            composable(BottomTabScreen.PhotoListScreen.route) {
                PhotoListScreen(navController)
            }


            composable(BottomTabScreen.PhotoDetailScreen.route + "/{photoId}") {
                PhotoDetailScreen()
            }

            // bottom bar item 2
            composable(BottomTabScreen.Search.route) {

            }

            // bottom bar item 3
            composable(BottomTabScreen.Favourites.route) {
                Column() {
                    Tabs(tabs = tabs, pagerState = pagerState)
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
            }

            // fab navigation 1
            composable(ScreenNavigationLocations.FabScreenOne.name) {
                FabScreenOne()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

private fun navigateBackToStart(
    navController: NavHostController
){
    navController.popBackStack(BottomTabScreen.Home.route,inclusive = false)
}

val bottomBarItems = listOf(
    BottomTabScreen.PhotoListScreen,
    BottomTabScreen.Search,
    BottomTabScreen.Favourites,
)

val topBarRoutes = listOf(
    BottomTabScreen.PhotoListScreen.route,
    BottomTabScreen.PhotoDetailScreen.route + "/{photoId}",
    BottomTabScreen.Search.route,
    BottomTabScreen.Favourites.route,
)

enum class ScreenNavigationLocations(){
    FabScreenOne,
    PhotoListScreen,
    PhotoDetailScreen,
    Home,
    Search,
    Favourites
}


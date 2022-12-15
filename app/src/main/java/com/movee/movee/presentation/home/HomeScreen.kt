package com.movee.movee.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.movee.movee.commons.composables.CustomTopAppBar
import com.movee.movee.presentation.feature_feed.composables.FeedScreen
import com.movee.movee.presentation.theme.SubtitleColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainBottomNavigationView(navController)
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationInfo.HomeBottomItem.route,
            modifier = Modifier.padding(
                PaddingValues(0.dp, 0.dp, 0.dp, it.calculateBottomPadding()))
        ) {
            composable(route = BottomNavigationInfo.HomeBottomItem.route) {
                FeedScreen()
            }
            composable(route = BottomNavigationInfo.SearchBottomItem.route) {
                FeedScreen()
            }
            composable(route = BottomNavigationInfo.FavoriteBottomItem.route) {
                FeedScreen()
            }
        }
    }
}

@Composable
fun MainBottomNavigationView(navController: NavController) {
    val navStack by navController.currentBackStackEntryAsState()
    val currentRoute = navStack?.destination?.route

    val items = listOf(
        BottomNavigationInfo.HomeBottomItem,
        BottomNavigationInfo.SearchBottomItem,
        BottomNavigationInfo.FavoriteBottomItem,
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = Color.White
    ) {
        items.forEach {
            BottomNavigationItem(
                selected = it.route == currentRoute, 
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = null,
                        tint = if (it.route == currentRoute) Color.White else SubtitleColor
                    )
                },
            )
        }
    }
}








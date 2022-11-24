package com.movee.movee.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationInfo(val route: String, val icon: ImageVector, val label: String) {
    object HomeBottomItem : BottomNavigationInfo("home", Icons.Default.Home, "Home")
    object SearchBottomItem : BottomNavigationInfo("search", Icons.Default.Search, "Search")
    object FavoriteBottomItem : BottomNavigationInfo("favorite", Icons.Default.Favorite, "Favorite")
}
package com.movee.movee.presentation.feature_feed.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.movee.movee.data.api.Resource
import com.movee.movee.presentation.factory.ItemDataFactory
import com.movee.movee.presentation.feature_feed.FeedViewModel

@Composable
fun FeedScreen() {
    val viewModel = hiltViewModel<FeedViewModel>()
    val feedItems = viewModel.feedItems.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        when (feedItems.value) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is Resource.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    feedItems.value.data?.let {
                        items(it) { item ->
                            ItemDataFactory.Create(feedItem = item)
                        }
                    }
                }
            }
            is Resource.Error -> Text("Error", color = Color.White)
            else -> CircularProgressIndicator(color = Color.White)
        }
    }
}
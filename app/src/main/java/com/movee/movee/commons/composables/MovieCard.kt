package com.movee.movee.commons.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.movee.movee.commons.constants.MovieCardSizeConstants
import com.movee.movee.commons.extensions.getImageUrl

@Preview
@Composable
fun MovieCard(
    url : String = "",
    size : MovieCardSizeConstants = MovieCardSizeConstants.MEDIUM
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getImageUrl(url))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .width(size.width.dp)
                .height(size.height.dp)
        )
    }
}
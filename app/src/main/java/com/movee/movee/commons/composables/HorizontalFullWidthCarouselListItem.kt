package com.movee.movee.commons.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.movee.movee.commons.constants.ApiConstants
import com.movee.movee.commons.extensions.getImageUrl
import com.movee.movee.domain.entities.MoviesResponse
import com.movee.movee.presentation.theme.AccentColor
import com.movee.movee.presentation.theme.GreenColor
import kotlin.math.roundToInt

@Composable
fun HorizontalFullWidthCarouselListItem(
    item: MoviesResponse.Result,
) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, MaterialTheme.colors.primary),
        startY = sizeImage.height.toFloat()/3,  // 1/3
        endY = sizeImage.height.toFloat()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getImageUrl(item.backdropPath, ApiConstants.ImageWidth.WIDTH_780))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .onGloballyPositioned {
                    sizeImage = it.size
                }
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(gradient)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            BigTitle(
                item.originalTitle
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${(item.vote * 10).roundToInt()} %",
                    style = TextStyle(
                        color = GreenColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    "•",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 24.sp
                    )
                )
                Text(
                    item.releaseDate.split("-")[0] + "-" + item.releaseDate.split("-")[1],
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }
            RoundedButton(
                color = AccentColor,
                cornerRadius = 5.dp,
                text = "Play",
                icon = Icons.Default.PlayArrow
            )
        }
    }
}
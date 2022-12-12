package com.movee.movee.commons.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.movee.movee.presentation.theme.TextColor

@Composable
fun BigTitle(
    title: String = "The flying beast",
    color: Color = TextColor
) {
    Text(
        title,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    )
}
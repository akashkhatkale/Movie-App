package com.movee.movee.commons.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movee.movee.presentation.theme.AccentColor

@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = AccentColor
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun TopBarPreview() {
    CustomTopAppBar(title = "Movee")
}
package com.movee.movee.commons.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    color: Color,
    cornerRadius: Dp,
    icon: ImageVector? = null,
    text: String,
    onClick: (() -> Unit )? = null
) {
    Button(
        onClick = onClick ?: {},
        colors = ButtonDefaults.buttonColors(backgroundColor = color,),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        if (icon != null) {
            Icon(
                icon,
                null
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(
            text
        )
    }
}
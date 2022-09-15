package com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.util.advancedShadow
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.selectedColor

@Composable
fun ShadowButton(
    buttonShape: Shape,
    buttonModifier: Modifier,
    buttonBackgroundColor: Color = Color.White,
    onClick: () -> Unit,
    text: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = buttonModifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonBackgroundColor
        ),
        shape = buttonShape
    ) {
        text()
    }
}
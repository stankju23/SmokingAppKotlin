package com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode


val colorPrimary = Color(0xFFF1EEFC)
val colorPrimaryDark = Color(0xFF558D96)
val colorAccent = Color(0xFFFFFFFF)


val mainColor = Color(0xFFFFFFFF)
val selectedColor = Color(0xFF558d96)
val unselectedBarColor = Color(0xFFe8e8e8)
val unselectedColor = Color(0xFF7F7766)
val darker_main = Color(0xFFFFFFFF)
val light_grey = Color(0xFFd3d3d3)
val complete = Color(0xFF54FF53)
val item_grey = Color(0xFFEFEFEF)

val mainBackground = Brush.verticalGradient(
    colors = listOf(
        colorPrimary,
        colorAccent
    ),
    startY = 0.7f,
    tileMode = TileMode.Clamp
)
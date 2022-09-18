package com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stanislavcavajda.bakalarkasmokingapp.R

val Nunito = FontFamily(
    Font(R.font.nunito_sans_regular, weight = FontWeight.Normal),
    Font(R.font.nunito_sans_light, weight = FontWeight.Light),
    Font(R.font.nunito_sans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.nunito_sans_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 200.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

//     Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
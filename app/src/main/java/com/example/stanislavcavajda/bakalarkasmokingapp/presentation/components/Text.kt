package com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.Nunito

@Composable
fun Title(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 0.dp),
        text = text,
        fontSize = 20.sp,
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontFamily = Nunito
    )
}

@Composable
fun Description(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 20.dp),
        text = text,
        fontSize = 15.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        fontFamily = Nunito
    )
}
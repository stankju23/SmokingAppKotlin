package com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 60.dp),
        text = text,
        fontSize = 20.sp,
        color = Color.LightGray,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Description(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 15.dp),
        text = text,
        fontSize = 15.sp,
        color = Color.LightGray,
        textAlign = TextAlign.Center
    )
}
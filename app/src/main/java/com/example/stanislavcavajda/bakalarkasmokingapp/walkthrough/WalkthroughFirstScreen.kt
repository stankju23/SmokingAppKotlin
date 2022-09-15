package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.Description
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.ShadowButton
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.Title
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.util.advancedShadow
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.util.outerShadow
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.SmokingAppTheme
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.selectedColor
import com.google.android.material.appbar.CollapsingToolbarLayout.TitleCollapseMode


@Composable
fun BaseScreen(content: @Composable () -> Unit) {
    SmokingAppTheme() {
        content()
    }
}

@DarkLightModePreview
@Composable
fun WalkthroughFirst() {
    val context = LocalContext.current
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.welcome_image),
                contentDescription = "",
                modifier = Modifier
                    .width(150.dp)
                    .height(120.dp)
            )
            Title(text = context.getString(R.string.welcome_title))
            Description(text = context.getString(R.string.welcome_desc))
            Spacer(modifier = Modifier.weight(1f))
            ShadowButton(
                buttonModifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 20.dp
                ), onClick = {
                },
            buttonShape = RoundedCornerShape(percent = 50)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 50.dp,
                        end = 50.dp,
                        top = 0.dp,
                        bottom = 0.dp),
                    text = context.getString(R.string.next_button),
                    color = Color.LightGray,
                    fontSize = 15.sp

                )
            }
        }
    }
}


@Preview(
    name = "dark mode",
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Preview(
    name = "light mode",
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true
)
annotation class DarkLightModePreview
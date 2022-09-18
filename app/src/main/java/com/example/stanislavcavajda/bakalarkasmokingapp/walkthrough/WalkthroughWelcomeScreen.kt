package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.Description
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.ShadowButton
import com.example.stanislavcavajda.bakalarkasmokingapp.presentation.components.Title
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.Nunito
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.SmokingAppTheme
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.light_grey
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.mainBackground
import org.koin.androidx.compose.get

@Composable
fun BaseScreen(content: @Composable () -> Unit) {
    SmokingAppTheme() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = mainBackground)) {
            content()
        }
    }
}

@Composable
fun WalkthroughWelcome(
    walkthroughWelcomeViewModel: WalkthroughWelcomeViewModel
) {
    val context = LocalContext.current
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.drawable.welcome_image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(all = 60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Title(text = context.getString(R.string.welcome_title))
            Description(text = context.getString(R.string.welcome_desc))
            ShadowButton(
                buttonModifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 20.dp
                ), onClick = {
                    walkthroughWelcomeViewModel.goToCigarettesPerDay()
                },
            buttonShape = RoundedCornerShape(percent = 50),
                border = BorderStroke(
                    width = 0.3.dp,
                    color = light_grey
                )
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 50.dp,
                        end = 50.dp,
                        top = 0.dp,
                        bottom = 0.dp),
                    text = context.getString(R.string.next_button),
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontFamily = Nunito

                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewWelcomeScreen() {
    WalkthroughWelcome(walkthroughWelcomeViewModel = WalkthroughWelcomeViewModel())
}
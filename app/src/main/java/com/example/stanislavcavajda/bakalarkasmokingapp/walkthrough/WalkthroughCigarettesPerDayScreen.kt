@file:OptIn(ExperimentalPagerApi::class)

package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.light_grey
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun WalkthroughCigarettesPerDayScreen() {
    val context = LocalContext.current
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.drawable.cigarettes_per_day_image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(all = 60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Title(text = context.getString(R.string.cigarettes_smoked_every_day))
            Description(text = context.getString(R.string.cigarettes_smoked_desc))

            val pagerState = rememberPagerState()

            rememberCoroutineScope().launch {
                pagerState.scrollToPage(10)
            }

            Box(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.Transparent)
            ) {
                HorizontalPager(
                    count = 60,
                    state = pagerState,
                    contentPadding = PaddingValues(start = 130.dp, end = 130.dp),
                    modifier = Modifier.align(Center)
                ) { page ->
                    val offset = 10
                        Text(
                            text = "${page + offset}",
                            fontSize = 25.sp,
                            fontFamily = Nunito,
                            color = Color.Gray
                        )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Transparent)
                , verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.weight(1f))
                    Divider(color = Color.Gray, modifier = Modifier
                        .height(50.dp)
                        .width(1.dp))
                    Spacer(modifier = Modifier.width(45.dp))
                    Divider(color = Color.Gray, modifier = Modifier
                        .height(50.dp)
                        .width(1.dp))
                    Spacer(modifier = Modifier.weight(1f))

                }
                

            }
            ShadowButton(
                buttonModifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 20.dp
                ), onClick = {
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
                        bottom = 0.dp
                    ),
                    text = context.getString(R.string.next_button),
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontFamily = Nunito

                )
            }
        }
    }
}
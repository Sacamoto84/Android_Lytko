package com.example.lytko.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lytko.R
import com.example.lytko.navigation.Destinations
import com.example.lytko.ui.theme.Blue800
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.siddroid.holi.colors.MaterialColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenAfterLogin(navController: NavHostController) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                if (pagerState.currentPage != 2)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), contentAlignment = Alignment.Center

                    )
                    {
                        OutlinedButton(
                            onClick = { navController.navigate(Destinations.Screen_Dashboard) },
                            modifier = Modifier
                                .padding(end = 4.dp, bottom = 8.dp)
                                .fillMaxWidth(),

                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),

                            shape = RoundedCornerShape(30),
                            border = BorderStroke((1.5).dp, MaterialColor.BLUE_800)
                        )
                        {
                            Text(
                                textAlign = TextAlign.Start,
                                text = "Пропустить",
                                modifier = Modifier.padding(start = 0.dp, bottom = 7.dp),
                                fontFamily = FontFamily(
                                    Font(R.font.notosans_medium)
                                ),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), contentAlignment = Alignment.Center
                )
                {
                    Button(
                        onClick = {
                            if (pagerState.currentPage <= 1)
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)

                                }
                            else
                                navController.navigate(Destinations.Screen_Dashboard)

                        },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialColor.BLUE_800,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(30),

                        modifier = Modifier
                            .padding(start = 4.dp, bottom = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = if (pagerState.currentPage != 2) "Далее" else "Начать работу",
                            modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                            //.background(Color.Red)
                            ,
                            fontFamily = FontFamily(
                                Font(R.font.notosans_medium)
                            ),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    )
    { PaddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = PaddingValues.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            HorizontalPager(
                count = 3,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = 0.dp),
                modifier = Modifier
                    .weight(1f),
            ) { page ->

                when (page) {
                    0 -> Screen_afterLogin_page0()
                    1 -> Screen_afterLogin_page1()
                    2 -> Screen_afterLogin_page2()
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .height(30.dp)
                    //.background(Color.Red)
                    .align(Alignment.CenterHorizontally),
                activeColor = MaterialColor.BLUE_500,
                inactiveColor = MaterialColor.GREY_800,
                spacing = 20.dp,
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp
            )
        }
    }
}

@Composable
private fun Screen_afterLogin_page0() {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    )
    {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(), contentAlignment = Alignment.CenterEnd
        )
        {
            Icon(
                modifier = Modifier
                    .padding(start = 0.dp, end = 8.dp)
                    .size(36.dp),
                painter = painterResource(id = R.drawable.right),
                contentDescription = "",
                tint = Color.White
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        //.background(Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Добро пожаловать\nв Умный дом!",
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 40.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(R.font.notosans_medium)
            ),
            //,letterSpacing = 10.sp
            lineHeight = 30.sp
        )

        Image(
            painterResource(id = R.drawable.afterlogin0),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 125.dp)
                .height(135.dp)
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

            Text(

                text = "Управляйте избранными устройствами\nво всех комнатах с главного экрана:\nвключение, температура, корректировка",
                color = MaterialColor.BLUE_200,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 6.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.notosans_regular)
                ),
                //,letterSpacing = 10.sp
                //lineHeight = 20.sp,
            )
        }
    }
}

@Composable
private fun Screen_afterLogin_page1() {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    )
    {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(), contentAlignment = Alignment.CenterEnd
        )
        {
            Icon(
                modifier = Modifier
                    .padding(start = 0.dp, end = 8.dp)
                    .size(36.dp),
                painter = painterResource(id = R.drawable.right),
                contentDescription = "",
                tint = Color.White
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    )
    {

        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
        )
        {
            Icon(
                modifier = Modifier
                    .padding(start = 0.dp, end = 8.dp)
                    .size(36.dp),
                painter = painterResource(id = R.drawable.left),
                contentDescription = "",
                tint = Color.White
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
//.background(Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Добавление Дома",
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 40.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(R.font.notosans_medium)
            ),
            //,letterSpacing = 10.sp
            lineHeight = 30.sp
        )

        Image(
            painterResource(id = R.drawable.afterlogin1),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 30.dp)
                .height(450.dp)
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

            Text(

                text = "Квартира, загородный дом, дача:\nуправляйте всеми Умными домами\n с одного аккаунта",
                color = MaterialColor.BLUE_200,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 6.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.notosans_regular)
                ),
                //,letterSpacing = 10.sp
                //lineHeight = 20.sp,
            )

        }
    }
}

@Composable
private fun Screen_afterLogin_page2() {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    )
    {

        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(), contentAlignment = Alignment.CenterStart
        )
        {
            Icon(
                modifier = Modifier
                    .padding(start = 0.dp, end = 8.dp)
                    .size(36.dp),
                painter = painterResource(id = R.drawable.left),
                contentDescription = "",
                tint = Color.White
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            "Сценарии работы устройств",
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 38.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(R.font.notosans_medium)
            ),
            lineHeight = 30.sp
        )

        Image(
            painterResource(id = R.drawable.afterlogin2),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 23.dp)
                .height(463.dp)
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

            Text(

                text = "Расписание работы устройств\nв зависимости от погоды, связанная работа\nс другими устройствами в доме",
                color = MaterialColor.BLUE_200,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 6.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.notosans_regular)
                ),
            )
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}
package com.example.lytko.screens

import android.content.ClipData.Item
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lytko.R
import com.example.lytko.screens.config.device0
import com.example.lytko.ui.theme.colorBackgroundDark
import com.example.lytko.ui.theme.colorBackgroundLight
import com.siddroid.holi.colors.MaterialColor


//MARK: Настройка конкретного устройсва
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen_Config_Current(navController: NavHostController) {
    Scaffold(

        topBar = { TopBar(navController) },
        containerColor = colorBackgroundDark //Color(0xFF232834)

    )
    { paddingValues ->


        Column(
            Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            device0.ScreenConfigList.forEach()
            { item ->


                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .height(56.dp)
                        .fillMaxWidth()
                    //.background(
                    //    Color.Red
                    //)
                )
                {

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                        //.background(Color.Blue)
                        ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(26.dp),
                            //.background(Color.Green),
                            painter = painterResource(id = item.r),
                            contentDescription = "",
                            tint = Color(0xFF818288)
                        )

                        Text(
                            text = item.label,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.notosans_medium)),
                            style = TextStyle(
                                platformStyle = PlatformTextStyle(includeFontPadding = false)
                            ),
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                            //.background(Color.Magenta)
                            , contentAlignment = Alignment.CenterEnd
                        )
                        {

                            Row() {
                                Text(
                                    text = item.lastLabel,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.notosans_medium)),
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                                    ),
                                    modifier = Modifier.padding(end = 10.dp),
                                    color = if (item.lastLabelLight) Color.White else Color(0xFF818288)
                                )

                                Icon(
                                    modifier = Modifier
                                        .padding(start = 0.dp)
                                        .size(26.dp),
                                    //.background(Color.Green),
                                    painter = painterResource(id = item.rLast),
                                    contentDescription = "",
                                    tint = Color(0xFF818288)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navController: NavHostController) {


    TopAppBar(

        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 0.dp)

                        .size(32.dp),
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "",
                    tint = MaterialColor.WHITE
                )
            }
        },

        actions = {
/*
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 6.dp)) {
                Icon(
                    modifier = Modifier
                        .padding(start = 0.dp)

                        .size(32.dp),
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "",
                    tint = MaterialColor.WHITE
                )
            }

*/
        },
        title = {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                //.background(Color.Green)
            )
            {

                Text(
                    textAlign = TextAlign.Start,
                    text = "Настройки",
                    modifier = Modifier.padding(start = 0.dp, bottom = 8.dp, end = 64.dp)
                    //.background(Color.Red)
                    ,
                    fontFamily = FontFamily(
                        Font(R.font.notosans_medium)
                    ),
                    fontSize = 28.sp, fontWeight = FontWeight.Bold
                )
            }


        },
        //modifier = Modifier.height(100.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorBackgroundLight)//Color(0xFF2C3041))
    )


}











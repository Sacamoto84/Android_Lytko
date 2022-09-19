package com.example.lytko

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lytko.navigation.Destinations
import com.example.lytko.screens.ScreenAfterLogin
import com.example.lytko.screens.ScreenDashboard
import com.example.lytko.screens.Screen_Config_Current
import com.example.lytko.screens.config.init_demo_config
import com.example.lytko.screens.screenLogin
import com.example.lytko.ui.theme.Blue400
import com.example.lytko.ui.theme.DarkGray1000
import com.example.lytko.ui.theme.Grey900
import com.example.lytko.ui.theme.LytkoTheme
import com.siddroid.holi.colors.MaterialColor
import libs.KeepScreenOn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Инициализайия демо списка дял настроек
        init_demo_config()

        setContent {
            KeepScreenOn()

            var navController = rememberNavController()

            LytkoTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, Destinations.Screen_Dashboard)
                    {

                        composable(Destinations.Screen_Dashboard) {
                            ScreenDashboard(navController)
                        }

                        composable(Destinations.Login) {
                            screenLogin(navController)
                        }

                        composable(Destinations.Onboding) {
                            ScreenAfterLogin(navController)
                        }

                        composable(Destinations.Main) {
                            Greeting("Android")
                        }

                        composable(Destinations.Config_Current) {
                            Screen_Config_Current(navController)
                        }

                    }
                }
            }
        }
    }
}

@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState",
    "UnrememberedMutableState"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {

    val strlistState = mutableStateListOf<String>("Лампочка", "Бойлер", "Теплый пол")
    Scaffold(
        bottomBar = {

            Button(
                contentPadding =  PaddingValues(start = 0.dp, top = 0.dp, bottom = 0.dp, end = 0.dp),
                shape = RoundedCornerShape(25),
                modifier = Modifier

                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                    .fillMaxWidth()
                    .height(56.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialColor.BLUE_800,
                    contentColor = Color.White
                ), onClick = {
                    strlistState.add(strlistState[strlistState.lastIndex - 2])
                    println("strlistState.value.add(Ванная)")
                }) {

                Text(
                    text = "Подключить", fontSize = 22.sp, fontFamily = FontFamily(
                        Font(R.font.notosans_medium)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(start = 0.dp)
                                //.background(Color.Green)
                                .size(36.dp),
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "",
                            tint = Blue400
                        )

                        Text(
                            textAlign = TextAlign.Start,
                            text = "Обновление",
                            modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                            //.background(Color.Red)
                            ,
                            fontFamily = FontFamily(
                                Font(R.font.notosans_medium)
                            ),
                            fontSize = 28.sp, fontWeight = FontWeight(2)
                        )
                    }

                }, //modifier = Modifier.height(100.dp),

                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Grey900)
            )


        }


    ) { paddingValue ->


        Column(
            Modifier
                .padding(top = paddingValue.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Box(contentAlignment = Alignment.CenterStart) {
                Text(
                    text = "КУХНЯ",
                    fontFamily = FontFamily(
                        Font(R.font.notosans_regular)
                    ),
                    fontSize = 28.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 6.dp)
                )
            }



            repeat(strlistState.size) { i ->

                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkGray1000),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                ) {

                    Box(contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = strlistState[i % strlistState.size],
                            fontFamily = FontFamily(
                                Font(R.font.notosans_regular)
                            ),
                            fontSize = 28.sp,
                            modifier = Modifier.padding(start = 16.dp, top = 6.dp)
                        )
                    }


                }
            }


        }


    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LytkoTheme {
        Greeting("Android")
    }
}



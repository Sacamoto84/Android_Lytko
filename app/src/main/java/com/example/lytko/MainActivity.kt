package com.example.lytko

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lytko.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LytkoTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {

    val strlist = mutableListOf<String>("Лампочка", "Бойлер", "Теплый пол")

    val strlistState = remember { mutableStateOf(strlist) }

    Scaffold(


        bottomBar = {


            Button(
                shape = RoundedCornerShape(30),
                modifier = Modifier

                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                    .fillMaxWidth()
                    .height(64.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue800,
                    contentColor = Color.White
                ), onClick = {


                    strlistState.value.add("Ванная")
                    println("strlistState.value.add(Ванная)")

                }) {

                Text(
                    text = "Подключить", fontSize = 22.sp, fontFamily = FontFamily(
                        Font(R.font.notosans_medium)
                    ), modifier = Modifier.padding(bottom = 8.dp)
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



            repeat(strlistState.value.size) { i ->

                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkGray1000),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                ) {

                    Box(contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = strlistState.value[i % strlistState.value.size],
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
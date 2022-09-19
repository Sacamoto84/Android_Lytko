package com.example.lytko.screens


import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lytko.R
import com.example.lytko.navigation.Destinations
import com.example.lytko.ui.theme.DarkGray1000
import com.example.lytko.ui.theme.LytkoTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.siddroid.holi.colors.MaterialColor
import kotlinx.coroutines.launch


var login by mutableStateOf(TextFieldValue(""))


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login_vhod_textfiels(navController: NavHostController) {
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        //MARK: Ввод логина
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(start = 14.dp, end = 14.dp, top = 16.dp),
            value = login,
            onValueChange = { it -> login = it },
            label = { Text(text = "Логин") },
            placeholder = { Text(text = "Введите логин") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = DarkGray1000,
                placeholderColor = Color(0xFF4B5067),
                focusedLabelColor = Color(0xFF2C98F0),
                focusedIndicatorColor = Color(0xFF2C98F0),
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        //MARK: Ввод пароля
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(start = 14.dp, end = 14.dp, top = 14.dp),
            value = login,
            onValueChange = { it -> login = it },
            label = { Text(text = "Пароль") },
            placeholder = { Text(text = "Введите Пароль") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = DarkGray1000,
                placeholderColor = Color(0xFF4B5067),
                focusedLabelColor = Color(0xFF2C98F0),
                focusedIndicatorColor = Color(0xFF2C98F0),
                unfocusedIndicatorColor = Color.Transparent
            ), singleLine = true
        )



        //MARK: Вход
        Button(
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp, top = 26.dp
                )
                .fillMaxWidth()
                ,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialColor.BLUE_800,
                contentColor = Color.White
            ), onClick = { navController.navigate(Destinations.Onboding) })
        {

            Text(
                text = "Вход", fontSize = 18.sp, fontFamily = FontFamily(
                    Font(R.font.notosans_medium)
                ), modifier = Modifier.padding(bottom = 0.dp)
            )

        }


    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun demo() {
    LytkoTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            //screenLogin(Destinations)
        }
    }


}


//MARK: Экран регистрации
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun screenLogin(navController: NavHostController) {

    val scrollingTabsPagerState = rememberPagerState()
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {


        //MARK: Отображение лого
        Row(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
            //.background(Color.Red)

        ) {

            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                //.background(Color.Gray)
            )

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                //.background(Color.DarkGray)
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    modifier = Modifier
                        .padding(top = 26.dp)
                        //.background(Color.Green)
                        .size(76.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    tint = Color.White
                )

                Text(
                    text = "Lytko",
                    fontSize = 26.sp,
                    fontFamily = FontFamily(
                        Font(R.font.notosans_medium)
                    ),
                    fontWeight = FontWeight(1000),
                    modifier = Modifier.offset(x = 0.dp, y = (-10).dp)
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                //.background(Color.Green)
            )

        }

        val selectedIndex by remember { mutableStateOf(0) }
        val tabs = listOf("Вход", "Регистрация")



        TabRow(
            selectedTabIndex = scrollingTabsPagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = Color(0xFFBCD4E6),
            modifier = Modifier.fillMaxWidth()
        )
        {
            tabs.forEachIndexed { index, tab ->

                Tab(
                    selected = scrollingTabsPagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            scrollingTabsPagerState.animateScrollToPage(index)
                        }
                    },

                    selectedContentColor = Color.White,
                    unselectedContentColor = Color(0xFF1F3C62)

                )
                {
                    Text(
                        text = tab,
                        modifier = Modifier.padding(12.dp),
                        //color = if (scrollingTabsPagerState.currentPage == index) Color.White else Color(0xFF1F3C62),
                        fontFamily = FontFamily(
                            Font(R.font.notosans_regular)
                        ), fontSize = 18.sp
                    )
                }
            }
        }

        HorizontalPager(count = 2, state = scrollingTabsPagerState) { page ->

            when (page) {
                0 -> {
                    Login_vhod_textfiels(navController)
                }
                1 -> {
                    Login_vhod_textfiels(navController)
                }
            }

        }


        //Box(
        //    Modifier
        //        .fillMaxWidth()
        //        .wrapContentSize(Alignment.Center)
        //) {
        //    Text(
        //        text = tabs[selectedIndex],
        //        style = TextStyle(fontSize = 30.sp)
        //    )
        //}


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
        {

            Column(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //MARK: Кнопки Вход регистрация
                Row()
                {


                }

                //MARK: Вход через соцсети
                //MARK: Забыли пароль
                Text(
                    text = "Войти через соцсети",
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontFamily = FontFamily(
                        Font(R.font.notosans_regular)
                    ), fontSize = 14.sp
                )

                //MARK: Забыли пароль
                Text(
                    text = "Забыли логин или  пароль?",
                    color = Color(0xFF2C98F0),
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontFamily = FontFamily(
                        Font(R.font.notosans_regular)
                    ), fontSize = 14.sp
                )

            }


        }


    }


}
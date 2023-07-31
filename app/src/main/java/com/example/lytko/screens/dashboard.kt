package com.example.lytko.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lytko.R
import com.example.lytko.navigation.Destinations
import com.example.lytko.ui.theme.colorBackgroundDark
import com.example.lytko.ui.theme.colorBackgroundLight
import com.example.lytko.ui.theme.colorTextGray
import com.siddroid.holi.colors.MaterialColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {


    TopAppBar(

        navigationIcon = {
            IconButton(
                onClick = { scope.launch { drawerState.open() } },
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 0.dp)

                        .size(32.dp),
                    painter = painterResource(id = R.drawable.menu),
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
                    text = "ДОМ",
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun ScreenDashboard(navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(androidx.compose.material3.DrawerValue.Closed)

    val items = listOf(Icons.Default.Dashboard, Icons.Default.Face)


    val selectedItem = remember { mutableStateOf(items[0]) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet(
                drawerContainerColor = colorBackgroundLight,
                drawerContentColor = Color.White
            ) {
                Spacer(Modifier.height(8.dp))

                Text(
                    "Lytko",
                    modifier = Modifier
                        .fillMaxWidth()
                        ,
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.notosans_medium)),textAlign =TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))

                androidx.compose.material3.Divider()

                Spacer(Modifier.height(8.dp))


                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.system_plus),
                            contentDescription = null
                        )
                    },
                    label = { Text("Добавить новое устройство") },

                    //selected = item == selectedItem.value,
                    selected = false,

                    onClick = {






                        scope.launch { drawerState.close() }
                        //selectedItem.value = item





                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorBackgroundLight,
                        selectedContainerColor = colorBackgroundLight
                    )
                )

                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_2907),
                            contentDescription = null
                        )
                    },
                    label = { Text("Поиск устройств в сети") },

                    //selected = item == selectedItem.value,
                    selected = false,

                    onClick = {
                        scope.launch { drawerState.close() }
                        //selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorBackgroundLight,
                        selectedContainerColor = colorBackgroundLight
                    )
                )


                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = null
                        )
                    },
                    label = { Text("Login") },

                    //selected = item == selectedItem.value,
                    selected = false,

                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Destinations.Login)
                        //selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = colorBackgroundLight,
                        selectedContainerColor = colorBackgroundLight
                    )
                )





            }


        }
    )
    {
        Scaffold(

            topBar = { TopBar(drawerState, scope) },
            containerColor = colorBackgroundDark//Color(0xFF232834)

        )
        { paddingValues ->


            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {

                repeat(5)
                {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                            .height(210.dp),//236
                        colors = CardDefaults.cardColors(containerColor = colorBackgroundLight),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    )
                    {
                        Column(Modifier.fillMaxSize()) {
                            //для значка уровень сигнала


                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                contentAlignment = Alignment.CenterEnd
                                //.background(Color.Green)
                            ) {


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(40.dp),
                                    contentAlignment = Alignment.CenterEnd
                                    //.background(Color.Green)
                                ) {

                                    IconButton(
                                        onClick = { navController.navigate(Destinations.Config_Current) },
                                        modifier = Modifier
                                            .padding(end = 0.dp, top = 4.dp)
                                    ) {

                                        Icon(
                                            modifier = Modifier
                                                //.padding(end = 12.dp, top = 4.dp)
                                                .size(26.dp),
                                            //.background(Color.Green),
                                            painter = painterResource(id = R.drawable.settings),
                                            contentDescription = "",
                                            tint = MaterialColor.GREY_300
                                        )
                                    }


                                }

                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(40.dp),
                                    contentAlignment = Alignment.CenterStart
                                    //.background(Color.Green)
                                ) {

                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 12.dp, top = 4.dp)
                                            .size(26.dp),
                                        //.background(Color.Green),
                                        painter = painterResource(id = R.drawable.wifi_signal2),
                                        contentDescription = "",
                                        tint = MaterialColor.WHITE
                                    )
                                }


                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(), contentAlignment = Alignment.Center
                                    //.background(Color.Green)
                                ) {

                                    Text(
                                        text = "Ванная",
                                        //modifier = Modifier.background(Color.Red),
                                        fontFamily = FontFamily(Font(R.font.notosans_medium)),
                                        fontSize = 24.sp,
                                        style = TextStyle(
                                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                                        )
                                    )
                                }

                            }


/*

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp), contentAlignment = Alignment.CenterEnd
                        //.background(Color.Green)
                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(end = 10.dp, top = 4.dp)
                                .size(24.dp),
                            //.background(Color.Green),
                            painter = painterResource(id = R.drawable.wifi_error),
                            contentDescription = "",
                            tint = MaterialColor.WHITE
                        )
                    }


             */


                            //Температура задание
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    //.wrapContentHeight()
                                    .height(IntrinsicSize.Min)
                                    .padding(top = 8.dp)
                                //.background(Color.Blue),
                                //contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "22.5",
                                    fontSize = 80.sp,
                                    modifier = Modifier
                                        //.background(Color.Black)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    //fontWeight = FontWeight.W500,
                                    //lineHeight = 100.sp,
                                    //maxLines = 1,
                                    //letterSpacing = 3.sp,
                                    fontFamily = FontFamily(Font(R.font.notosans_medium_numeric)),
                                    style = TextStyle(
                                        lineHeight = 2.5.em,
                                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                                    )
                                )

                            }

                            //Текущая температура
                            Box(
                                Modifier
                                    .padding(top = 8.dp)
                                    .fillMaxWidth()
                                    .height(32.dp)
                                //.background(Color.Magenta)
                            ) {

                                Text(
                                    color = colorTextGray,
                                    text = "Сейчас 21.5°C",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        //.background(Color.Blue)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily(Font(R.font.notosans_regular)),
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(
                                        platformStyle = PlatformTextStyle(
                                            includeFontPadding = true
                                        )
                                    )
                                )
                            }

                            //Значки
                            Box(
                                Modifier
                                    .fillMaxSize(),
                                //.background(Color.Red),
                                contentAlignment = Alignment.BottomCenter
                            ) {


                                Row(
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                    //.background(Color.Magenta)
                                ) {


                                    IconButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier.padding(end = 0.dp, bottom = 8.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(start = 0.dp)
                                                .size(48.dp),
                                            //.background(Color.Green),
                                            painter = painterResource(id = R.drawable.device_heat),
                                            contentDescription = "",
                                            tint = MaterialColor.WHITE
                                        )
                                    }

                                    IconButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(start = 0.dp)
                                                .size(48.dp),
                                            //.background(Color.Red),
                                            painter = painterResource(id = R.drawable.down),
                                            contentDescription = "",
                                            tint = MaterialColor.WHITE
                                        )
                                    }


                                    IconButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier.padding(start = 0.dp, bottom = 8.dp)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(start = 0.dp)
                                                .size(48.dp),
                                            //.background(Color.Blue),
                                            painter = painterResource(id = R.drawable.up),
                                            contentDescription = "",
                                            tint = MaterialColor.WHITE
                                        )
                                    }


                                }


                            }


                        }
                    }

                }
            }


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun ScreenDashboardDrawer() {


}











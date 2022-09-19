package com.example.lytko.custom_element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

@Composable
fun CustomnavigationRail()
{
    val contex = LocalContext.current
    val density = LocalDensity.current

   // var showNavigationRail by remember { mutableStateOf(false) }


    //AnimatedVisibility(visibleState = showNavigationRail) {
    //   , enter = slideInHorizontally
    //}



}
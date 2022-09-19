package com.example.lytko.custom_element

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lytko.ui.theme.colorButtonBlue
import com.siddroid.holi.colors.MaterialColor


@Composable
fun CustomBlueButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    height: Dp = 48.dp,
    fontSize: TextUnit = 22.sp,
    roundedRadius: Int = 30
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(roundedRadius),
        modifier = modifier.height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButtonBlue,
            contentColor = Color.White
        )
    ) {
        Text(text = text, fontSize = fontSize)
    }
}


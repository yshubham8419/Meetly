package com.shubham.kumar.meetly.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.shubham.kumar.meetly.ui.theme.Black100
import com.shubham.kumar.meetly.ui.theme.Blue20
import com.shubham.kumar.meetly.ui.theme.Blue30
import com.shubham.kumar.meetly.ui.theme.Green80
import com.shubham.kumar.meetly.ui.theme.Size10
import com.shubham.kumar.meetly.ui.theme.Size120
import com.shubham.kumar.meetly.ui.theme.Size20
import com.shubham.kumar.meetly.ui.theme.Size480
import com.shubham.kumar.meetly.ui.theme.Size580
import com.shubham.kumar.meetly.ui.theme.Size80
import com.shubham.kumar.meetly.ui.theme.SizeNone
import com.shubham.kumar.meetly.ui.theme.TextSize20

@Preview
@Composable
fun Dialer() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(color = Blue20),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDivider()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Size580),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+91 9149984449", fontSize = TextSize20,
                    fontWeight = FontWeight(500)
                )
            }

            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .aspectRatio(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                for (i in 0..2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        for (j in 1..3) {
                            Button1(text = "${(i * 3) + j}")
                        }
                    }
                }
            }
            Button1(text = "Call", textColor = Color.White,containerColor = Green80)
            Spacer(modifier = Modifier.height(Size120))
        }
    }
}

@Composable
private fun Button1(
    text: String = "1",
    onClick: () -> Unit = {},
    textColor: Color = Black100,
    containerColor: Color = Color.White
) {
    Button(
        shape = RoundedCornerShape(Size80),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        modifier = Modifier.clickable { onClick() },
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = Size20,
            pressedElevation = SizeNone
        ),
        contentPadding = PaddingValues(Size80),
        onClick = {

        }
    ) {
        Box(
            modifier = Modifier.size(Size580),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = textColor,
                text = text, fontSize = TextSize20, fontWeight = FontWeight(600)
            )
        }
    }
}
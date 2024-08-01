package com.shubham.kumar.meetly.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.shubham.kumar.meetly.R
import com.shubham.kumar.meetly.ui.theme.Blue80
import com.shubham.kumar.meetly.ui.theme.Size160
import com.shubham.kumar.meetly.ui.theme.Size240
import com.shubham.kumar.meetly.ui.theme.Size480
import com.shubham.kumar.meetly.ui.theme.TextSize14

@Preview
@Composable
fun SearchBar(value: String = "", onValueChanged: (it: String) -> Unit = {}) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = TextSize14,
            color = Color.White,
        ),
        value = value, onValueChange = onValueChanged) {
        Row(
            modifier = Modifier
                .height(Size480)
                .fillMaxWidth()
//                .clip(shape = RoundedCornerShape(Size80))
                .background(color = Blue80),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color.White,
                    modifier = Modifier.size(Size240),
                    painter = painterResource(id = R.drawable.search), contentDescription = null
                )
                Spacer(modifier = Modifier.width(Size160))
                Box(contentAlignment = Alignment.CenterStart){
                    if (value.isEmpty()) {
                        Text(
                            color = Color.White,
                            text = "Search Number",
                            fontSize = TextSize14
                        )
                    }
                    it()
                }
            }
        }
    }
}
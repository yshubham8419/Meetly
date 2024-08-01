package com.shubham.kumar.meetly.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shubham.kumar.meetly.R
import com.shubham.kumar.meetly.ui.composables.SearchBar
import com.shubham.kumar.meetly.ui.routes.Screens
import com.shubham.kumar.meetly.ui.theme.Blue80
import com.shubham.kumar.meetly.ui.theme.Green80
import com.shubham.kumar.meetly.ui.theme.Red80
import com.shubham.kumar.meetly.ui.theme.Size10
import com.shubham.kumar.meetly.ui.theme.Size120
import com.shubham.kumar.meetly.ui.theme.Size160
import com.shubham.kumar.meetly.ui.theme.Size20
import com.shubham.kumar.meetly.ui.theme.Size320
import com.shubham.kumar.meetly.ui.theme.Size40
import com.shubham.kumar.meetly.ui.theme.Size480
import com.shubham.kumar.meetly.ui.theme.Size580
import com.shubham.kumar.meetly.ui.theme.Size60
import com.shubham.kumar.meetly.ui.theme.Size80
import com.shubham.kumar.meetly.ui.theme.SizeNone
import com.shubham.kumar.meetly.ui.theme.TextSize14
import com.shubham.kumar.meetly.ui.theme.TextSize16

@Composable
fun History(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.fillMaxWidth()) {
                SearchBar()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(Size120),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(SizeNone))
                repeat(20) {
                    HistoryItem()
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
        Box(modifier = Modifier.padding(end = Size160, bottom = Size160).align(Alignment.BottomEnd)){
            FloatingActionButton(
                modifier=Modifier.size(Size580),
                containerColor = Blue80,
                onClick = {
                    navController.navigate(Screens.Dialer.path){
                        launchSingleTop  = true
                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_dialpad_24),
                    contentDescription = "dialer",
                    tint = Color.White
                )
            }
        }
    }


}

@Preview(showBackground = true, widthDp = 312)
@Composable
private fun HistoryItem() {
    Card(
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = RoundedCornerShape(Size60),
        elevation = CardDefaults.cardElevation(defaultElevation = Size10),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(Size120)
        ) {
            Box {
                Icon(
                    modifier = Modifier.size(Size480),
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = null,
                    tint = Red80
                )
            }
            Spacer(modifier = Modifier.width(Size120))
            Column {
                Text(
                    text = "+91 9149984449",
                    fontSize = TextSize16,
                    lineHeight = TextSize16,
                    fontWeight = FontWeight(500),

                    )
                Spacer(modifier = Modifier.height(Size20))
                Row {
                    Text(
                        text = "Incoming Call", fontSize = TextSize14,
                        lineHeight = TextSize14
                    )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier.size(Size480),
                contentPadding = PaddingValues(SizeNone),
                onClick = { /*TODO*/ },
                shape = CircleShape
            ) {
                Icon(
                    tint = Green80,
                    modifier = Modifier
                        .size(Size320),
                    painter = painterResource(id = R.drawable.baseline_call_24),
                    contentDescription = null
                )
            }

        }
    }
}
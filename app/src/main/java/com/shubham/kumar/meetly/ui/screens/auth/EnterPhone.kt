package com.shubham.kumar.meetly.ui.screens.auth

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shubham.kumar.meetly.R
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.ui.routes.Screens
import com.shubham.kumar.meetly.ui.theme.Black100
import com.shubham.kumar.meetly.ui.theme.Black30
import com.shubham.kumar.meetly.ui.theme.Black70
import com.shubham.kumar.meetly.ui.theme.Blue50
import com.shubham.kumar.meetly.ui.theme.Blue80
import com.shubham.kumar.meetly.ui.theme.Size10
import com.shubham.kumar.meetly.ui.theme.Size120
import com.shubham.kumar.meetly.ui.theme.Size20
import com.shubham.kumar.meetly.ui.theme.Size200
import com.shubham.kumar.meetly.ui.theme.Size320
import com.shubham.kumar.meetly.ui.theme.Size40
import com.shubham.kumar.meetly.ui.theme.Size60
import com.shubham.kumar.meetly.ui.theme.TextSize36
import com.shubham.kumar.meetly.ui.theme.TextSize42
import com.shubham.kumar.meetly.viewmodel.AuthViewModel

@Composable
fun EnterPhone(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(key1 = authViewModel.authState) {
        if(authViewModel.authState == AuthState.CodeSent){
            navController.navigate(Screens.EnterOtp.path){
                popUpTo(Screens.Auth.path){
                    inclusive = false
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(),
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Column(modifier = Modifier.fillMaxHeight(0.60f)) {
            Text(
                text = "Verify your\nphone number",
                lineHeight = TextSize42,
                fontSize = TextSize36,
                fontWeight = FontWeight.Bold,
                color = Blue80,
                modifier = Modifier.padding(bottom = Size20)
            )
            Spacer(modifier = Modifier.height(Size120))

            Text(
                text = buildAnnotatedString {
                    append("We will send you an ")
                    withStyle(SpanStyle(color = Black100, fontWeight = FontWeight(500))) {
                        append("OTP (One Time Password)")
                    }
                    append(" on this mobile number")
                },
                color = Black70,
                modifier = Modifier.padding(bottom = Size40)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Enter mobile no.*",
                color = Black70,
            )
            Spacer(modifier = Modifier.height(Size120))
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(bottom = Size40),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Container1(isFocussed = false) {
                    Text(
                        text = "+91",
                        color = Black100,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.padding(end = Size20)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = null,
                        modifier = Modifier.size(Size200)
                    )
                }
                Spacer(modifier = Modifier.width(Size120))
                val interactionSource = remember { MutableInteractionSource() }
                val isFocussed = interactionSource.collectIsFocusedAsState()
                BasicTextField(
                    enabled = authViewModel.allowedToEditPhoneNumber.value,
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Size20),
                    value = authViewModel.phoneNumber,
                    onValueChange = authViewModel::updatePhoneNumber,
                    textStyle = TextStyle(
                        color = Black100,
                        fontWeight = FontWeight(500),
                        letterSpacing = 2.sp
                    ),
                    decorationBox = {
                        Container1(
                            modifier = Modifier.fillMaxSize(),
                            isFocussed = isFocussed.value
                        ) {
                            it()
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )

            }
            Spacer(modifier = Modifier.height(Size120))
            val context = LocalContext.current
            Button(
                enabled = authViewModel.allowedToEditPhoneNumber.value,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                          authViewModel.verifyPhoneNumber(120L, context as Activity)
                },
                shape = RoundedCornerShape(Size60),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue80,
                    disabledContainerColor = Blue50
                ),
                contentPadding = PaddingValues(Size120)
            ) {
                when (authViewModel.allowedToEditPhoneNumber.value) {
                    true -> Text(
                        text = "Send OTP",
                        fontWeight = FontWeight(600),
                        color = Color.White,
                    )

                    false ->
                        CircularProgressIndicator(
                            modifier = Modifier.size(Size320),
                            color = Color.White
                        )
                }
            }
        }
    }
}

@Composable
private fun Container1(
    modifier: Modifier = Modifier,
    isFocussed: Boolean,
    content: @Composable RowScope.() -> Unit,
    ) {
    Row(
        modifier = modifier
            .border(
                width = if (isFocussed) Size20 else Size10,
                color = if (isFocussed) Blue80 else Black30,
                shape = RoundedCornerShape(Size60)
            )
            .padding(start = Size120, top = Size120, bottom = Size120, end = Size40),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
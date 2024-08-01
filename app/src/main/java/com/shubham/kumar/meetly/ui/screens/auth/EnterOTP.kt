package com.shubham.kumar.meetly.ui.screens.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.ui.routes.Screens
import com.shubham.kumar.meetly.ui.theme.Black100
import com.shubham.kumar.meetly.ui.theme.Black70
import com.shubham.kumar.meetly.ui.theme.Blue50
import com.shubham.kumar.meetly.ui.theme.Blue60
import com.shubham.kumar.meetly.ui.theme.Blue80
import com.shubham.kumar.meetly.ui.theme.Size120
import com.shubham.kumar.meetly.ui.theme.Size160
import com.shubham.kumar.meetly.ui.theme.Size20
import com.shubham.kumar.meetly.ui.theme.Size320
import com.shubham.kumar.meetly.ui.theme.Size40
import com.shubham.kumar.meetly.ui.theme.Size400
import com.shubham.kumar.meetly.ui.theme.Size60
import com.shubham.kumar.meetly.ui.theme.Size80
import com.shubham.kumar.meetly.ui.theme.TextSize36
import com.shubham.kumar.meetly.ui.theme.TextSize42
import com.shubham.kumar.meetly.viewmodel.AuthViewModel

@Composable
fun EnterOTP(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(key1 = authViewModel.authState) {
        if(authViewModel.authState is AuthState.VerificationFailed){
            navController.navigate(Screens.EnterPhone.path){
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Column(modifier = Modifier.fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "OTP Verification",
                lineHeight = TextSize42,
                fontSize = TextSize36,
                fontWeight = FontWeight.Bold,
                color = Blue80,
            )
            Spacer(modifier = Modifier.height(Size160))

            Text(text = "Enter the code from sms we sent", color = Black70)
            Text(text = buildAnnotatedString {
                append("to ")
                withStyle(SpanStyle(color = Black100, fontWeight = FontWeight(500))) {
                    append(" +${authViewModel.phoneNumber}")
                }
            }, color = Black70)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Enter OTP *",
                color = Black70,
            )
            Spacer(modifier = Modifier.height(Size80))
            OTPField(enabled = !authViewModel.otpEntered, otpLength = 6, otp = authViewModel.otp, onChangeOTP = authViewModel::updateOTP)
            Spacer(modifier = Modifier.height(Size120))
            Button(
                enabled = !authViewModel.otpEntered,
                modifier = Modifier.fillMaxWidth(),
                onClick = { authViewModel.submitOtp() },
                shape = RoundedCornerShape(Size60),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue80,
                    disabledContainerColor = Blue50
                ),
                contentPadding = PaddingValues(Size120)
            ) {
                when (!authViewModel.otpEntered) {
                    true -> Text(
                        text = "Submit OTP",
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
private fun OTPField(enabled:Boolean,otpLength: Int, otp: String, onChangeOTP: (otp: String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocussed = interactionSource.collectIsFocusedAsState()
    BasicTextField(
        enabled = enabled,
        value = TextFieldValue(otp, selection = TextRange(otpLength)), onValueChange = {
            if (it.text.length <= otpLength)
                onChangeOTP(it.text)
        },
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Size80)
            ) {
                repeat(otpLength) { index ->
                    CharacterContainer(
                        character = if (index < otp.length) otp[index] else ' ',
                        isFocussed = (isFocussed.value) && ((otp.length == index)
                                || (index == otpLength - 1 && otp.length == otpLength))
                    )
                }

            }
        },
        interactionSource = interactionSource
    )
}

@Composable
private fun CharacterContainer(character: Char, isFocussed: Boolean = false) {
    Box(
        modifier = Modifier
            .size(Size400)
            .border(
                width = Size20,
                color = if (isFocussed) Blue80 else Blue60,
                shape = RoundedCornerShape(Size40)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(text = character.toString())
    }
}
package com.shubham.kumar.meetly.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.viewmodel.AuthViewModel

@Composable
fun AuthScreen(authViewModel: AuthViewModel = hiltViewModel()) {
    val authState by authViewModel.authState.collectAsState()

    var phoneNumber by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var showOtpInput by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.CodeSent -> showOtpInput = true
            is AuthState.VerificationCompleted -> {
                // Handle successful verification
                showOtpInput = false
            }
            is AuthState.VerificationFailed -> {
                // Handle failed verification
                showOtpInput = false
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!showOtpInput) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    authViewModel.verifyPhoneNumber(phoneNumber, 60L,context as Activity)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify Phone Number")
            }
        } else {
            OutlinedTextField(
                value = otpCode,
                onValueChange = { otpCode = it },
                label = { Text("OTP Code") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    authViewModel.submitOtp(otpCode)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit OTP")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (authState) {
            is AuthState.UnInitialized -> Text("Enter your phone number.")
            is AuthState.Initialized -> Text("Phone number initialized.")
            is AuthState.CodeSent -> Text("OTP code sent to your phone.")
            is AuthState.VerificationCompleted -> Text("Verification completed.")
            is AuthState.VerificationFailed -> Text("Verification failed. Try again.")
        }
    }
}

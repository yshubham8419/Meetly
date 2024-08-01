package com.shubham.kumar.meetly.viewmodel

import android.app.Activity
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var authState by mutableStateOf<AuthState>(AuthState.UnInitialized)
        private set
    var phoneNumber by mutableStateOf("")
        private set

    val allowedToEditPhoneNumber: State<Boolean> =  derivedStateOf {
        authState == AuthState.UnInitialized
                || authState is AuthState.VerificationCompleted
                || authState == AuthState.VerificationFailed
    }

    var otp by mutableStateOf("")
        private set
    var otpEntered by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            authRepository.authState.collect{
                authState = it
                if(it == AuthState.VerificationFailed){
                    phoneNumber = ""
                    otp = ""
                    otpEntered = false
                    authState = AuthState.UnInitialized
                }
            }
        }

    }
    fun verifyPhoneNumber(timeoutSeconds: Long, activity: Activity) {
        viewModelScope.launch {
            authRepository.verifyPhoneNumber("+91$phoneNumber", timeoutSeconds, activity)
        }
    }

    fun submitOtp() {
        otpEntered = true
        viewModelScope.launch {
            authRepository.onOtpSubmitted(otp)
        }
    }

    fun updatePhoneNumber(newNumber:String){
        phoneNumber = newNumber
    }
    fun updateOTP(newOTP:String){
        otp = newOTP
    }
}
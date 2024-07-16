package com.shubham.kumar.meetly.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val authState: StateFlow<AuthState> get() = authRepository.authState

    fun verifyPhoneNumber(phone: String, timeoutSeconds: Long, activity: Activity) {
        viewModelScope.launch {
            authRepository.verifyPhoneNumber(phone, timeoutSeconds, activity)
        }
    }

    fun submitOtp(code: String) {
        viewModelScope.launch {
            authRepository.onOtpSubmitted(code)
        }
    }
}
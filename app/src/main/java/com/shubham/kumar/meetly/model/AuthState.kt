package com.shubham.kumar.meetly.model

sealed class AuthState {
    data object UnInitialized:AuthState()
    data object Initialized : AuthState()
    data object VerificationFailed:AuthState()
    data object CodeSent:AuthState()
    data class VerificationCompleted(val idToken: String) : AuthState()
}

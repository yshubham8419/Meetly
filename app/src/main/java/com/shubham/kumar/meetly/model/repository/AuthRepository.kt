package com.shubham.kumar.meetly.model.repository

import android.app.Activity
import com.shubham.kumar.meetly.model.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState : StateFlow<AuthState>
    suspend fun verifyPhoneNumber(phone:String,timeoutSeconds:Long,activity: Activity)
    suspend fun onOtpSubmitted(code:String)
}
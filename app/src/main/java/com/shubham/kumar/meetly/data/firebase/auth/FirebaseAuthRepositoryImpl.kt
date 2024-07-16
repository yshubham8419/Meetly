package com.shubham.kumar.meetly.data.firebase.auth

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.shubham.kumar.meetly.model.AuthState
import com.shubham.kumar.meetly.model.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class FirebaseAuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepository {

    private val _authState = MutableStateFlow<AuthState>(AuthState.UnInitialized)
    override val authState: StateFlow<AuthState> get() = _authState

    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    override suspend fun verifyPhoneNumber(
        phone: String,
        timeoutSeconds: Long,
        activity: Activity
    ) {
        _authState.value = AuthState.Initialized
        startPhoneNumberVerification(phone, timeoutSeconds, activity)
    }

    private suspend fun startPhoneNumberVerification(
        phoneNumber: String,
        timeoutSeconds: Long,
        activity: Activity
    ) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                CoroutineScope(Dispatchers.IO).launch {
                    signInWithCredential(credential)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                _authState.value = AuthState.VerificationFailed
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                this@FirebaseAuthRepositoryImpl.verificationId = verificationId
                resendToken = token
                _authState.value = AuthState.CodeSent
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setActivity(activity)
            .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun onOtpSubmitted(code: String) {
        verificationId?.let {
            val credential = PhoneAuthProvider.getCredential(it, code)
            signInWithCredential(credential)
        }
    }

    private suspend fun signInWithCredential(credential: PhoneAuthCredential) {
        try {
            val result = withContext(Dispatchers.IO) {
                firebaseAuth.signInWithCredential(credential).await()
            }
            val idToken = result.user?.let { retrieveIdToken(it) }
            _authState.value = if (idToken == null) {
                AuthState.VerificationFailed
            } else {
                AuthState.VerificationCompleted(idToken)
            }
        } catch (e: Exception) {
            _authState.value = AuthState.VerificationFailed
        }
    }

    private suspend fun retrieveIdToken(user: FirebaseUser): String? {
        return try {
            val tokenResult = withContext(Dispatchers.IO) {
                user.getIdToken(true).await()
            }
            tokenResult.token
        } catch (e: Exception) {
            null
        }
    }
}

package com.shubham.kumar.meetly.data.server.auth

import com.shubham.kumar.meetly.data.server.auth.dto.LoginCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login/")
    suspend fun login(@Body loginCredentials: LoginCredentials)


}
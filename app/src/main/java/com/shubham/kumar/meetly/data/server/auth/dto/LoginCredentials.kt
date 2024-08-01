package com.shubham.kumar.meetly.data.server.auth.dto


import com.google.gson.annotations.SerializedName

data class LoginCredentials(
    @SerializedName("id_token")
    val idToken: String
)
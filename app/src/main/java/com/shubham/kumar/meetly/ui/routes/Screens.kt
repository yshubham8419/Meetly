package com.shubham.kumar.meetly.ui.routes

sealed class Screens(val path:String) {
    data object Auth : Screens("auth")
    data object EnterPhone :Screens("enter_phone")
    data object EnterOtp:Screens("enter_otp")
    data object LoginAndVerify :Screens("login_and_verify")
}
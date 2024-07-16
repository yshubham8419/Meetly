package com.shubham.kumar.meetly.ui.routes

sealed class Screens(val path:String) {
    data object auth :Screens("auth_screen")
}
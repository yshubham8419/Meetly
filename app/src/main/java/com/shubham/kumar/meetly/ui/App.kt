package com.shubham.kumar.meetly.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.shubham.kumar.meetly.ui.routes.Screens
import com.shubham.kumar.meetly.ui.screens.auth.EnterOTP
import com.shubham.kumar.meetly.ui.screens.auth.EnterPhone
import com.shubham.kumar.meetly.ui.screens.dashboard.Dialer
import com.shubham.kumar.meetly.ui.screens.dashboard.History
import com.shubham.kumar.meetly.viewmodel.AuthViewModel

@Composable
fun App(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally){
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screens.Auth.path){
                navigation(startDestination = Screens.EnterPhone.path ,route = Screens.Auth.path ){
                    composable(Screens.EnterPhone.path){
                        val authViewModel:AuthViewModel = it.sharedViewModel(navController = navController)
                        EnterPhone(authViewModel = authViewModel,navController = navController)
                    }
                    composable(Screens.EnterOtp.path){
                        val authViewModel:AuthViewModel = it.sharedViewModel(navController = navController)
                        EnterOTP(authViewModel = authViewModel,navController = navController)
                    }
                }
                navigation(startDestination = Screens.History.path,route = Screens.Dashboard.path){
                    composable(Screens.History.path){
                        History(navController = navController)
                    }
                    composable(Screens.Dialer.path){
                        Dialer()
                    }
                }
            }
    }
}

@Composable
private inline fun <reified T:ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController):T{
    val navGraphRoute = destination.parent?.route?:return hiltViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel<T>(parentEntry)
}
package com.shubham.kumar.meetly.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shubham.kumar.meetly.ui.routes.Screens
import com.shubham.kumar.meetly.ui.screens.auth.EnterPhone

@Composable
fun App(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
            val navHostController = rememberNavController()
            NavHost(navController = navHostController, startDestination = Screens.EnterPhone.path){
                composable(Screens.EnterPhone.path){
                    EnterPhone()
                }
            }
    }
}
package com.aufarizazakipradana607062330127.testmagang.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.testmagang.ui.screen.Features
import com.aufarizazakipradana607062330127.testmagang.ui.screen.Login
import com.aufarizazakipradana607062330127.testmagang.ui.screen.MainScreen
import com.aufarizazakipradana607062330127.testmagang.ui.screen.Register

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            Login(navController = navController)
        }
        composable(route = Screen.Register.route) {
            Register(navController = navController)
        }
        composable(route = Screen.Features.route) {
            Features(navController = navController)
        }
    }
}
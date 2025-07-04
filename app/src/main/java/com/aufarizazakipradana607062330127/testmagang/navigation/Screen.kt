package com.aufarizazakipradana607062330127.testmagang.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Features: Screen("features")
}
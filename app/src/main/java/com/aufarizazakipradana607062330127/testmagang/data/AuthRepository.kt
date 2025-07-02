package com.aufarizazakipradana607062330127.testmagang.data

import androidx.compose.runtime.mutableStateListOf

data class User(val username: String, val email: String, val passwordHash: String)

object AuthRepository {
    val registeredUsers = mutableStateListOf<User>()
    var loggedInUser: User? = null
}
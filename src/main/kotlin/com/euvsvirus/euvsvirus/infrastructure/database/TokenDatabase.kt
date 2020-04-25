package com.euvsvirus.euvsvirus.infrastructure.database

import java.util.*


object TokenDatabase {
    var tokens = mutableMapOf<String, String>()

    fun generateTokenForUser(userId: String): String {
        val token = UUID.randomUUID().toString()
        tokens[userId] = token
        return token
    }
}
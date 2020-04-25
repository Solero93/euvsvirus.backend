package com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase

import java.util.*


object TokenDatabase {
    var tokens = mutableMapOf<String, String>()

    fun clean() = run { tokens = mutableMapOf<String, String>() }

    fun generateTokenForUser(userId: String): String {
        val token = UUID.randomUUID().toString()
        tokens[userId] = token
        return token
    }

    fun obtainUserIdFromToken(token: String): String? = tokens.filterKeys { tokens[it] == token }.keys.firstOrNull()
}
package com.euvsvirus.euvsvirus.api.user

import com.euvsvirus.euvsvirus.infrastructure.database.TokenDatabase
import com.euvsvirus.euvsvirus.infrastructure.database.UserDatabase

object DatabaseCleaner {
    fun clean() {
        UserDatabase.clean()
        TokenDatabase.clean()
    }
}
package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase

object DatabaseCleaner {
    fun clean() {
        UserDatabase.clean()
        TokenDatabase.clean()
    }
}
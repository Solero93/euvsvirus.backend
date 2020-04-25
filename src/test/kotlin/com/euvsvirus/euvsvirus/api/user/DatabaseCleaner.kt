package com.euvsvirus.euvsvirus.api.user

import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.UserDatabase

object DatabaseCleaner {
    fun clean() {
        UserDatabase.clean()
        TokenDatabase.clean()
    }
}
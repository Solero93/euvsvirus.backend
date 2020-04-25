package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.user.domain.User
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase

object TokenMother {
    fun getTokenForUser(user: User): String {
        return TokenDatabase.generateTokenForUser(user.id)
    }
}
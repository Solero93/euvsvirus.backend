package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.user.domain.User
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase

object UserMother {
    fun createPeterParkerUser(): User {
        val user = DatabaseUser(
                id = "random",
                firstName = "Peter",
                lastName = "Parker",
                email = "peterparker@mail.com",
                password = "thisisasecret",
                avatarUrl = "randomUrl"
        )
        UserDatabase.storeUser(user)
        return user.toUser()
    }
}
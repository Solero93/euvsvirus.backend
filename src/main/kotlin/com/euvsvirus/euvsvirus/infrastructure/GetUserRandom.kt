package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.domain.GetUserRepository
import com.euvsvirus.euvsvirus.domain.User

class GetUserRandom : GetUserRepository {
    override fun getUser(userId: String): User? {
        return User(
                id = userId,
                firstName = "Peter",
                lastName = "Parker",
                email = "peterparker@mail.com",
                avatarUrl = "randomUrl"
        )
    }
}
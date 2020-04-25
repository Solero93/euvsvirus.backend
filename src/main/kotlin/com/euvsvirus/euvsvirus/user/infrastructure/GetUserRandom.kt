package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.domain.GetUserRepository
import com.euvsvirus.euvsvirus.user.domain.User

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
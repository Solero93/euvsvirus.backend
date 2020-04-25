package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.api.CreateUserRequest
import com.euvsvirus.euvsvirus.user.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.user.domain.User

class StoreUserDoNothing : StoreUserRepository {
    override fun store(createUserRequest: CreateUserRequest): User {
        return User(
                id = "randomId",
                firstName = createUserRequest.firstName,
                lastName = createUserRequest.lastName,
                email = createUserRequest.email,
                avatarUrl = "randomUrl"
        )
    }
}
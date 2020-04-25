package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.api.CreateUserRequest
import com.euvsvirus.euvsvirus.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.domain.User
import org.springframework.stereotype.Repository

@Repository
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
package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.CreateUserRequest
import com.euvsvirus.euvsvirus.api.CreateUserResponse
import org.springframework.stereotype.Component

@Component
class CreateUser {
    fun invoke(createUserRequest: CreateUserRequest): CreateUserResponse {
        with(createUserRequest) {
            return CreateUserResponse(
                    id = "randomId",
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    avatarUrl = "randomUrl"
            )
        }
    }
}
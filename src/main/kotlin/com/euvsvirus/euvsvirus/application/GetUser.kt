package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.GetUserResponse
import org.springframework.stereotype.Service

@Service
class GetUser {
    fun invoke(userId: String): GetUserResponse {
        return GetUserResponse(
                id = userId,
                firstName = "Peter",
                lastName = "Parker",
                email = "peterparker@mail.com",
                avatarUrl = "randomUrl"
        )
    }
}
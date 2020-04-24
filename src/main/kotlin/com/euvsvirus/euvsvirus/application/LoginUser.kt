package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.LoginUserRequest
import com.euvsvirus.euvsvirus.api.LoginUserResponse
import org.springframework.stereotype.Component

@Component
class LoginUser {
    fun invoke(loginUserRequest: LoginUserRequest): LoginUserResponse {
        return LoginUserResponse(
                id = "randomId",
                firstName = "Peter",
                lastName = "Parker",
                email = loginUserRequest.email,
                token = "randomToken",
                avatarUrl = "randomUrl"
        )
    }
}
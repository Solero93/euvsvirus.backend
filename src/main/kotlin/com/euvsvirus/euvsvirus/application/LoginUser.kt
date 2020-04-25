package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest
import com.euvsvirus.euvsvirus.api.user.LoginUserResponse
import org.springframework.stereotype.Service

@Service
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
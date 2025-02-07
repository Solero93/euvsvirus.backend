package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.user.domain.User

data class LoginUserResponse(val id: String, val firstName: String, val lastName: String, val email: String, val avatarUrl: String, val token: String) {
    companion object {
        fun fromUserAndToken(user: User, token: String): LoginUserResponse =
                LoginUserResponse(
                        id = user.id,
                        firstName = user.firstName,
                        lastName = user.lastName,
                        email = user.email,
                        avatarUrl = user.avatarUrl,
                        token = token
                )
    }
}
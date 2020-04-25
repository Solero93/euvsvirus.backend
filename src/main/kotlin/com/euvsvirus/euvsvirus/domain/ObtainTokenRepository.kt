package com.euvsvirus.euvsvirus.domain

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest

interface ObtainTokenRepository {
    fun getToken(loginUserRequest: LoginUserRequest): String
}
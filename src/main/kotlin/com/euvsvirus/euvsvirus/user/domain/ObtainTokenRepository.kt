package com.euvsvirus.euvsvirus.user.domain

import com.euvsvirus.euvsvirus.user.api.LoginUserRequest

interface ObtainTokenRepository {
    fun getToken(loginUserRequest: LoginUserRequest): String
}
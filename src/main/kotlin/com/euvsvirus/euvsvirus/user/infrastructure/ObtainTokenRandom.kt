package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.api.LoginUserRequest
import com.euvsvirus.euvsvirus.user.domain.ObtainTokenRepository

class ObtainTokenRandom : ObtainTokenRepository {
    override fun getToken(loginUserRequest: LoginUserRequest): String {
        return "randomToken"
    }
}
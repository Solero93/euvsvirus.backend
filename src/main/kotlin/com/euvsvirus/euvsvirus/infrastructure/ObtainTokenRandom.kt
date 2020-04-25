package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest
import com.euvsvirus.euvsvirus.domain.ObtainTokenRepository

class ObtainTokenRandom : ObtainTokenRepository {
    override fun getToken(loginUserRequest: LoginUserRequest): String {
        return "randomToken"
    }
}
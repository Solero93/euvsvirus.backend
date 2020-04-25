package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest
import com.euvsvirus.euvsvirus.api.user.LoginUserResponse
import com.euvsvirus.euvsvirus.domain.ObtainTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginUser (@Autowired val obtainTokenRepository: ObtainTokenRepository) {
    fun invoke(loginUserRequest: LoginUserRequest): LoginUserResponse {
        val token = obtainTokenRepository.getToken(loginUserRequest);
        return LoginUserResponse(
                id = "randomId",
                firstName = "Peter",
                lastName = "Parker",
                email = loginUserRequest.email,
                token = token,
                avatarUrl = "randomUrl"
        )
    }
}
package com.euvsvirus.euvsvirus.user.application

import com.euvsvirus.euvsvirus.user.api.LoginUserRequest
import com.euvsvirus.euvsvirus.user.api.LoginUserResponse
import com.euvsvirus.euvsvirus.user.domain.GetUserRepository
import com.euvsvirus.euvsvirus.user.domain.ObtainTokenRepository
import com.euvsvirus.euvsvirus.user.domain.ObtainUserIdFromTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LoginUser @Autowired constructor(
        val obtainTokenRepository: ObtainTokenRepository,
        val obtainUserIdFromTokenRepository: ObtainUserIdFromTokenRepository,
        val getUserRepository: GetUserRepository
) {
    fun invoke(loginUserRequest: LoginUserRequest): LoginUserResponse {
        val token = obtainTokenRepository.getToken(loginUserRequest)
        val userId = obtainUserIdFromTokenRepository.obtainUserId(token) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
        val user = getUserRepository.getUser(userId)!!
        return LoginUserResponse.fromUserAndToken(
                user = user,
                token = token
        )
    }
}
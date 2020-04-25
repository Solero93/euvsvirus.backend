package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest
import com.euvsvirus.euvsvirus.api.user.LoginUserResponse
import com.euvsvirus.euvsvirus.domain.GetUserRepository
import com.euvsvirus.euvsvirus.domain.ObtainTokenRepository
import com.euvsvirus.euvsvirus.domain.ObtainUserIdFromTokenRepository
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
        return LoginUserResponse(
                token = token,
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                avatarUrl = user.avatarUrl
        )
    }
}
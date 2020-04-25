package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.GetUserResponse
import com.euvsvirus.euvsvirus.domain.GetUserRepository
import com.euvsvirus.euvsvirus.domain.ObtainUserIdFromTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetCurrentUser @Autowired constructor(
        val getUserRepository: GetUserRepository,
        val obtainUserIdFromTokenRepository: ObtainUserIdFromTokenRepository
) {
    fun invoke(token: String): GetUserResponse {
        val userId = obtainUserIdFromTokenRepository.obtainUserId(token) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
        val user = getUserRepository.getUser(userId)!!
        return GetUserResponse(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                avatarUrl = user.avatarUrl
        )
    }
}
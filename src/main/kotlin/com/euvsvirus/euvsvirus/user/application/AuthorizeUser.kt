package com.euvsvirus.euvsvirus.user.application

import com.euvsvirus.euvsvirus.user.domain.ObtainUserIdFromTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthorizeUser(@Autowired private val obtainUserIdFromTokenRepository: ObtainUserIdFromTokenRepository) {
    fun invoke(authorization: String): String {
        val token = authorization.removePrefix("Bearer ")
        return obtainUserIdFromTokenRepository.obtainUserId(token)
                ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
    }
}
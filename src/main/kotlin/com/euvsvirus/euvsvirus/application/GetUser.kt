package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.GetUserResponse
import com.euvsvirus.euvsvirus.domain.GetUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetUser(@Autowired val getUserRepository: GetUserRepository) {
    fun invoke(userId: String): GetUserResponse {
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
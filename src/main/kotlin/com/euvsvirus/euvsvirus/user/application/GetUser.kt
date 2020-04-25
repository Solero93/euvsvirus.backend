package com.euvsvirus.euvsvirus.user.application

import com.euvsvirus.euvsvirus.user.api.GetUserResponse
import com.euvsvirus.euvsvirus.user.domain.GetUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetUser(@Autowired private val getUserRepository: GetUserRepository) {
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
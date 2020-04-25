package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.CreateUserRequest
import com.euvsvirus.euvsvirus.api.CreateUserResponse
import com.euvsvirus.euvsvirus.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateUser(@Autowired val storeUserRepository: StoreUserRepository) {
    fun invoke(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user: User = storeUserRepository.store(createUserRequest);
        return CreateUserResponse(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                avatarUrl = user.avatarUrl
        )
    }
}
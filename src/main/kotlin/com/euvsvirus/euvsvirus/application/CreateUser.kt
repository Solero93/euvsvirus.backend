package com.euvsvirus.euvsvirus.application

import com.euvsvirus.euvsvirus.api.user.CreateUserRequest
import com.euvsvirus.euvsvirus.api.user.CreateUserResponse
import com.euvsvirus.euvsvirus.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.domain.User
import com.euvsvirus.euvsvirus.infrastructure.IsThereUserWithSameEmailFromMemory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CreateUser @Autowired constructor(
        val storeUserRepository: StoreUserRepository,
        val isThereUserWithSameEmailFromMemory: IsThereUserWithSameEmailFromMemory
) {
    fun invoke(createUserRequest: CreateUserRequest): CreateUserResponse {
        if (isThereUserWithSameEmailFromMemory.isThereUserWithSameEmail(createUserRequest.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
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
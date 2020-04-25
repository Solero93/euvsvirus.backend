package com.euvsvirus.euvsvirus.user.domain

import com.euvsvirus.euvsvirus.user.api.CreateUserRequest

interface StoreUserRepository {
    fun store(createUserRequest: CreateUserRequest): User
}
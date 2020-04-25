package com.euvsvirus.euvsvirus.domain

import com.euvsvirus.euvsvirus.api.CreateUserRequest

interface StoreUserRepository {
    fun store(createUserRequest: CreateUserRequest): User
}
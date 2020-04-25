package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.api.user.CreateUserRequest
import com.euvsvirus.euvsvirus.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.domain.User
import com.euvsvirus.euvsvirus.infrastructure.database.DatabaseUser
import com.euvsvirus.euvsvirus.infrastructure.database.UserDatabase
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class StoreUserToMemory: StoreUserRepository {
    override fun store(createUserRequest: CreateUserRequest): User {
        val id: String = UUID.randomUUID().toString()
        val databaseUser = DatabaseUser(
                id = id,
                firstName = createUserRequest.firstName,
                lastName = createUserRequest.lastName,
                email = createUserRequest.email,
                password = createUserRequest.password,
                avatarUrl = "randomURL"
        )
        UserDatabase.storeUser(databaseUser)
        return databaseUser.toUser()
    }
}
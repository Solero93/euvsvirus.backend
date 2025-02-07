package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.api.CreateUserRequest
import com.euvsvirus.euvsvirus.user.domain.StoreUserRepository
import com.euvsvirus.euvsvirus.user.domain.User
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
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
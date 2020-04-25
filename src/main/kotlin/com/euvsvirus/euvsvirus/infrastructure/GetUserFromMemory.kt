package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.domain.GetUserRepository
import com.euvsvirus.euvsvirus.domain.User
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class GetUserFromMemory: GetUserRepository {
    override fun getUser(userId: String): User? {
        val databaseUser: DatabaseUser? = UserDatabase.getUser(userId)
        return databaseUser?.toUser()
    }
}
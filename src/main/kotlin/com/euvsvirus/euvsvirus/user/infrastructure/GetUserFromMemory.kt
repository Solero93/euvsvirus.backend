package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.domain.GetUserRepository
import com.euvsvirus.euvsvirus.user.domain.User
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class GetUserFromMemory: GetUserRepository {
    override fun getUser(userId: String): User? {
        val databaseUser: DatabaseUser? = UserDatabase.getUser(userId)
        return databaseUser?.toUser()
    }
}
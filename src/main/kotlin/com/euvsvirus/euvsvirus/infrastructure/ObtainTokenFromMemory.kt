package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.api.user.LoginUserRequest
import com.euvsvirus.euvsvirus.domain.ObtainTokenRepository
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class ObtainTokenFromMemory: ObtainTokenRepository {
    override fun getToken(loginUserRequest: LoginUserRequest): String {
        val user: DatabaseUser = UserDatabase.getUserByEmailPassword(email = loginUserRequest.email, password = loginUserRequest.password)
                ?: throw RuntimeException() // TODO Throw proper exception
        return TokenDatabase.generateTokenForUser(user.id)
    }
}
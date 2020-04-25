package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.api.LoginUserRequest
import com.euvsvirus.euvsvirus.user.domain.ObtainTokenRepository
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.stereotype.Repository

@Repository
class ObtainTokenFromMemory: ObtainTokenRepository {
    override fun getToken(loginUserRequest: LoginUserRequest): String {
        val user: DatabaseUser = UserDatabase.getUserByEmailPassword(email = loginUserRequest.email, password = loginUserRequest.password)
                ?: throw RuntimeException() // TODO Throw proper exception
        return TokenDatabase.generateTokenForUser(user.id)
    }
}
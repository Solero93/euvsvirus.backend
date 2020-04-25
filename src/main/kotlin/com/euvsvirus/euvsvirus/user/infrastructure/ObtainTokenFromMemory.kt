package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.api.LoginUserRequest
import com.euvsvirus.euvsvirus.user.domain.ObtainTokenRepository
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException

@Repository
class ObtainTokenFromMemory: ObtainTokenRepository {
    override fun getToken(loginUserRequest: LoginUserRequest): String {
        val user: DatabaseUser = UserDatabase.getUserByEmailPassword(email = loginUserRequest.email, password = loginUserRequest.password)
                ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
        return TokenDatabase.generateTokenForUser(user.id)
    }
}
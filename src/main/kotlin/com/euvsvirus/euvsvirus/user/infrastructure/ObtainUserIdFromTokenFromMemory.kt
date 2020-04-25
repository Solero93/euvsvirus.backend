package com.euvsvirus.euvsvirus.user.infrastructure

import com.euvsvirus.euvsvirus.user.domain.ObtainUserIdFromTokenRepository
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.TokenDatabase
import org.springframework.stereotype.Repository

@Repository
class ObtainUserIdFromTokenFromMemory: ObtainUserIdFromTokenRepository {
    override fun obtainUserId(token: String): String? = TokenDatabase.obtainUserIdFromToken(token)
}
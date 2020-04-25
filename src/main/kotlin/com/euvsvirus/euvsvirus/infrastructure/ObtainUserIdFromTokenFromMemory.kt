package com.euvsvirus.euvsvirus.infrastructure

import com.euvsvirus.euvsvirus.domain.ObtainUserIdFromTokenRepository
import com.euvsvirus.euvsvirus.infrastructure.database.TokenDatabase
import org.springframework.stereotype.Repository

@Repository
class ObtainUserIdFromTokenFromMemory: ObtainUserIdFromTokenRepository {
    override fun obtainUserId(token: String): String? = TokenDatabase.obtainUserIdFromToken(token)
}
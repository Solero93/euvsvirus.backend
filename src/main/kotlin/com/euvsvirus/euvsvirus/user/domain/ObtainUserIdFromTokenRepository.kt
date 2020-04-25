package com.euvsvirus.euvsvirus.user.domain

interface ObtainUserIdFromTokenRepository {
    fun obtainUserId(token: String): String?
}
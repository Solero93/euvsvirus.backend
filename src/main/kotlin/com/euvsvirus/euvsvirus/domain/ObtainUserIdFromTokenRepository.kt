package com.euvsvirus.euvsvirus.domain

interface ObtainUserIdFromTokenRepository {
    fun obtainUserId(token: String): String?
}
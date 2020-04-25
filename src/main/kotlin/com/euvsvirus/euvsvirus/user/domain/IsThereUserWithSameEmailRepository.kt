package com.euvsvirus.euvsvirus.user.domain

interface IsThereUserWithSameEmailRepository {
    fun isThereUserWithSameEmail(email: String): Boolean
}
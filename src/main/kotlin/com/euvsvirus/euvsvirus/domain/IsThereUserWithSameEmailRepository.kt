package com.euvsvirus.euvsvirus.domain

interface IsThereUserWithSameEmailRepository {
    fun isThereUserWithSameEmail(email: String): Boolean
}
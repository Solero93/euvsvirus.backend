package com.euvsvirus.euvsvirus.domain

interface GetUserRepository {
    fun getUser(userId: String): User?
}
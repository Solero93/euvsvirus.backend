package com.euvsvirus.euvsvirus.user.domain

interface GetUserRepository {
    fun getUser(userId: String): User?
}
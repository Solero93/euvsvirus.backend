package com.euvsvirus.euvsvirus.infrastructure.database

object UserDatabase {
    var users = mutableListOf<DatabaseUser>()

    fun storeUser(user: DatabaseUser) = users.add(user)

    fun getUser(userId: String): DatabaseUser? = users.find { it.id == userId }

    fun getUserByEmailPassword(email: String, password: String): DatabaseUser? = users.find { it.email == email && it.password == password }
}
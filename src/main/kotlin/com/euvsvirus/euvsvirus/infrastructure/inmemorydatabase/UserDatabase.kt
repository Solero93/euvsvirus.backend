package com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase

object UserDatabase {
    var users = mutableListOf<DatabaseUser>()

    fun clean() = run { users = mutableListOf<DatabaseUser>() }

    fun storeUser(user: DatabaseUser) = users.add(user)

    fun getUser(userId: String): DatabaseUser? = users.find { it.id == userId }

    fun numberOfUsers(): Int = users.size

    fun isThereUserWithSameEmail(email: String): Boolean = users.filter { it.email == email }.isNotEmpty()

    fun getUserByEmailPassword(email: String, password: String): DatabaseUser? = users.find { it.email == email && it.password == password }
}
package com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase

import com.euvsvirus.euvsvirus.user.domain.User


data class DatabaseUser(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val avatarUrl: String
) {
    fun toUser(): User = User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            avatarUrl = avatarUrl
    )
}

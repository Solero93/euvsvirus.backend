package com.euvsvirus.euvsvirus.user.domain

data class User(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val avatarUrl: String
)

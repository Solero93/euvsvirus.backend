package com.euvsvirus.euvsvirus.api.user

data class LoginUserResponse(val id: String, val firstName: String, val lastName: String, val email: String, val avatarUrl: String, val token: String)
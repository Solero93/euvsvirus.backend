package com.euvsvirus.euvsvirus.api

data class LoginUserResponse(val id: String, val firstName: String, val lastName: String, val email: String, val token: String, val avatarUrl: String)
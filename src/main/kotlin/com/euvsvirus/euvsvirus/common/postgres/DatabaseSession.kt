package com.euvsvirus.euvsvirus.common.postgres

import kotliquery.Session
import kotliquery.sessionOf

object DatabaseSession {
    private val username = System.getenv("POSTGRES_USER")
    private val password = System.getenv("POSTGRES_PASSWORD")
    private val database = System.getenv("POSTGRES_DB")
    private val host = System.getenv("POSTGRES_HOST")

    fun getSession(): Session {
        return sessionOf("jdbc:postgresql://$host/$database", username, password)
    }
}

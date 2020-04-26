package com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase

import com.euvsvirus.euvsvirus.common.postgres.DatabaseSession.getSession
import kotliquery.queryOf
import java.util.*


object TokenDatabase {
    fun clean() = getSession().use { it.run(queryOf("DELETE FROM public.\"Token\";").asExecute) }

    fun generateTokenForUser(userId: String): String {
        val token = UUID.randomUUID().toString()
        getSession().use {
            it.run(queryOf("""
            INSERT INTO public."Token" (userId, token)
            VALUES(?, ?)
            ON CONFLICT (userId)
            DO
            UPDATE SET token = ?
        """.trimIndent(), userId, token, token).asExecute)
        } // TODO on conflict, update
        return token
    }

    fun obtainUserIdFromToken(token: String): String? {
        return getSession().use {
            it.run(queryOf("""
            SELECT userId
            FROM public."Token"
            WHERE token = ?;
        """.trimIndent(), token).map { row -> row.string("userId") }.asSingle)
        }
    }
}
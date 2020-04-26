package com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase

import com.euvsvirus.euvsvirus.common.postgres.DatabaseSession.getSession
import kotliquery.queryOf

object UserDatabase {
    fun clean() = getSession().use { it.run(queryOf("DELETE FROM public.\"AppUser\";").asExecute) }

    fun storeUser(user: DatabaseUser) {
        getSession().use {
            it.run(queryOf(
                    """
            INSERT INTO public."AppUser" (id, firstName, lastName, email, password, avatarUrl)
            VALUES(?, ?, ?, ?, ?, ?);
        """.trimIndent(),
                    user.id,
                    user.firstName,
                    user.lastName,
                    user.email,
                    user.password,
                    user.avatarUrl
            ).asExecute)
        }
    }

    fun getUser(userId: String): DatabaseUser? {
        return getSession().use {
            it.run(queryOf("""
            SELECT *
            FROM public."AppUser"
            WHERE id = ?;
        """.trimIndent(), userId).map {
                DatabaseUser(
                        id = it.string("id"),
                        firstName = it.string("firstName"),
                        lastName = it.string("lastName"),
                        email = it.string("email"),
                        password = it.string("password"),
                        avatarUrl = it.string("avatarUrl")
                )
            }.asSingle)
        }
    }

    fun numberOfUsers(): Int = getSession().use {
        it.run(queryOf("""
            SELECT COUNT(*) AS numberOfUsers
            FROM public."AppUser"
        """.trimIndent()).map { it.int("numberOfUsers") }.asSingle) ?: 0
    }

    fun isThereUserWithSameEmail(email: String): Boolean = getSession().use {
        it.run(queryOf("""
            SELECT *
            FROM public."AppUser"
            WHERE email = ?
        """.trimIndent(), email).map {}.asList).isNotEmpty()
    }

    fun getUserByEmailPassword(email: String, password: String): DatabaseUser? {
        return getSession().use { session ->
            session.run(queryOf("""
            SELECT *
            FROM public."AppUser"
            WHERE email = ? AND password = ?;
        """.trimIndent(), email, password).map {
                DatabaseUser(
                        id = it.string("id"),
                        firstName = it.string("firstName"),
                        lastName = it.string("lastName"),
                        email = it.string("email"),
                        password = it.string("password"),
                        avatarUrl = it.string("avatarUrl")
                )
            }.asSingle)
        }
    }
}
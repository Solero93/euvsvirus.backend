package com.euvsvirus.euvsvirus.common.postgres

import kotliquery.queryOf
import org.intellij.lang.annotations.Language

object CreateTables {
    fun createTables() {
        createUserTable()
        createTokenTable()
    }

    private fun createUserTable() {
        val session = DatabaseSession.getSession()

        @Language("SQL")
        val statement = """
            -- auto-generated definition
            create table "AppUser"
            (
                id        varchar not null
                    constraint user_pk
                        primary key,
                firstname varchar not null,
                lastname  varchar not null,
                email     varchar not null,
                password  varchar not null,
                avatarurl varchar not null
            );
            
            alter table "AppUser"
                owner to euvsvirus;
        """.trimIndent()
        session.execute(queryOf(statement))
    }

    private fun createTokenTable() {
        val session = DatabaseSession.getSession()

        @Language("SQL")
        val statement = """
            -- auto-generated definition
            create table "Token"
            (
                userid varchar not null
                    constraint token_pk
                        primary key
                    constraint token_user_id_fk
                        references "AppUser"
                        on update cascade on delete cascade,
                token    varchar not null
            );
            
            alter table "Token"
                owner to euvsvirus;
        """.trimIndent()
        session.execute(queryOf(statement))
    }
}
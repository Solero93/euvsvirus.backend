package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.common.postgres.CreateTables.createTables
import com.euvsvirus.euvsvirus.common.postgres.DatabaseSession
import kotliquery.queryOf
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.euvsvirus.euvsvirus")
class EuvsvirusApplication

fun main(args: Array<String>) {
	try {
		createTables()
	} catch (e: Exception) {}
	runApplication<EuvsvirusApplication>(*args)
}

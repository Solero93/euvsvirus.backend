package com.euvsvirus.euvsvirus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.euvsvirus.euvsvirus")
class EuvsvirusApplication

fun main(args: Array<String>) {
	runApplication<EuvsvirusApplication>(*args)
}

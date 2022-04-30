package com.dn.kotlin_mvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class KotlinMvcApplication

fun main(args: Array<String>) {
    runApplication<KotlinMvcApplication>(*args)
}

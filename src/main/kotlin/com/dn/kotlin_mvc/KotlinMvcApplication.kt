package com.dn.kotlin_mvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
class KotlinMvcApplication

fun main(args: Array<String>) {
    runApplication<KotlinMvcApplication>(*args)
}

@PostConstruct
fun started() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
}

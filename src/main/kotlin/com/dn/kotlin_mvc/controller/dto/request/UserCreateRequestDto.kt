package com.dn.kotlin_mvc.controller.dto.request

import com.dn.kotlin_mvc.entity.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 생성 DTO")
data class UserCreateRequestDto(
    @field:Schema(description = "사용자 이름", required = true, example = "daniel")
    val name: String,

    @field:Schema(description = "사용자 이메일", required = true, example = "daniel@test.com")
    val email: String,

    @field:Schema(description = "사용자 나이", example = "30")
    val age: Int?
) {
    fun toEntity(): UserEntity {
        return UserEntity(name = name, email = email, age = age)
    }
}

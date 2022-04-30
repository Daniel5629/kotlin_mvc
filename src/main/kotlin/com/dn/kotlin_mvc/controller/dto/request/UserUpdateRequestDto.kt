package com.dn.kotlin_mvc.controller.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class UserUpdateRequestDto(
    @field:Schema(description = "사용자 아이디", required = true, example = "1")
    val id: Long,

    @field:Schema(description = "사용자 이름", required = true, example = "daniel")
    val name: String,

    @field:Schema(description = "사용자 이메일", required = true, example = "daniel@test.com")
    val email: String,

    @field:Schema(description = "사용자 나이", example = "30")
    val age: Int?
)

package com.dn.kotlin_mvc.controller.dto.response

import io.swagger.v3.oas.annotations.media.Schema

data class MemberResponseDto(
    @field:Schema(description = "회원 아이디", required = true, example = "1")
    val id: Long?,

    @field:Schema(description = "회원 이름", required = true, example = "daniel")
    val name: String?,

    @field:Schema(description = "회원 이메일", required = true, example = "daniel@test.com")
    val email: String?,

    @field:Schema(description = "회원 나이", example = "30")
    val age: Int?
)
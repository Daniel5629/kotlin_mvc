package com.dn.kotlin_mvc.controller.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 패스워드 변경 DTO")
data class MemberPasswordUpdateRequestDto(
    @field:Schema(description = "회원 패스워드", required = true, example = "password")
    val password: String,

    @field:Schema(description = "회원 패스워드 확인", required = true, example = "password check")
    val passwordCheck: String
)

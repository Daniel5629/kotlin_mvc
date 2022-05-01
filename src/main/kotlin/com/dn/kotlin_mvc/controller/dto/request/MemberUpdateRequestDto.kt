package com.dn.kotlin_mvc.controller.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class MemberUpdateRequestDto(
    @field:Schema(description = "회원 이름", required = true, example = "daniel")
    val name: String,

    @field:Schema(description = "회원 패스워드", required = true, example = "password")
    val password: String,

    @field:Schema(description = "회원 전화번호", required = true, example = "01012345678")
    val phoneNumber: String
)

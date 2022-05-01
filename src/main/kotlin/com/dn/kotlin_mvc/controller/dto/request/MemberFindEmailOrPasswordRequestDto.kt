package com.dn.kotlin_mvc.controller.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 이메일 또는 패스워드 찾기 DTO")
data class MemberFindEmailOrPasswordRequestDto(
    @field:Schema(description = "회원 이름", required = true, example = "daniel")
    val name: String,

    @field:Schema(description = "회원 이메일(패스워드 찾기에서 사용)", example = "daniel@test.com")
    val email: String?,

    @field:Schema(description = "회원 전화번호(이메일 찾기에서 사용)", example = "01012345678")
    val phoneNumber: String?
)

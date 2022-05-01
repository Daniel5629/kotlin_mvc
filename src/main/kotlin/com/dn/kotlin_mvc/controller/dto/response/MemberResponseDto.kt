package com.dn.kotlin_mvc.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class MemberResponseDto(
    @field:Schema(description = "회원 아이디", required = true, example = "1")
    val userId: Long?,

    @field:Schema(description = "회원 이름", required = true, example = "daniel")
    val name: String?,

    @field:Schema(description = "회원 이메일", required = true, example = "daniel@test.com")
    val email: String?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @field:Schema(description = "회원 생년월일", required = true, example = "2022-05-05")
    val dateOfBirth: LocalDate?,

    @field:Schema(description = "회원 성별", required = true, example = "MEN|WOMEN")
    val gender: String?,

    @field:Schema(description = "회원 전화번호", required = true, example = "01012345678")
    val phoneNumber: String?
)
package com.dn.kotlin_mvc.controller

import com.dn.kotlin_mvc.controller.dto.request.MemberCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberFindEmailOrPasswordRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.response.MemberResponseDto
import com.dn.kotlin_mvc.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "회원 API")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/v1/members")
    @Operation(summary = "전체 회원 조회 API", description = "전체 회원 조회")
    fun findAllMember(): ResponseEntity<List<MemberResponseDto>> {
        val responseDtoList = memberService.findAllMember()
            .map { memberEntity -> memberEntity.toResponseDto() }
            .toList()

        return ResponseEntity(responseDtoList, HttpStatus.OK)
    }

    @GetMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 조회 API", description = "회원 조회")
    fun findMemberById(
        @Parameter(
            description = "회원 아이디",
            required = true,
            example = "1"
        ) @PathVariable memberId: Long
    ): ResponseEntity<MemberResponseDto> {
        val responseDto = memberService.findMemberById(memberId).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PostMapping("/v1/members")
    @Operation(summary = "회원 등록 API", description = "회원 등록")
    fun createMember(@RequestBody memberCreateRequestDto: MemberCreateRequestDto): ResponseEntity<MemberResponseDto> {
        val responseDto = memberService.createMember(memberCreateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PutMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 수정 API", description = "회원 수정")
    fun updateMember(
        @Parameter(
            description = "회원 아이디",
            required = true,
            example = "1"
        ) @PathVariable memberId: Long,
        @RequestBody memberUpdateRequestDto: MemberUpdateRequestDto
    ): ResponseEntity<MemberResponseDto> {
        val responseDto = memberService.updateMember(memberId, memberUpdateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @DeleteMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 삭제 API", description = "회원 삭제")
    fun deleteMember(
        @Parameter(
            description = "회원 아이디",
            required = true,
            example = "1"
        ) @PathVariable memberId: Long
    ) {
        memberService.deleteMember(memberId)
    }

    @PostMapping("/v1/members/account/email")
    fun findEmail(@RequestBody memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): ResponseEntity<String> {
        val email = memberService.findEmail(memberFindEmailOrPasswordRequestDto)
        return ResponseEntity(email, HttpStatus.OK)
    }

    @PostMapping("/v1/members/account/password")
    fun findPassword(memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): ResponseEntity<String> {
        val password = memberService.findPassword(memberFindEmailOrPasswordRequestDto)
        return ResponseEntity(password, HttpStatus.OK)
    }

}
package com.dn.kotlin_mvc.controller

import com.dn.kotlin_mvc.controller.dto.request.UserCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.UserUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.response.UserResponseDto
import com.dn.kotlin_mvc.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "사용자 API")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/v1/users")
    @Operation(summary = "전체 사용자 조회 API", description = "전체 사용자 조회")
    fun findUsers(): ResponseEntity<List<UserResponseDto>> {
        val responseDtoList = userService.findAllUser()
            .map { userEntity -> userEntity.toResponseDto() }
            .toList()

        return ResponseEntity(responseDtoList, HttpStatus.OK)
    }

    @GetMapping("/v1/users/{userId}")
    @Operation(summary = "사용자 조회 API", description = "사용자 조회")
    fun findUser(
        @Parameter(
            description = "사용자 아이디",
            required = true,
            example = "1"
        ) @PathVariable userId: Long
    ): ResponseEntity<UserResponseDto> {
        val responseDto = userService.findUserById(userId).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PostMapping("/v1/users")
    @Operation(summary = "사용자 등록 API", description = "사용자 등록")
    fun createUser(@RequestBody userCreateRequestDto: UserCreateRequestDto): ResponseEntity<UserResponseDto> {
        val responseDto = userService.createUser(userCreateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PutMapping("/v1/users")
    @Operation(summary = "사용자 수정 API", description = "사용자 수정")
    fun updateUser(@RequestBody userUpdateRequestDto: UserUpdateRequestDto): ResponseEntity<UserResponseDto> {
        val responseDto = userService.updateUser(userUpdateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @DeleteMapping("/v1/users/{userId}")
    @Operation(summary = "사용자 삭제 API", description = "사용자 삭제")
    fun deleteUser(
        @Parameter(
            description = "사용자 아이디",
            required = true,
            example = "1"
        ) @PathVariable userId: Long
    ) {
        userService.deleteUser(userId)
    }

}
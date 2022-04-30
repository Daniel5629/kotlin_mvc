package com.dn.kotlin_mvc.controller

import com.dn.kotlin_mvc.controller.dto.request.UserCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.UserUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.response.UserResponseDto
import com.dn.kotlin_mvc.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userService: UserService
) {

    @GetMapping("/v1/users")
    fun findUsers(): ResponseEntity<List<UserResponseDto>> {
        val responseDtoList = userService.findAllUser()
                .map { userEntity -> userEntity.toResponseDto() }
                .toList()

        return ResponseEntity(responseDtoList, HttpStatus.OK)
    }

    @GetMapping("/v1/users/{id}")
    fun findUser(@PathVariable id: Long): ResponseEntity<UserResponseDto> {
        val responseDto = userService.findUserById(id).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PostMapping("/v1/users")
    fun createUser(@RequestBody userCreateRequestDto: UserCreateRequestDto): ResponseEntity<UserResponseDto> {
        val responseDto = userService.createUser(userCreateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PutMapping("/v1/users")
    fun updateUser(@RequestBody userUpdateRequestDto: UserUpdateRequestDto): ResponseEntity<UserResponseDto> {
        val responseDto = userService.updateUser(userUpdateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @DeleteMapping("/v1/users/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

}
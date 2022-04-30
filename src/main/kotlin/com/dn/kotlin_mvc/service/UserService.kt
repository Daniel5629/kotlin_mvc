package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.UserCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.UserUpdateRequestDto
import com.dn.kotlin_mvc.entity.UserEntity

interface UserService {
    fun findAllUser():List<UserEntity>
    fun findUserById(id: Long): UserEntity
    fun findUserByName(name: String): UserEntity
    fun fundUserByEmail(email: String): UserEntity
    fun createUser(userCreateRequestDto: UserCreateRequestDto): UserEntity
    fun updateUser(userUpdateRequestDto: UserUpdateRequestDto): UserEntity
    fun deleteUser(id: Long)
}
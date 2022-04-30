package com.dn.kotlin_mvc.controller.dto.request

import com.dn.kotlin_mvc.entity.UserEntity

data class UserCreateRequestDto(val name: String, val email: String, val age: Int?) {
    fun toEntity(): UserEntity {
        return UserEntity(name = name, email = email, age = age)
    }
}

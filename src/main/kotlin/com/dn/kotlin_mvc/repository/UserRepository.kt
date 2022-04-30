package com.dn.kotlin_mvc.repository

import com.dn.kotlin_mvc.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity,Long?> {
    fun findByName(name: String): UserEntity?
    fun findByEmail(Email: String): UserEntity?
}
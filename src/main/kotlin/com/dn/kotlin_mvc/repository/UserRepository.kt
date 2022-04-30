package com.dn.kotlin_mvc.repository

import com.dn.kotlin_mvc.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity,Long?> {
    @Query("SELECT * FROM USER where USER.EMAIL = :email", nativeQuery = true)
    fun findByEmail(@Param("email") email: String): UserEntity?
}
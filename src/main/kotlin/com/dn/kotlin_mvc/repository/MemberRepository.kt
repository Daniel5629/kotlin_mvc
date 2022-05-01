package com.dn.kotlin_mvc.repository

import com.dn.kotlin_mvc.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<MemberEntity,Long?> {
    @Query("SELECT * FROM MEMBER where MEMBER.EMAIL = :email", nativeQuery = true)
    fun findByEmail(@Param("email") email: String): MemberEntity?
}
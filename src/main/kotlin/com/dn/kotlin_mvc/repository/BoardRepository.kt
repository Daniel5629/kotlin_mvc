package com.dn.kotlin_mvc.repository

import com.dn.kotlin_mvc.entity.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: JpaRepository<BoardEntity, Long> {
}
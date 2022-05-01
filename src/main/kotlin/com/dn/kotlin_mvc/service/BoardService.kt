package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.BoardCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.BoardUpdateRequestDto
import com.dn.kotlin_mvc.entity.BoardEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardService {
    fun findAllBoard(pageable:Pageable): Page<BoardEntity>
    fun findBoardById(boardId: Long): BoardEntity
    fun createBoard(boardCreateRequestDto: BoardCreateRequestDto): BoardEntity
    fun updateBoard(boardId: Long, boardUpdateRequestDto: BoardUpdateRequestDto): BoardEntity
    fun deleteBoard(boardId: Long)
}
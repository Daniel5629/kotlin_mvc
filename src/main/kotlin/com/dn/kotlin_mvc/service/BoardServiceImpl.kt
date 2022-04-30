package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.BoardCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.BoardUpdateRequestDto
import com.dn.kotlin_mvc.entity.BoardEntity
import com.dn.kotlin_mvc.repository.BoardRepository
import com.dn.kotlin_mvc.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardServiceImpl(
    private var boardRepository: BoardRepository,
    private var userRepository: UserRepository
) : BoardService {

    @Transactional(readOnly = true)
    override fun findAllBoard(pageable: Pageable): Page<BoardEntity> {
        return boardRepository.findAll(pageable)
    }

    @Transactional(readOnly = true)
    override fun findBoardById(boardId: Long): BoardEntity {
       return boardRepository.findByIdOrNull(boardId)
            ?: throw IllegalArgumentException("존재하지 않는 게시물입니다.")
    }

    @Transactional
    override fun createBoard(boardCreateRequestDto: BoardCreateRequestDto): BoardEntity {
        val userEntity = (userRepository.findByIdOrNull(boardCreateRequestDto.writerId)
            ?: throw IllegalArgumentException("게시물을 등록할 수 없는 사용자입니다."))

        val boardEntity = boardCreateRequestDto.toEntity(userEntity)
        boardRepository.save(boardEntity)

        return boardEntity
    }

    @Transactional
    override fun updateBoard(boardUpdateRequestDto: BoardUpdateRequestDto): BoardEntity {
        val boardEntity = findBoardById(boardUpdateRequestDto.boardId)

        if (boardEntity.writer.id != boardUpdateRequestDto.writerId) {
            throw IllegalArgumentException("게시물 수정 권한이 없는 사용자입니다.")
        }

        boardEntity.title = boardUpdateRequestDto.title
        boardEntity.content = boardUpdateRequestDto.content

        return boardEntity
    }

    @Transactional
    override fun deleteBoard(boardId: Long) {
git
    }
}
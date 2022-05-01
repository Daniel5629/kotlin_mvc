package com.dn.kotlin_mvc.controller

import com.dn.kotlin_mvc.controller.dto.request.BoardCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.BoardUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.response.BoardResponseDto
import com.dn.kotlin_mvc.service.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "게시판 API")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping("/v1/boards")
    @Operation(summary = "전체 게시판 조회 API", description = "전체 게시판 조회")
    fun findAllBoard(pageable: Pageable): ResponseEntity<List<BoardResponseDto>> {
        val responseDtoList = boardService.findAllBoard(pageable)
            .map { boardEntity -> boardEntity.toResponseDto() }
            .toList()

        return ResponseEntity(responseDtoList, HttpStatus.OK)
    }

    @GetMapping("/v1/boards/{boardId}")
    @Operation(summary = "게시물 조회 API", description = "게시물 조회")
    fun findUser(
        @Parameter(
            description = "게시물 아이디",
            required = true,
            example = "1"
        ) @PathVariable boardId: Long
    ): ResponseEntity<BoardResponseDto> {
        val responseDto = boardService.findBoardById(boardId).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PostMapping("/v1/boards")
    @Operation(summary = "게시물 등록 API", description = "게시물 등록")
    fun createUser(@RequestBody boardCreateRequestDto: BoardCreateRequestDto): ResponseEntity<BoardResponseDto> {
        val responseDto = boardService.createBoard(boardCreateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PutMapping("/v1/boards/{boardId}")
    @Operation(summary = "게시물 수정 API", description = "게시물 수정")
    fun updateUser(
        @Parameter(
            description = "게시물 아이디",
            required = true,
            example = "1"
        ) @PathVariable boardId: Long,
        @RequestBody boardUpdateRequestDto: BoardUpdateRequestDto
    ): ResponseEntity<BoardResponseDto> {
        val responseDto = boardService.updateBoard(boardId, boardUpdateRequestDto).toResponseDto()
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @DeleteMapping("/v1/boards/{boardId}")
    @Operation(summary = "게시물 삭제 API", description = "게시물 삭제")
    fun deleteUser(
        @Parameter(
            description = "게시물 아이디",
            required = true,
            example = "1"
        ) @PathVariable boardId: Long
    ) {
        boardService.deleteBoard(boardId)
    }

}
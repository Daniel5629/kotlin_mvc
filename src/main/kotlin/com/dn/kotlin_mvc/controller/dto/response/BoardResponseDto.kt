package com.dn.kotlin_mvc.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class BoardResponseDto(
    @field:Schema(description = "게시물 아이디", example = "1")
    val boardId: Long?,

    @field:Schema(description = "게시물 제목", example = "제목")
    val title: String,

    @field:Schema(description = "게시물 내용", example = "내용")
    val content: String,

    @field:Schema(description = "게시물 작성자", example = "작성자")
    val writerName: String,

    @field:Schema(description = "게시물 조회수", example = "10")
    val views: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @field:Schema(description = "게시물 작성일", example = "2022-04-30 11:10:08")
    val createdDate: LocalDateTime?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @field:Schema(description = "게시물 수정일", example = "2022-04-30 11:10:08")
    val updatedDate: LocalDateTime?
)

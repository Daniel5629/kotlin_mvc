package com.dn.kotlin_mvc.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class BoardResponseDto(
    @field:Schema(description = "게시물 제목", example = "제목")
    var title: String?,

    @field:Schema(description = "게시물 내용", example = "내용")
    var content: String?,

    @field:Schema(description = "게시물 작성자", example = "작성자")
    var writerName: String?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @field:Schema(description = "게시물 작성일", example = "2022-04-30")
    var createdDate: LocalDateTime?,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @field:Schema(description = "게시물 수정일", example = "2022-04-30")
    var updatedDate: LocalDateTime?
)

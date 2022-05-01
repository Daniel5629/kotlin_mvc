package com.dn.kotlin_mvc.controller.dto.request

import com.dn.kotlin_mvc.entity.BoardEntity
import com.dn.kotlin_mvc.entity.MemberEntity
import io.swagger.v3.oas.annotations.media.Schema

data class BoardCreateRequestDto(
    @field:Schema(description = "게시물 제목", example = "제목")
    val title: String,

    @field:Schema(description = "게시물 내용", example = "내용")
    val content: String,

    @field:Schema(description = "게시물 작성자", example = "작성자")
    val writerId: Long
) {
    fun toEntity(writer: MemberEntity): BoardEntity {
        return BoardEntity(title = this.title, content = this.content, writer = writer)
    }
}


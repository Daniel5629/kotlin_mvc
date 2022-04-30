package com.dn.kotlin_mvc.controller.dto.request

import com.dn.kotlin_mvc.entity.BoardEntity
import com.dn.kotlin_mvc.entity.UserEntity

data class BoardCreateRequestDto(
    var title: String,
    var content: String,
    var writerId: Long
) {
    fun toEntity(writer: UserEntity): BoardEntity {
        return BoardEntity(title = this.title, content = this.content, writer = writer)
    }
}


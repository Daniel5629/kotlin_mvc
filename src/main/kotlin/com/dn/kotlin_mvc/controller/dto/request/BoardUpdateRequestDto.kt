package com.dn.kotlin_mvc.controller.dto.request

data class BoardUpdateRequestDto(var boardId: Long, var title: String, var content: String, var writerId: Long)


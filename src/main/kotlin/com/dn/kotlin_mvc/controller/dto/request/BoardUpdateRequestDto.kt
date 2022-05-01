package com.dn.kotlin_mvc.controller.dto.request

data class BoardUpdateRequestDto(var title: String, var content: String, var writerId: Long)
// security 도입후 writerId는 삭제.
// writerId 대신 로그인 정보에서 로그인한 회원 아이디를 가져온다.


package com.dn.kotlin_mvc.controller

import com.dn.kotlin_mvc.service.MemberService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
internal class MemberControllerTest {

    @Autowired
    private lateinit var mockMvc:MockMvc

    @InjectMocks
    private lateinit var memberController: MemberController

    @Mock
    private lateinit var memberService: MemberService

    @DisplayName("전체 회원을 조회한다.")
    @Test
    fun findAllMember() {
    }

    @DisplayName("아이디로 회원 조회")
    @Test
    fun findMemberById() {
    }

    @DisplayName("회원 생성")
    @Test
    fun createMember() {
    }

    @DisplayName("회원 수정")
    @Test
    fun updateMember() {
    }

    @DisplayName("회원 삭제")
    @Test
    fun deleteMember() {
    }
}
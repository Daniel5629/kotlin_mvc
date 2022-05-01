package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.MemberCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberFindEmailOrPasswordRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberPasswordUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberUpdateRequestDto
import com.dn.kotlin_mvc.entity.MemberEntity

interface MemberService {
    fun findAllMember():List<MemberEntity>
    fun findMemberById(memberId: Long): MemberEntity
    fun createMember(memberCreateRequestDto: MemberCreateRequestDto): MemberEntity
    fun updateMember(memberId: Long, memberUpdateRequestDto: MemberUpdateRequestDto): MemberEntity
    fun deleteMember(memberId: Long)
    fun updatePassword(memberId: Long, memberPasswordUpdateRequestDto: MemberPasswordUpdateRequestDto): MemberEntity
    fun findEmail(memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): String
    fun findPassword(memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): String
}
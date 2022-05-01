package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.MemberCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberUpdateRequestDto
import com.dn.kotlin_mvc.entity.MemberEntity
import com.dn.kotlin_mvc.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private var memberRepository: MemberRepository
) : MemberService {

    @Transactional(readOnly = true)
    override fun findAllMember(): List<MemberEntity> {
        return memberRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun findMemberById(memberId: Long): MemberEntity {
        return memberRepository.findByIdOrNull(memberId)
            ?: throw IllegalArgumentException("존재하지 않는 회원입니다.")
    }

    @Transactional
    override fun createMember(memberCreateRequestDto: MemberCreateRequestDto): MemberEntity {
        val validateResult = validateUserEmail(memberCreateRequestDto.email)

        if (!validateResult) {
            throw IllegalArgumentException("이미 사용중인 이메일입니다. 다른 이메일을 사용해 주세요!")
        }

        return memberRepository.save(memberCreateRequestDto.toEntity())
    }

    @Transactional
    override fun updateMember(memberId: Long, memberUpdateRequestDto: MemberUpdateRequestDto): MemberEntity {
        val memberEntity = findMemberById(memberId)
        memberEntity.name = memberUpdateRequestDto.name
        memberEntity.email = memberUpdateRequestDto.email
        memberEntity.age = memberUpdateRequestDto.age ?: memberEntity.age

        return memberEntity
    }

    @Transactional
    override fun deleteMember(memberId: Long) {
        val memberEntity = findMemberById(memberId)
        memberRepository.delete(memberEntity)
    }

    private fun validateUserEmail(email: String): Boolean {
        var validateResult = false
        val memberEntity = memberRepository.findByEmail(email)
        memberEntity ?: run { validateResult = true }

        return validateResult
    }
}
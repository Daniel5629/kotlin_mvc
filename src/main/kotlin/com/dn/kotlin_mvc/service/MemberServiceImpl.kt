package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.MemberCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberFindEmailOrPasswordRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberPasswordUpdateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.MemberUpdateRequestDto
import com.dn.kotlin_mvc.entity.MemberEntity
import com.dn.kotlin_mvc.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

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
        memberEntity.phoneNumber = memberUpdateRequestDto.phoneNumber

        return memberEntity
    }

    @Transactional
    override fun deleteMember(memberId: Long) {
        val memberEntity = findMemberById(memberId)
        memberRepository.delete(memberEntity)
    }

    @Transactional
    override fun updatePassword(
        memberId: Long,
        memberPasswordUpdateRequestDto: MemberPasswordUpdateRequestDto
    ): MemberEntity {
        val password = memberPasswordUpdateRequestDto.password
        val passwordCheck = memberPasswordUpdateRequestDto.passwordCheck

        if (password !== passwordCheck) {
            throw IllegalArgumentException("패스워드를 확인해주세요!")
        }

        val memberEntity = findMemberById(memberId)
        memberEntity.password = password

        return memberEntity
    }

    @Transactional(readOnly = true)
    override fun findEmail(memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): String {
        val name = memberFindEmailOrPasswordRequestDto.name
        val phoneNumber = memberFindEmailOrPasswordRequestDto.phoneNumber
            ?: throw IllegalArgumentException("전화번호는 필수값입니다.")

        val memberEntity = (memberRepository.findByNameAndPhoneNumber(name, phoneNumber)
            ?: throw IllegalArgumentException("존재하지 않는 회원입니다."))

        return hideMailFullName(memberEntity)
    }

    @Transactional
    override fun findPassword(memberFindEmailOrPasswordRequestDto: MemberFindEmailOrPasswordRequestDto): String {
        val name = memberFindEmailOrPasswordRequestDto.name
        val email = memberFindEmailOrPasswordRequestDto.email
            ?: throw IllegalArgumentException("이메일은 필수값입니다.")

        val memberEntity = (memberRepository.findByNameAndEmail(name, email)
            ?: throw IllegalArgumentException("존재하지 않는 회원입니다."))

        val tempPassword = generateTempPassword()
        memberEntity.password = tempPassword

        // TODO: 2022/05/01 메일로 임시 패스워드 보내기 구현

        return tempPassword
    }

    private fun validateUserEmail(email: String): Boolean {
        var validateResult = false
        val memberEntity = memberRepository.findByEmail(email)
        memberEntity ?: run { validateResult = true }

        return validateResult
    }

    private fun hideMailFullName(memberEntity: MemberEntity): String {
        val email = memberEntity.email
        val startIndex = email.indexOf("@")
        val mailCorp = email.substring(startIndex)
        val removeMailCorp = email.substring(0, startIndex)
        val replaceRange = removeMailCorp.replaceRange(IntRange(4, removeMailCorp.length - 1), "*****")

        return replaceRange + mailCorp
    }

    private fun generateTempPassword(): String {
        val rawUUID = UUID.randomUUID().toString()

        return rawUUID
            .uppercase()
            .substring(rawUUID.lastIndexOf("-") + 1)
    }
}
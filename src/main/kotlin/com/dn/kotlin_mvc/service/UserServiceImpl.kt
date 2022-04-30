package com.dn.kotlin_mvc.service

import com.dn.kotlin_mvc.controller.dto.request.UserCreateRequestDto
import com.dn.kotlin_mvc.controller.dto.request.UserUpdateRequestDto
import com.dn.kotlin_mvc.entity.UserEntity
import com.dn.kotlin_mvc.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private var userRepository: UserRepository
) : UserService {

    @Transactional(readOnly = true)
    override fun findAllUser(): List<UserEntity> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun findUserById(userId: Long): UserEntity {
        return userRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    @Transactional
    override fun createUser(userCreateRequestDto: UserCreateRequestDto): UserEntity {
        val validateResult = validateUserEmail(userCreateRequestDto.email)

        if (!validateResult) {
            throw IllegalArgumentException("이미 사용중인 이메일입니다. 다른 이메일을 사용해 주세요!")
        }

        return userRepository.save(userCreateRequestDto.toEntity())
    }

    @Transactional
    override fun updateUser(userUpdateRequestDto: UserUpdateRequestDto): UserEntity {
        val userEntity = findUserById(userUpdateRequestDto.id)
        userEntity.name = userUpdateRequestDto.name
        userEntity.email = userUpdateRequestDto.email
        userEntity.age = userUpdateRequestDto.age ?: userEntity.age

        return userEntity
    }

    @Transactional
    override fun deleteUser(userId: Long) {
        val userEntity = findUserById(userId)
        userRepository.delete(userEntity)
    }

    private fun validateUserEmail(email: String): Boolean {
        var validateResult = false
        userRepository.findByEmail(email)
            ?: run { validateResult = true }

        return validateResult
    }
}
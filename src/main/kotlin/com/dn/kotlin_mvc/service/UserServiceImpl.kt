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
    override fun findUserById(id: Long): UserEntity {
        return userRepository.findByIdOrNull(id)
                ?:throw IllegalAccessException("존재하지 않는 사용자 입니다.")
    }

    @Transactional(readOnly = true)
    override fun findUserByName(name: String): UserEntity {
        return userRepository.findByName(name)
                ?:throw IllegalAccessException("존재하지 않는 사용자 입니다.")
    }

    @Transactional(readOnly = true)
    override fun fundUserByEmail(email: String): UserEntity {
        return userRepository.findByEmail(email)
                ?:throw IllegalAccessException("존재하지 않는 사용자 입니다.")
    }

    @Transactional
    override fun createUser(userCreateRequestDto: UserCreateRequestDto): UserEntity {
       return userRepository.save(userCreateRequestDto.toEntity())
    }

    @Transactional
    override fun updateUser(userUpdateRequestDto: UserUpdateRequestDto): UserEntity {
        val userEntity = findUserById(userUpdateRequestDto.id)
        userEntity.name = userUpdateRequestDto.name
        userEntity.email = userUpdateRequestDto.email
        userEntity.age = userUpdateRequestDto.age ?:userEntity.age

        return userEntity
    }

    @Transactional
    override fun deleteUser(id: Long) {
        val userEntity = findUserById(id)
        userEntity.isDelete = true
    }
}
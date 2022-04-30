package com.dn.kotlin_mvc.entity

import com.dn.kotlin_mvc.controller.dto.response.UserResponseDto
import com.dn.kotlin_mvc.entity.base.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Table(name = "user")
@Entity
@SQLDelete(sql = "update user set is_delete = true where id = ?")
@Where(clause = "is_delete = false")
class UserEntity(
    @Column(name = "name") var name: String,
    @Column(name = "email") var email: String,
    @Column(name = "age") var age: Int?,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long? = null

    fun toResponseDto(): UserResponseDto {
        return UserResponseDto(
            id = id,
            name = name,
            email = email,
            age = age
        )
    }
}
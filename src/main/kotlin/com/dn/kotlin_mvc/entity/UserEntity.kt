package com.dn.kotlin_mvc.entity

import com.dn.kotlin_mvc.controller.dto.response.UserResponseDto
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Table(name = "user")
@Entity
@SQLDelete(sql = "update user set is_delete = 1 where id = ?")
@Where(clause = "is_delete = false")
class UserEntity(
        @Column(name = "name") var name: String,
        @Column(name = "email") var email: String,
        @Column(name = "age") var age: Int?,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "is_delete")
    var isDelete: Boolean = false

    fun toResponseDto(): UserResponseDto {
        return UserResponseDto(id, name, email, age)
    }
}
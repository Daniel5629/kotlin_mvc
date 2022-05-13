package com.dn.kotlin_mvc.entity

import com.dn.kotlin_mvc.controller.dto.response.MemberResponseDto
import com.dn.kotlin_mvc.entity.base.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*

@Table(name = "member")
@Entity
@SQLDelete(sql = "update member set is_delete = true where member_id = ?")
@Where(clause = "is_delete = false")
class MemberEntity(
    @Column(name = "name", length = 10) var name: String,
    @Column(name = "email", length = 20) var email: String,
    @Column(name = "password", length = 15) var password: String,
    @Column(name = "date_of_birth", length = 10) var dateOfBirth: LocalDate,
    @Column(name = "gender", length = 5) var gender: String,
    @Column(name = "phone_number", length = 11) var phoneNumber: String
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null

    @Column(name = "is_blocked")
    var isBlocked: Boolean = false

    @Column(name = "login_fail_count")
    var loginFailCount: Int = 0

    fun toResponseDto(): MemberResponseDto {
        return MemberResponseDto(
            userId = id,
            name = name,
            email = email,
            dateOfBirth = dateOfBirth,
            gender = gender,
            phoneNumber = phoneNumber
        )
    }
}
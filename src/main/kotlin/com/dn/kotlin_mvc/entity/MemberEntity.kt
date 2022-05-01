package com.dn.kotlin_mvc.entity

import com.dn.kotlin_mvc.controller.dto.response.MemberResponseDto
import com.dn.kotlin_mvc.entity.base.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Table(name = "member")
@Entity
@SQLDelete(sql = "update member set is_delete = true where member_id = ?")
@Where(clause = "is_delete = false")
class MemberEntity(
    @Column(name = "name") var name: String,
    @Column(name = "email") var email: String,
    @Column(name = "age") var age: Int?,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null

    fun toResponseDto(): MemberResponseDto {
        return MemberResponseDto(
            id = id,
            name = name,
            email = email,
            age = age
        )
    }
}
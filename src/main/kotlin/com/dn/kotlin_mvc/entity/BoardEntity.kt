package com.dn.kotlin_mvc.entity

import com.dn.kotlin_mvc.controller.dto.response.BoardResponseDto
import com.dn.kotlin_mvc.entity.base.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Table(name = "board")
@Entity
@SQLDelete(sql = "update board set is_delete = true where board_id = ?")
@Where(clause = "is_delete = false")
class BoardEntity(
    @Column(name = "title") var title: String,
    @Column(name = "content") var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var writer: UserEntity

) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    var id: Long? = null

    fun toResponseDto(): BoardResponseDto {
        return BoardResponseDto(
            title = title,
            content = content,
            writerName = writer.name,
            createdDate = createdDate,
            updatedDate = updatedDate
        )
    }
}
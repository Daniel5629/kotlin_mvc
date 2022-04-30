package com.dn.kotlin_mvc.entity.base

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : java.io.Serializable {
    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    var createdDate: LocalDateTime? = null

    // TODO: 2022/04/30 security 적용 후 auditing 설정 작성 필요
//    @CreatedBy
//    @Column(name = "created_by", updatable = false, nullable = false)
//    var createdBy: Long? = null

    @LastModifiedDate
    @Column(name = "updated_date")
    var updatedDate: LocalDateTime? = null

    // TODO: 2022/04/30 security 적용 후 auditing 설정 작성 필요
//    @LastModifiedBy
//    @Column(name = "updated_by")
//    var updatedBy: Long? = null

    @Column(name = "is_delete")
    var isDelete: Boolean = false
}
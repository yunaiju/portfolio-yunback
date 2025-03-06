package com.yunback.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Introduction : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "introduction_id")
    var id: Long? = null // ? 붙히면 null 허용

}
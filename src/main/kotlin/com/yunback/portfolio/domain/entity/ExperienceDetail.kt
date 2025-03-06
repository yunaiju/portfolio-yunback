package com.yunback.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class ExperienceDetail : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_detail_id")
    var id: Long? = null // ? 붙히면 null 허용

}
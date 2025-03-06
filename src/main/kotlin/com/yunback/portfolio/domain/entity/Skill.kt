package com.yunback.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Skill : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    var id: Long? = null // ? 붙히면 null 허용

}
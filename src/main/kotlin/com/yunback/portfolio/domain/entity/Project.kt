package com.yunback.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Project : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    var id: Long? = null // ? 붙히면 null 허용

}
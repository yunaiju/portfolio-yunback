package com.yunback.portfolio.domain.repository

import com.yunback.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {
    fun findAllByIsActive(isActive: Boolean): List<Link>
}
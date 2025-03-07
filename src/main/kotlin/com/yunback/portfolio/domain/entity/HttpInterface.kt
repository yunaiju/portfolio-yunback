package com.yunback.portfolio.domain.entity

import jakarta.persistence.*
import jakarta.servlet.http.HttpServletRequest

@Entity
class HttpInterface(httpServletRequest: HttpServletRequest) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "http_interface_id")
    var id: Long? = null // ? 붙히면 null 허용

    var cookies: String? = httpServletRequest.cookies
        // ? (nullable) : null이 아니면 실행, null이면 실행 X -> NullPointException 방지
        // cookies : cookie의 배열
        ?.map{"${it.name}:${it.value}"} // map : 배열을 순차적으로 돌면서 객체 반환
        ?.toString()

    var referer: String? = httpServletRequest.getHeader("referer")
    // referer : 요청이 어디에서 왔는가

    var localAddr: String? = httpServletRequest.localAddr

    var remoteAddr: String? = httpServletRequest.remoteAddr

    var remoteHost: String? = httpServletRequest.remoteHost

    var requestUri: String? = httpServletRequest.requestURI

    var userAgent: String? = httpServletRequest.getHeader("user-agent")

}
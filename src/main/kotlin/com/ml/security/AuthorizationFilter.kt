package com.ml.security

import com.ml.exceptions.AuthenticationException
import com.ml.service.UserDetailsCustomService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) :
    BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")
        if (authorization != null && authorization.startsWith("Bearer")) {
            val authentication = getAuthentication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (!jwtUtil.isValidToken(token)) {
            throw AuthenticationException("Token inválido", "9999")
        }

        val subject = jwtUtil.getSubject(token)
        val customer = userDetailsCustomService.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(subject, null, customer.authorities)
    }
}
package com.fanxan.jemputkuyapi.authentication

import com.fanxan.jemputkuyapi.BaseResponse
import com.fanxan.jemputkuyapi.Constants
import com.fanxan.jemputkuyapi.Empty
import com.fanxan.jemputkuyapi.JemputkuyException
import com.fanxan.jemputkuyapi.services.UserServices
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var userService: UserServices

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try {
            if (JwtConfig.isPremit(request)) {
                filterChain.doFilter(request, response)
            } else {
                val claims = validate(request)
                if (claims[Constants.CLAIMS] != null) {
//                Setup
                    setupAuthentication(claims){
                        filterChain.doFilter(request, response)
                    }
                } else {
                    SecurityContextHolder.clearContext()
                    throw JemputkuyException("Token Required")
                }
            }

        } catch (e: Exception) {
            val errorResponse = BaseResponse<Empty>()
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"


            when (e) {
                is UnsupportedJwtException -> {
                    errorResponse.message = "Error unsupported!!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)
                    response.writer.println(responseString)
//                    response.writer.println("Error unsupported!!")
//                    throw JemputkuyException("Error Unsupported")
                }
                else -> {
                    errorResponse.message = e.message ?: "Token Invalid"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)
                    response.writer.println(responseString)
//                response.writer.println("Error Invalid Token !!")
//                throw JemputkuyException("Error Invalid Token !!")



                }
            }
        }
    }

    private fun validate(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader("Authorization")
        return Jwts.parserBuilder()
            .setSigningKey(Constants.SECRET.toByteArray())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun setupAuthentication(claims: Claims, doOnNext: () -> Unit) {
        val authorities = claims[Constants.CLAIMS] as List<String>
        val authStream = authorities.stream().map {
            SimpleGrantedAuthority(it)
        }
            .collect(Collectors.toList())
        val auth = UsernamePasswordAuthenticationToken(claims.subject, null, authStream)
        SecurityContextHolder.getContext().authentication = auth
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        println("ASUUUUU subject ---> $claims")
        println("ASUUUUU user id ---> $userId")
        doOnNext.invoke()
    }

}
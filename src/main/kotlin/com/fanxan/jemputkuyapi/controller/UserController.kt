package com.fanxan.jemputkuyapi.controller

import com.fanxan.jemputkuyapi.BaseResponse
import com.fanxan.jemputkuyapi.model.LoginResponse
import com.fanxan.jemputkuyapi.model.User
import com.fanxan.jemputkuyapi.model.UserLogin
import com.fanxan.jemputkuyapi.model.UserRequest
import com.fanxan.jemputkuyapi.services.UserServices
import com.fanxan.jemputkuyapi.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @Autowired
    private lateinit var userServices: UserServices

    @GetMapping
    fun getUser(
    ): BaseResponse<User> {
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        return userServices.getUserByUserId(userId.orEmpty()).toResponse()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userLogin: UserLogin
    ): BaseResponse<LoginResponse> {
        return userServices.login(userLogin).toResponse()
    }

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<Boolean> {
        return userServices.register(userRequest.mapToUser()).toResponse()
    }
}
package com.fanxan.jemputkuyapi.controller

import com.fanxan.jemputkuyapi.BaseResponse
import com.fanxan.jemputkuyapi.model.*
import com.fanxan.jemputkuyapi.services.UserServices
import com.fanxan.jemputkuyapi.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/user/driver")
class DriverController {
    @Autowired
    private lateinit var userServices: UserServices

    @GetMapping
    fun getUser(
    ): BaseResponse<User> {
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        println("<><><><> $userId")
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
        return userServices.register(userRequest.mapToNewDriver()).toResponse()
    }
}
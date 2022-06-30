package com.fanxan.jemputkuyapi.services

import com.fanxan.jemputkuyapi.model.LoginResponse
import com.fanxan.jemputkuyapi.model.User
import com.fanxan.jemputkuyapi.model.UserLogin

interface UserServices {
    fun login(userLogin: UserLogin): Result<LoginResponse>
    fun register(user: User):Result<Boolean>
    fun getUserByUsername(username:String):Result<User>
    fun getUserByUserId(id:String):Result<User>
}
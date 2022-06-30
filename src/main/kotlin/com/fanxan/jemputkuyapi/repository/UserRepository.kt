package com.fanxan.jemputkuyapi.repository

import com.fanxan.jemputkuyapi.model.User

interface UserRepository {
    fun insertUser(user: User):Result<Boolean>
    fun getUserById(id: String):Result<User>
    fun getUserByUsername(username: String):Result<User>
//    fun getUserByRoles(roles:String):Result<User>
}
package com.fanxan.jemputkuyapi.services

import com.fanxan.jemputkuyapi.JemputkuyException
import com.fanxan.jemputkuyapi.authentication.JwtConfig
import com.fanxan.jemputkuyapi.model.LoginResponse
import com.fanxan.jemputkuyapi.model.User
import com.fanxan.jemputkuyapi.model.UserLogin
import com.fanxan.jemputkuyapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

@Service
class UserServicesImpl(
    @Autowired
    private val userRepository: UserRepository
) : UserServices {
    override fun login(userLogin: UserLogin): Result<LoginResponse> {
        val resultUser = userRepository.getUserByUsername(userLogin.username)
        return resultUser.map {
            val token = JwtConfig.generatedToken(it)
            val passwordIndb = it.password
            val passwordRequest = userLogin.password
            if (passwordIndb == passwordRequest) {
                LoginResponse(token)
            } else {
                throw JemputkuyException("Password invalid!")
            }
        }
    }

    override fun register(user: User): Result<Boolean> {
        return userRepository.insertUser(user)
    }

    override fun getUserByUsername(username: String): Result<User> {
        return userRepository.getUserByUsername(username).map {
            it.password = "null"
            it
        }
    }

    override fun getUserByUserId(id: String): Result<User> {
        return userRepository.getUserById(id).map {
            it.password = "null"
            it
        }
    }

    override fun updateUser(id: String, user: User): Result<Boolean> {
        return userRepository.updateUser(id, user)
    }
}
package com.fanxan.jemputkuyapi.model

import com.fanxan.jemputkuyapi.utils.RoleEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

data class User(
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var first_name: String = "",
        var last_name: String = "",
        var phone_number: String = "",
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var role: Int = RoleEnum.CUSTOMER.id

) {
    companion object {
        fun createNewUser(
                username: String,
                password: String,
                email: String,
                address: String,
                phone_number: String,
                first_name: String,
                last_name: String,
                lat: Double,
                lng: Double
        ): User {
            return User(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    phone_number = phone_number,
                    first_name = first_name,
                    last_name = last_name,
                    lat = lat,
                    lng = lng
            )
        }

        fun createNewCustomer(
                username: String,
                password: String,
                email: String,
                address: String,
                phone_number: String,
                first_name: String,
                last_name: String,
                lat: Double,
                lng: Double
        ): User {
            return User(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    phone_number = phone_number,
                    first_name = first_name,
                    last_name = last_name,
                    lat = lat,
                    lng = lng,
                    role = RoleEnum.CUSTOMER.id
            )
        }


        fun createNewDriver(
                username: String,
                password: String,
                email: String,
                address: String,
                phone_number: String,
                first_name: String,
                last_name: String,
                lat: Double,
                lng: Double
        ): User {
            return User(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    phone_number = phone_number,
                    first_name = first_name,
                    last_name = last_name,
                    lat = lat,
                    lng = lng,
                    role = RoleEnum.DRIVER.id
            )
        }

        fun userCustomer(
                username: String,
                password: String,
                email: String,
                address: String,
                phone_number: String,
                first_name: String,
                last_name: String,
                lat: Double,
                lng: Double
        ): User {
            return User(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    phone_number = phone_number,
                    first_name = first_name,
                    last_name = last_name,
                    lat = lat,
                    lng = lng,
                    role = RoleEnum.DRIVER.id
            )
        }

    }
}
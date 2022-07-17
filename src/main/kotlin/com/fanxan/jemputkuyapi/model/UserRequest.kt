package com.fanxan.jemputkuyapi.model

import com.fanxan.jemputkuyapi.utils.RoleEnum

data class UserRequest(
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var phone_number: String = "",
        var first_name: String = "",
        var last_name: String = "",
        var lat: String = "",
        var lng: String = ""

) {
    fun mapToUser(): User {
        return User.createNewUser(username, password, email, address, phone_number, first_name, last_name, lat, lng)
    }

    fun mapToCustomer(): User {
        return User.userCustomer(username, password, email, address, phone_number, first_name, last_name, lat, lng)
    }

    fun mapToNewCustomer(): User {
        return User.createNewCustomer(username, password, email, address, phone_number, first_name, last_name, lat, lng)
    }

    fun mapToNewDriver(): User {
        return User.createNewDriver(username, password, email, address, phone_number, first_name, last_name, lat, lng)
    }

}
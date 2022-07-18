package com.fanxan.jemputkuyapi.model

data class CustomerRequest(
    var username: String = "",
    var password: String = "",
    var email: String = "",
    var address: String = "",
    var phone_number: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
){
    fun mapToNewCustomer(): User {
        return User.createNewCustomer(username, password, email, address, phone_number, first_name, last_name,lat,lng)
    }
}

package com.fanxan.jemputkuyapi.location.services

import com.fanxan.jemputkuyapi.location.entity.Coordinate
import com.fanxan.jemputkuyapi.location.entity.Location
import com.fanxan.jemputkuyapi.location.entity.Routes

interface LocationServices {
    fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>>
    fun reserveLocation(coordinate: Coordinate): Result<Location>
    fun getRoutesLocation(origin: Coordinate, destination: Coordinate): Result<Routes>
}
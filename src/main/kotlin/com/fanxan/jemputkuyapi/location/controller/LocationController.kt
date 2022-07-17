package com.fanxan.jemputkuyapi.location.controller

import com.fanxan.jemputkuyapi.BaseResponse
import com.fanxan.jemputkuyapi.coordinateStringToData
import com.fanxan.jemputkuyapi.location.entity.Coordinate
import com.fanxan.jemputkuyapi.location.entity.Location
import com.fanxan.jemputkuyapi.location.entity.Routes
import com.fanxan.jemputkuyapi.location.services.LocationServices
import com.fanxan.jemputkuyapi.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location")
class LocationController {

    @Autowired
    private lateinit var locationServices: LocationServices

    @GetMapping("/search")
    fun searchLocation(
            @RequestParam(value = "name", required = true) name: String,
            @RequestParam(value = "coordinate", required = true) coordinate: String
    ): BaseResponse<List<Location>> {
        val coordinateData = coordinate.coordinateStringToData()
        return locationServices.searchLocation(name, coordinateData).toResponse()
    }

    @GetMapping("/reserve")
    fun reserveLocation(
            @RequestParam(value = "coordinate", required = true) coordinate: String
    ): BaseResponse<Location> {
        val coordinateData = coordinate.coordinateStringToData()
        return locationServices.reserveLocation(coordinateData).toResponse()
    }

    @GetMapping("/routes")
    fun routesLocation(
            @RequestParam(value = "origin") origin: String,
            @RequestParam(value = "destination") destination: String
    ): BaseResponse<Routes> {
        val coordinateOrigin = origin.coordinateStringToData()
        val coordinateDestination = destination.coordinateStringToData()
        return locationServices.getRoutesLocation(coordinateOrigin, coordinateDestination).toResponse()
    }
}
package com.fanxan.jemputkuyapi.location.services

import com.fanxan.jemputkuyapi.location.entity.Coordinate
import com.fanxan.jemputkuyapi.location.entity.Location
import com.fanxan.jemputkuyapi.location.component.LocationComponent
import com.fanxan.jemputkuyapi.location.entity.Routes
import com.fanxan.jemputkuyapi.location.mapper.Mapper
import com.fanxan.jemputkuyapi.orThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationServicesImpl(
        @Autowired
        private val locationComponent: LocationComponent
) : LocationServices {

    override fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>> {
        return locationComponent.searchLocation(name, coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it)
        }

    }

    override fun reserveLocation(coordinate: Coordinate): Result<Location> {
        return locationComponent.reserveLocation(coordinate).map {
            Mapper.mapSearchLocationHereToLocation(it).firstOrNull().orThrow("Location not found")
        }
    }

    override fun getRoutesLocation(origin: Coordinate, destination: Coordinate): Result<Routes> {
        return locationComponent.getRoutes(origin, destination).map {
            val coordinates = Mapper.mapRoutesHereToRoute(it)
            Routes(coordinates)
        }
    }
}
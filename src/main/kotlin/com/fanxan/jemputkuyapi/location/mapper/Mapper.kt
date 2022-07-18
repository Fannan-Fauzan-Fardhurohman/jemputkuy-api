package com.fanxan.jemputkuyapi.location.mapper

import com.fanxan.jemputkuyapi.location.entity.Coordinate
import com.fanxan.jemputkuyapi.location.entity.Location
import com.fanxan.jemputkuyapi.location.entity.LocationHereApiResult
import com.fanxan.jemputkuyapi.location.entity.LocationHereRouteResult
import com.fanxan.jemputkuyapi.util.PolylineEncoderDecoder

object Mapper {
    fun mapSearchLocationHereToLocation(locationHereApiResult: LocationHereApiResult): List<Location> {
        return locationHereApiResult.items?.map {
            val address = Location.Address(
                    city = it?.address?.city.orEmpty(),
                    country = it?.address?.countryName.orEmpty(),
                    district = it?.address?.district.orEmpty()
            )
            Location(
                    name = it?.title.orEmpty(),
                    address = address,
                    coordinate = Coordinate(it?.position?.lat ?: 0.0, it?.position?.lng ?: 0.0)
            )
        }.orEmpty()
    }

    fun mapRoutesHereToRoute(locationHereRouteResult: LocationHereRouteResult): List<Coordinate> {
        val polylineString = locationHereRouteResult.routes
                ?.firstOrNull()
                ?.sections
                ?.firstOrNull()
                ?.polyline
                .orEmpty()

        val coordinates = PolylineEncoderDecoder.decode(polylineString).map {
            Coordinate(it.lat, it.lng)
        }
        return coordinates
    }
}
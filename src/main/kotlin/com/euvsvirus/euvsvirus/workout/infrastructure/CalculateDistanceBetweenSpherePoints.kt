package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.domain.SpherePoint
import java.lang.Math.toRadians
import java.math.BigDecimal
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object CalculateDistanceBetweenSpherePoints {
    fun calculateDistanceInMetres(point1: SpherePoint, point2: SpherePoint): BigDecimal {
        // NOTE https://stackoverflow.com/a/16794680
        val R = 6371 // Radius of the earth

        val latitudePoint1 = point1.latitude
        val latitudePoint2 = point2.latitude
        val longitudePoint1 = point1.longitude
        val longitudePoint2 = point2.longitude

        val latDistance = toRadians(latitudePoint1 - latitudePoint2)
        val lonDistance = toRadians(longitudePoint1 - longitudePoint2)
        val a = (sin(latDistance / 2) * sin(latDistance / 2)
                + (cos(toRadians(latitudePoint1)) * cos(toRadians(latitudePoint2))
                * sin(lonDistance / 2) * sin(lonDistance / 2)))
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return BigDecimal(R * c * 1000) // convert to meters
    }
}
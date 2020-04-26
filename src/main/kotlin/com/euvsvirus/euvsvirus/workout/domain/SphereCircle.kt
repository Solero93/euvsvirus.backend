package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class SphereCircle(val center: SpherePoint, val radius: Double) {
    init {
        assert(BigDecimal(radius) > BigDecimal(0.0))
    }

    fun toDoubleList(): List<Double> {
        return listOf(center.latitude, center.longitude, radius)
    }

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double, radius: Double): SphereCircle {
            return SphereCircle(
                    center = SpherePoint.fromNumbers(latitude = latitude, longitude = longitude),
                    radius = radius
            )
        }
    }
}
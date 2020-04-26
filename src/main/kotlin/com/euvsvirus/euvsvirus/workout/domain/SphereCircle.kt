package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class SphereCircle(val center: SpherePoint, val radius: Double) {
    init {
        assert(BigDecimal(radius) > BigDecimal(0.0))
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
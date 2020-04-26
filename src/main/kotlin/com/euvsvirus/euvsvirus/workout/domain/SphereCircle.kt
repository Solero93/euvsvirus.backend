package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class SphereCircle(val latitude: BigDecimal, val longitude: BigDecimal, val radius: BigDecimal) {
    init {
        assert(BigDecimal(-90) <= latitude && latitude <= BigDecimal(90))
        assert(BigDecimal(-180) <= longitude && longitude <= BigDecimal(180))
        assert(radius > BigDecimal(0))
    }

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double, radius: Double): SphereCircle {
            return SphereCircle(
                    latitude = BigDecimal(latitude),
                    longitude = BigDecimal(longitude),
                    radius = BigDecimal(radius)
            )
        }
    }
}
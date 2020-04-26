package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class PointWithIntensity(val latitude: BigDecimal, val longitude: BigDecimal, val intensity: BigDecimal) {
    init {
        assert(BigDecimal(-90) <= latitude && latitude <= BigDecimal(90))
        assert(BigDecimal(-180) <= longitude && longitude <= BigDecimal(180))
        assert(BigDecimal(0) <= intensity && intensity <= BigDecimal(100))
    }

    fun toBigDecimalList(): List<BigDecimal> {
        return listOf(latitude, longitude, intensity)
    }

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double, intensity: Double): PointWithIntensity {
            return PointWithIntensity(
                    latitude = BigDecimal(latitude),
                    longitude = BigDecimal(longitude),
                    intensity = BigDecimal(intensity)
            )
        }
    }
}
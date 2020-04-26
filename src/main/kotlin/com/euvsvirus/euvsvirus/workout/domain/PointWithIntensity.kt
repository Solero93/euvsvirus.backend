package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class PointWithIntensity(val point: SpherePoint, val intensity: Double) {
    init {
        assert(BigDecimal(0) <= BigDecimal(intensity) && BigDecimal(intensity) <= BigDecimal(100))
    }

    fun toDoubleList(): List<Double> {
        return listOf(point.latitude, point.longitude, intensity)
    }

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double, intensity: Double): PointWithIntensity {
            return PointWithIntensity(
                    point = SpherePoint(latitude = latitude, longitude = longitude),
                    intensity = intensity
            )
        }
    }
}
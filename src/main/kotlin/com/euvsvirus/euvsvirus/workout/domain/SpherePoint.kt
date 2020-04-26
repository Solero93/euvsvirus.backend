package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class SpherePoint(val latitude: Double, val longitude: Double) {
    init {
        assert(BigDecimal(-90) <= BigDecimal(latitude) && BigDecimal(latitude) <= BigDecimal(90))
        assert(BigDecimal(-180) <= BigDecimal(longitude) && BigDecimal(longitude) <= BigDecimal(180))
    }

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double): SpherePoint {
            return SpherePoint(latitude, longitude)
        }
    }
}
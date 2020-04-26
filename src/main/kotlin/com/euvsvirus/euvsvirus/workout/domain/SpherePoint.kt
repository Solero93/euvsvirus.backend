package com.euvsvirus.euvsvirus.workout.domain

import java.math.BigDecimal

data class SpherePoint(val latitude: Double, val longitude: Double) {
    init {
        assert(BigDecimal(-90) <= BigDecimal(latitude) && BigDecimal(latitude) <= BigDecimal(90))
        assert(BigDecimal(-180) <= BigDecimal(longitude) && BigDecimal(longitude) <= BigDecimal(180))
    }

    fun addDeltaVector(vector: SphereVector) = addDelta(vector.latitude, vector.longitude)

    fun subtractDeltaVector(vector: SphereVector) = addDelta(-vector.latitude, -vector.longitude)

    fun addDelta(deltaLatitude: Double, deltaLongitude: Double): SpherePoint = SpherePoint(latitude + deltaLatitude, longitude + deltaLongitude)

    companion object {
        fun fromNumbers(latitude: Double, longitude: Double): SpherePoint {
            return SpherePoint(latitude, longitude)
        }
    }
}
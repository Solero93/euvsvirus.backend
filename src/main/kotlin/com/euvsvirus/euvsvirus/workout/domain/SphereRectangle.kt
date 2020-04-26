package com.euvsvirus.euvsvirus.workout.domain

data class SphereRectangle(val topLeft: SpherePoint, val bottomRight: SpherePoint) {
    val maxLatitude = bottomRight.latitude
    val maxLongitude = topLeft.longitude
    val minLatitude = topLeft.latitude
    val minLongitude = bottomRight.longitude

    init {
        assert(maxLatitude >= minLatitude)
        assert(maxLongitude >= minLongitude)
    }

    fun isInside(point: SpherePoint): Boolean {
        with(point) {
            return minLatitude <= latitude && latitude <= maxLatitude
                    && minLongitude <= longitude && longitude <= maxLongitude
        }
    }

    companion object {
        fun fromList(bounds: List<List<Double>>): SphereRectangle {
            return SphereRectangle(
                    topLeft = SpherePoint.fromNumbers(bounds[0][0], bounds[0][1]),
                    bottomRight = SpherePoint.fromNumbers(bounds[1][0], bounds[1][1])
            )
        }
    }
}
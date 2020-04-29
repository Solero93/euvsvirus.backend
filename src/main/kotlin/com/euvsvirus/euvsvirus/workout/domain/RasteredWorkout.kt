package com.euvsvirus.euvsvirus.workout.domain

import java.time.ZonedDateTime

data class RasteredWorkout(
        val id: String,
        val userId: String,
        val datetimeStart: ZonedDateTime,
        val datetimeEnd: ZonedDateTime,
        val sport: String,
        val raster: List<PointWithIntensity>
)
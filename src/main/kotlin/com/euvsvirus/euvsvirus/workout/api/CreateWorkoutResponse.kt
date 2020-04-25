package com.euvsvirus.euvsvirus.workout.api

import java.time.ZonedDateTime

data class CreateWorkoutResponse(
        val id: String,
        val datetimeStart: ZonedDateTime,
        val datetimeEnd: ZonedDateTime,
        val sport: String,
        val raster: List<List<Float>>
)
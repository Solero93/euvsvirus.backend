package com.euvsvirus.euvsvirus.workout.api

import java.math.BigDecimal
import java.time.ZonedDateTime

data class CreateWorkoutRequest(
        val datetimeStart: ZonedDateTime,
        val datetimeEnd: ZonedDateTime,
        val sport: String,
        val points: List<List<BigDecimal>>
)
package com.euvsvirus.euvsvirus.workout.domain

import java.time.ZonedDateTime

data class Workout(
        val id: String,
        val userId: String,
        val datetimeStart: ZonedDateTime,
        val datetimeEnd: ZonedDateTime,
        val sport: String,
        val points: List<SphereCircle>
)
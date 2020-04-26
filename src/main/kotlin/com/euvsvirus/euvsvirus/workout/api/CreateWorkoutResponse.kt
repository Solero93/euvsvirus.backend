package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.Workout
import java.time.ZonedDateTime

data class CreateWorkoutResponse(
        val id: String,
        val userId: String,
        val datetimeStart: ZonedDateTime,
        val datetimeEnd: ZonedDateTime,
        val sport: String,
        val raster: List<List<Double>>
) {
    companion object {
        fun fromWorkout(workout: Workout): CreateWorkoutResponse {
            return CreateWorkoutResponse(
                    id = workout.id,
                    userId = workout.userId,
                    datetimeStart = workout.datetimeStart,
                    datetimeEnd = workout.datetimeEnd,
                    sport = workout.sport,
                    raster = workout.raster.map { it.toBigDoubleList() }
            )
        }
    }
}
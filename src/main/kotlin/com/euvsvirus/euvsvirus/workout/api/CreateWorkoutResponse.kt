package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.RasteredWorkout
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
        fun fromRasteredWorkout(rasteredWorkout: RasteredWorkout): CreateWorkoutResponse {
            with(rasteredWorkout) {
                return CreateWorkoutResponse(
                        id = id,
                        userId = userId,
                        datetimeStart = datetimeStart,
                        datetimeEnd = datetimeEnd,
                        sport = sport,
                        raster = raster.map { it.toDoubleList() }
                )
            }
        }
    }
}
package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.RasteredWorkout

data class WorkoutResponse(val id: String, val userId: String, val sport: String, val raster: List<List<Double>>) {
    companion object {
        fun fromRasteredWorkout(rasteredWorkout: RasteredWorkout): WorkoutResponse {
            return WorkoutResponse(
                    id = rasteredWorkout.id,
                    userId = rasteredWorkout.userId,
                    sport = rasteredWorkout.sport,
                    raster = rasteredWorkout.raster.map { it.toDoubleList() }
            )
        }
    }
}
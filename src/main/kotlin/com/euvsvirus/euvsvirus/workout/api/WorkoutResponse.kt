package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.domain.Workout

data class WorkoutResponse(val id: String, val userId: String, val sport: String, val raster: List<List<Double>>) {
    companion object {
        fun fromWorkout(workout: Workout): WorkoutResponse {
            return WorkoutResponse(
                    id = workout.id,
                    userId = workout.userId,
                    sport = workout.sport,
                    raster = workout.raster.map { it.toBigDoubleList() }
            )
        }
    }
}
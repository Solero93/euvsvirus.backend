package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.WorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.Workout
import org.springframework.stereotype.Service

@Service
class GetWorkouts {
    fun invoke(): WorkoutResponse {
        return WorkoutResponse(
                results = listOf(
                        Workout("RANDOM_ID", "RANDOM_SPORT", listOf(listOf(0.1f, 0.2f, 0.4f)))
                )
        )
    }
}
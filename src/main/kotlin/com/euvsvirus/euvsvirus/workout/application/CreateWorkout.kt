package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutResponse
import org.springframework.stereotype.Service

@Service
class CreateWorkout {
    fun invoke(createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse {
        return CreateWorkoutResponse(
                id = "RANDOM_ID",
                userId = "RANDOM_USER_ID",
                datetimeStart = createWorkoutRequest.datetimeStart,
                datetimeEnd = createWorkoutRequest.datetimeEnd,
                sport = createWorkoutRequest.sport,
                raster = listOf(listOf(0.1f, 0.2f, 0.4f))
        )
    }
}
package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.CreateWorkoutRepository
import org.springframework.stereotype.Repository

@Repository
class CreateWorkoutDoesNothing: CreateWorkoutRepository {
    override fun createWorkout(createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse {
        return CreateWorkoutResponse(
                id = "RANDOM_ID",
                userId = "RANDOM_USER_ID",
                datetimeStart = createWorkoutRequest.datetimeStart,
                datetimeEnd = createWorkoutRequest.datetimeEnd,
                sport = createWorkoutRequest.sport,
                raster = listOf(listOf(0.1, 0.2, 0.4))
        )
    }

}
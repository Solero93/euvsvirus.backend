package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.CreateWorkoutRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateWorkout (@Autowired private val createWorkoutRepository: CreateWorkoutRepository){
    fun invoke(createWorkoutRequest: CreateWorkoutRequest, userId: String): CreateWorkoutResponse {
        return CreateWorkoutResponse.fromWorkout(createWorkoutRepository.createWorkout(createWorkoutRequest, userId))
    }
}
package com.euvsvirus.euvsvirus.workout.domain

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutResponse

interface CreateWorkoutRepository {
    fun createWorkout(createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse
}
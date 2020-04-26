package com.euvsvirus.euvsvirus.workout.domain

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest

interface CreateWorkoutRepository {
    fun createWorkout(createWorkoutRequest: CreateWorkoutRequest): Workout
}
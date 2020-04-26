package com.euvsvirus.euvsvirus.workout.domain

import com.euvsvirus.euvsvirus.workout.api.GetWorkoutsRequest

interface GetWorkoutsRepository {
    fun getWorkouts(getWorkoutsRequest: GetWorkoutsRequest): List<Workout>
}
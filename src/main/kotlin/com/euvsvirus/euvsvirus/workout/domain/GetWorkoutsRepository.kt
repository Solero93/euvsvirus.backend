package com.euvsvirus.euvsvirus.workout.domain

interface GetWorkoutsRepository {
    fun getWorkouts(): List<Workout>
}
package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import com.euvsvirus.euvsvirus.workout.domain.Workout
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase
import org.springframework.stereotype.Repository

@Repository
class GetWorkoutsPostgres : GetWorkoutsRepository {
    override fun getWorkouts(): List<Workout> = WorkoutDatabase.getWorkouts()
}
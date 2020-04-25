package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import com.euvsvirus.euvsvirus.workout.domain.Workout
import org.springframework.stereotype.Repository

@Repository
class GetWorkoutsRandom : GetWorkoutsRepository {
    override fun getWorkouts(): List<Workout> = listOf(
            Workout("RANDOM_ID", "RANDOM_SPORT", "RANDOM_USER_ID", listOf(listOf(0.1f, 0.2f, 0.4f)))
    )
}
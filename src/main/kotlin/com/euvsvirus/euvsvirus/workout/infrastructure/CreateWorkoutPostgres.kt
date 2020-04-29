package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.domain.CreateWorkoutRepository
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CreateWorkoutPostgres: CreateWorkoutRepository {
    override fun createWorkout(createWorkoutRequest: CreateWorkoutRequest, userId: String): Workout {
        val workoutId = UUID.randomUUID().toString()
        val points = createWorkoutRequest.points.map { SphereCircle.fromNumbers(it[0], it[1], it[2]) }
        val workout = Workout(
                id = workoutId,
                userId = userId,
                datetimeStart = createWorkoutRequest.datetimeStart,
                datetimeEnd = createWorkoutRequest.datetimeEnd,
                sport = createWorkoutRequest.sport,
                points = points
        )
        WorkoutDatabase.createWorkout(workout)
        return workout
    }
}
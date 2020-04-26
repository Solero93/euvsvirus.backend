package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.domain.CreateWorkoutRepository
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import org.springframework.stereotype.Repository

@Repository
class CreateWorkoutDoesNothing: CreateWorkoutRepository {
    override fun createWorkout(createWorkoutRequest: CreateWorkoutRequest): Workout {
        return Workout(
                id = "RANDOM_ID",
                userId = "RANDOM_USER_ID",
                datetimeStart = createWorkoutRequest.datetimeStart,
                datetimeEnd = createWorkoutRequest.datetimeEnd,
                sport = createWorkoutRequest.sport,
                points = createWorkoutRequest.points.map { SphereCircle(it[0], it[1], it[2]) },
                raster = listOf(PointWithIntensity.fromNumbers(0.1, 0.2, 0.4))
        )
    }

}
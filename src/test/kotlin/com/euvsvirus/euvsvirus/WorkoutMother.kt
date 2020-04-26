package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase
import java.time.ZonedDateTime

object WorkoutMother {
    fun createDummyWorkout(userId: String): Workout {
        val workout = Workout(
                id = "random",
                datetimeStart = ZonedDateTime.now(),
                datetimeEnd = ZonedDateTime.now(),
                userId = userId,
                sport = "MY_SPORT",
                raster = listOf(PointWithIntensity.fromNumbers(0.4, 0.5, 100.0)),
                points = listOf(SphereCircle.fromNumbers(0.4, 0.5, 100.0))
        )
        WorkoutDatabase.createWorkout(workout)
        return workout
    }
}
package com.euvsvirus.euvsvirus

import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase
import java.time.ZonedDateTime
import java.util.*

object WorkoutMother {
    private val CURRENT_DATE = ZonedDateTime.now()
    fun createDummyWorkout(userId: String): Workout {
        val workout = Workout(
                id = UUID.randomUUID().toString(),
                datetimeStart = CURRENT_DATE.minusDays(1),
                datetimeEnd = CURRENT_DATE,
                userId = userId,
                sport = "MY_SPORT",
                points = listOf(SphereCircle.fromNumbers(0.4, 0.5, 100.0))
        )
        WorkoutDatabase.createWorkout(workout)
        return workout
    }

    fun createDummyWorkout2(userId: String): Workout {
        val workout = Workout(
                id = UUID.randomUUID().toString(),
                datetimeStart = CURRENT_DATE.minusHours(12),
                datetimeEnd = CURRENT_DATE.minusHours(36),
                userId = userId,
                sport = "MY_SPORT",
                points = listOf(SphereCircle.fromNumbers(0.4, 0.5, 100.0))
        )
        WorkoutDatabase.createWorkout(workout)
        return workout
    }
}
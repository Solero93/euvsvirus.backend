package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.Workout
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
class GetWorkoutsRandom : GetWorkoutsRepository {
    override fun getWorkouts(): List<Workout> = listOf(
            Workout(
                    id = "RANDOM_ID",
                    sport = "RANDOM_SPORT",
                    userId = "RANDOM_USER_ID",
                    datetimeStart = ZonedDateTime.now(),
                    datetimeEnd = ZonedDateTime.now(),
                    raster = listOf(PointWithIntensity.fromNumbers(0.1, 0.2, 0.4)),
                    points = listOf(SphereCircle.fromNumbers(0.1, 0.2, 1.0))
            )
    )
}
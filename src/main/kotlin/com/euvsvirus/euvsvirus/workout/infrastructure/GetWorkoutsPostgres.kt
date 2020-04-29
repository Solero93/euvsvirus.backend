package com.euvsvirus.euvsvirus.workout.infrastructure

import com.euvsvirus.euvsvirus.workout.api.GetWorkoutsRequest
import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.RasteredWorkout
import com.euvsvirus.euvsvirus.workout.infrastructure.database.WorkoutDatabase
import org.springframework.stereotype.Repository

@Repository
class GetWorkoutsPostgres : GetWorkoutsRepository {
    override fun getWorkouts(getWorkoutsRequest: GetWorkoutsRequest): List<RasteredWorkout> {
        val workouts = WorkoutDatabase.getWorkouts(getWorkoutsRequest)
        return workouts.map {
            RasteredWorkout(
                    id = it.id,
                    userId = it.userId,
                    datetimeStart = it.datetimeStart,
                    datetimeEnd = it.datetimeEnd,
                    sport = it.sport,
                    raster = it.points.map { point -> PointWithIntensity(point.center, 1.0) }
            )
        }
    }
}
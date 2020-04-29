package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutRequest
import com.euvsvirus.euvsvirus.workout.api.CreateWorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.CreateWorkoutRepository
import com.euvsvirus.euvsvirus.workout.domain.PointWithIntensity
import com.euvsvirus.euvsvirus.workout.domain.RasteredWorkout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateWorkout (@Autowired private val createWorkoutRepository: CreateWorkoutRepository){
    fun invoke(createWorkoutRequest: CreateWorkoutRequest, userId: String): CreateWorkoutResponse {
        val workout = createWorkoutRepository.createWorkout(createWorkoutRequest, userId)
        val rasteredWorkout = with (workout) {
            RasteredWorkout(
                id = id,
                    userId = userId,
                    datetimeStart = datetimeStart,
                    datetimeEnd = datetimeEnd,
                    sport = sport,
                    raster = points.map { point -> PointWithIntensity(point.center, 1.0) }
            )
        }
        return CreateWorkoutResponse.fromRasteredWorkout(rasteredWorkout)
    }
}
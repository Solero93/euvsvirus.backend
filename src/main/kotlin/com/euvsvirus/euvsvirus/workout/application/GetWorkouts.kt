package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.GetWorkoutsRequest
import com.euvsvirus.euvsvirus.workout.api.GetWorkoutsResponse
import com.euvsvirus.euvsvirus.workout.api.WorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetWorkouts @Autowired constructor(private val getWorkoutsRepository: GetWorkoutsRepository) {
    fun invoke(getWorkoutsRequest: GetWorkoutsRequest): GetWorkoutsResponse {
        return GetWorkoutsResponse(
                results = getWorkoutsRepository.getWorkouts(getWorkoutsRequest)
                        .map { WorkoutResponse.fromRasteredWorkout(it) }
        )
    }
}
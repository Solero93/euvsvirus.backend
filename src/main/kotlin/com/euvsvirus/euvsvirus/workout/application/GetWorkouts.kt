package com.euvsvirus.euvsvirus.workout.application

import com.euvsvirus.euvsvirus.workout.api.WorkoutResponse
import com.euvsvirus.euvsvirus.workout.domain.GetWorkoutsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GetWorkouts @Autowired constructor(val getWorkoutsRepository: GetWorkoutsRepository) {
    fun invoke(): WorkoutResponse {
        return WorkoutResponse(
                results = getWorkoutsRepository.getWorkouts()
        )
    }
}
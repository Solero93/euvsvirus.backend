package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.application.CreateWorkout
import com.euvsvirus.euvsvirus.workout.application.GetWorkouts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/workout"])
class WorkoutController @Autowired constructor(
        private val getWorkouts: GetWorkouts,
        private val createWorkout: CreateWorkout
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getWorkoutsEndpoint(): GetWorkoutsResponse = getWorkouts.invoke()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createWorkoutEndpoint(@RequestBody createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse = createWorkout.invoke(createWorkoutRequest)
}
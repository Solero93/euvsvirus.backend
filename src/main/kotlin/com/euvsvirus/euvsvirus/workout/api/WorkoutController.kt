package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.application.GetWorkouts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/workout"])
class WorkoutController @Autowired constructor(val getWorkouts: GetWorkouts){
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getWorkoutsEndpoint(): GetWorkoutsResponse = getWorkouts.invoke()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createWorkoutEndpoint(@RequestBody createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse {
        return CreateWorkoutResponse(
                id = "1",
                datetimeStart = createWorkoutRequest.datetimeStart,
                datetimeEnd = createWorkoutRequest.datetimeEnd,
                sport = createWorkoutRequest.sport,
                raster = listOf(listOf(0.1f, 0.2f, 0.4f))
        )
    }
}
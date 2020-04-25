package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.workout.application.GetWorkouts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/workout"])
class WorkoutController @Autowired constructor(val getWorkouts: GetWorkouts){
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getWorkoutsEndpoint(): WorkoutResponse = getWorkouts.invoke()
}
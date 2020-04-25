package com.euvsvirus.euvsvirus.api.workout

import com.euvsvirus.euvsvirus.domain.Workout
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/workout"])
class WorkoutController {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getWorkoutsEndpoint(): WorkoutResponse {
        return WorkoutResponse(
                results = listOf(
                        Workout("RANDOM_ID", "RANDOM_SPORT", listOf(listOf(0.1f, 0.2f, 0.4f)))
                )
        )
    }

}
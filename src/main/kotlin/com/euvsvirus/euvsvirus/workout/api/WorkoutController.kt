package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.user.application.AuthorizeUser
import com.euvsvirus.euvsvirus.workout.application.CreateWorkout
import com.euvsvirus.euvsvirus.workout.application.GetWorkouts
import com.euvsvirus.euvsvirus.workout.domain.SphereRectangle
import org.json.JSONArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@RequestMapping(path = ["/api/workout"])
class WorkoutController @Autowired constructor(
        private val getWorkouts: GetWorkouts,
        private val createWorkout: CreateWorkout,
        private val authorizeUser: AuthorizeUser
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getWorkoutsEndpoint(@RequestParam("bounds", required = false, defaultValue = "[[-1.0,-1.0],[-1.0,-1.0]]") bounds: String,
                            @RequestParam("datetime", required = false, defaultValue = "NOT_DEFINED") datetime: String): GetWorkoutsResponse {
        val jsonArray = JSONArray(bounds)
        val boundsToSend = listOf(
                listOf(jsonArray.getJSONArray(0).getDouble(0), jsonArray.getJSONArray(0).getDouble(1)),
                listOf(jsonArray.getJSONArray(1).getDouble(0), jsonArray.getJSONArray(1).getDouble(1))
        )
        return getWorkouts.invoke(GetWorkoutsRequest(bounds = SphereRectangle.fromList(boundsToSend), datetime = if (datetime == "NOT_DEFINED") null else ZonedDateTime.parse(datetime)))
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createWorkoutEndpoint(@RequestHeader(value = "Authorization", defaultValue = "Bearer -") authorization: String, @RequestBody createWorkoutRequest: CreateWorkoutRequest): CreateWorkoutResponse {
        val userId = authorizeUser.invoke(authorization)
        return createWorkout.invoke(createWorkoutRequest, userId)
    }
}
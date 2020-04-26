package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
import com.euvsvirus.euvsvirus.TokenMother
import com.euvsvirus.euvsvirus.UserMother
import com.euvsvirus.euvsvirus.workout.domain.SphereCircle
import com.euvsvirus.euvsvirus.workout.domain.SpherePoint
import org.assertj.core.api.SoftAssertions
import org.hamcrest.Matchers.hasSize
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [WorkoutController::class])
class CreateWorkoutTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `No token, no fun`() {
        val workoutRequest = JSONObject().apply {
            put("datetimeStart", "2007-11-03T16:18:05Z")
            put("datetimeEnd", "2007-11-03T18:18:05Z")
            put("sport", "RANDOM_SPORT")
            put("points", JSONArray(listOf(listOf(0.4f, 0.5f, 100f))))
        }

        mockMvc.perform(post("/api/workout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(workoutRequest.toString()))
                .andExpect(status().isForbidden)
    }

    @Test
    fun `Wrong token, no fun`() {
        val workoutRequest = JSONObject().apply {
            put("datetimeStart", "2007-11-03T16:18:05Z")
            put("datetimeEnd", "2007-11-03T18:18:05Z")
            put("sport", "RANDOM_SPORT")
            put("points", JSONArray(listOf(listOf(0.4f, 0.5f, 100f))))
        }

        mockMvc.perform(post("/api/workout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer WRONG_TOKEN")
                .content(workoutRequest.toString()))
                .andExpect(status().isForbidden)
    }

    @Test
    fun `When creating a workout, the created workout should be returned`() {
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)

        val workoutRequest = JSONObject().apply {
            put("datetimeStart", "2007-11-03T16:18:05Z")
            put("datetimeEnd", "2007-11-03T18:18:05Z")
            put("sport", "RANDOM_SPORT")
            put("points", JSONArray(listOf(listOf(0.4, 0.5, 100))))
        }

        mockMvc.perform(post("/api/workout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token")
                .content(workoutRequest.toString()))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id").isString)
                .andExpect(jsonPath("userId").isString)
                .andExpect(jsonPath("datetimeStart").value(workoutRequest.get("datetimeStart")))
                .andExpect(jsonPath("datetimeEnd").value(workoutRequest.get("datetimeEnd")))
                .andExpect(jsonPath("sport").value(workoutRequest.get("sport")))
                .andExpect(jsonPath("raster[0]", hasSize<List<String>>(3)))

    }

    @Test
    fun `When creating a workout with a single circle, the points of the raster must be contained in that circle`() {
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)
        val circle = listOf(0.0, 0.0, 100.0)

        val workoutRequest = JSONObject().apply {
            put("datetimeStart", "2007-11-03T16:18:05Z")
            put("datetimeEnd", "2007-11-03T18:18:05Z")
            put("sport", "RANDOM_SPORT")
            put("points", JSONArray(listOf(circle)))
        }

        val sentWorkout = JSONObject(mockMvc.perform(post("/api/workout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token")
                .content(workoutRequest.toString()))
                .andExpect(status().isOk)
                .andReturn().response.contentAsString)

        val raster: JSONArray = sentWorkout.getJSONArray("raster")
        val finalList = mutableListOf<List<Double>>()
        for (i in 0 until raster.length()) {
            val currentPoint = raster.getJSONArray(i)
            finalList.add(listOf(currentPoint.getDouble(0), currentPoint.getDouble(1), currentPoint.getDouble(2)))
        }
        SoftAssertions.assertSoftly { softly ->
            val sphereCircle = SphereCircle.fromNumbers(circle[0], circle[1], circle[2])
            finalList.forEach {
                val currentPoint = SpherePoint.fromNumbers(it[0], it[1])
                softly.assertThat(sphereCircle.isContained(currentPoint)).isTrue
                softly.assertThat(it[2]).isBetween(0.0, 1.0)
            }
        }
    }
}
package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.user.api.DatabaseCleaner
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
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
    fun `When creating a workout, the created workout should be returned`() {
        val user = DatabaseUser(
                id = "random",
                firstName = "Peter",
                lastName = "Parker",
                email = "peterparker@mail.com",
                password = "thisisasecret",
                avatarUrl = "randomUrl"
        )
        UserDatabase.storeUser(user)

        val loginRequest = JSONObject().apply {
            put("email", user.email)
            put("password", user.password)
        }

        val token = JSONObject(mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest.toString()))
                .andExpect(status().isOk)
                .andReturn().response.contentAsString).get("token")

        val workoutRequest = JSONObject().apply {
            put("datetimeStart", "2007-11-03T16:18:05Z")
            put("datetimeEnd", "2007-11-03T18:18:05Z")
            put("sport", "RANDOM_SPORT")
            put("points", JSONArray(listOf(listOf(0.4f, 0.5f, 100f))))
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
}
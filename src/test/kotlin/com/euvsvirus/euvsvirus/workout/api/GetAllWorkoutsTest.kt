package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
import com.euvsvirus.euvsvirus.TokenMother
import com.euvsvirus.euvsvirus.UserMother
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [WorkoutController::class])
class GetAllWorkoutsTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `When obtaining all the workouts, a list of workouts should be returned`() {
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)

        mockMvc.perform(get("/api/workout")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("results[0].id").isString)
                .andExpect(jsonPath("results[0].userId").isString)
                .andExpect(jsonPath("results[0].sport").isString)
                .andExpect(jsonPath("results[0].raster[0]", hasSize<List<String>>(3)))
    }
}
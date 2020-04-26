package com.euvsvirus.euvsvirus.workout.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
import com.euvsvirus.euvsvirus.TokenMother
import com.euvsvirus.euvsvirus.UserMother
import com.euvsvirus.euvsvirus.WorkoutMother
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
import java.time.format.DateTimeFormatter

@WebMvcTest(controllers = [WorkoutController::class])
class GetAllWorkoutsTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `When obtaining a list of workouts with bounding box, a list of workouts within that box should be returned`() {
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)
        val workout = WorkoutMother.createDummyWorkout(user.id)

        val bounds = listOf(listOf(0, 0.3), listOf(0.3, 0.1))

        mockMvc.perform(get("/api/workout?bounds=${bounds}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("results[0].id").isString)
                .andExpect(jsonPath("results[0].userId").isString)
                .andExpect(jsonPath("results[0].sport").isString)
                .andExpect(jsonPath("results[0].raster[0]", hasSize<List<String>>(3)))
    }

    @Test
    fun `When obtaining a list of workouts at a specific date, only those of that date should be returned`() {
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)
        val workout = WorkoutMother.createDummyWorkout(user.id)
        val workout2 = WorkoutMother.createDummyWorkout2(user.id)

        val datetime = workout.datetimeEnd.minusHours(1)

        mockMvc.perform(get("/api/workout?datetime=${datetime.format(DateTimeFormatter.ISO_INSTANT)}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("results", hasSize<List<Any>>(1)))
                .andExpect(jsonPath("results[0].id").isString)
                .andExpect(jsonPath("results[0].userId").isString)
                .andExpect(jsonPath("results[0].sport").isString)
                .andExpect(jsonPath("results[0].raster[0]", hasSize<List<String>>(3)))
    }

}
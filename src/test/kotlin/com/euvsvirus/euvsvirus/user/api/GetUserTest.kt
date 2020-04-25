package com.euvsvirus.euvsvirus.user.api

import org.hamcrest.Matchers.`is`
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController::class])
internal class GetUserTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `After creating a user, when getting the user from the system, the same user should be returned`() {
        val userRequest = JSONObject().apply {
            put("firstName", "Peter")
            put("lastName", "Parker")
            put("email", "peterparker@mail.com")
            put("password", "thisisasecret")
        }

        val response = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest.toString()))
                .andReturn().response.contentAsString
        val jsonResponse = JSONObject(response)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/${jsonResponse.get("id")}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id", `is`(jsonResponse.get("id"))))
                .andExpect(jsonPath("firstName", `is`(userRequest.get("firstName"))))
                .andExpect(jsonPath("lastName", `is`(userRequest.get("lastName"))))
                .andExpect(jsonPath("email", `is`(userRequest.get("email"))))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("avatarUrl").isString)
                .andExpect(jsonPath("token").doesNotExist())
    }
}
package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
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
internal class GetCurrentUserTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `Get current User should give information of the logged in user`() {
        val userRequest = JSONObject().apply {
            put("firstName", "Peter")
            put("lastName", "Parker")
            put("email", "peterparker@mail.com")
            put("password", "thisisasecret")
        }

        val createUserResponse = JSONObject(mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest.toString()))
                .andExpect(status().isOk)
                .andReturn().response.contentAsString)

        val loginRequest = JSONObject().apply {
            put("email", userRequest.get("email"))
            put("password", userRequest.get("password"))
        }

        val tokenJson = JSONObject(mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest.toString()))
                .andReturn().response.contentAsString)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/current")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer ${tokenJson.get("token")}"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id", `is`(createUserResponse.get("id"))))
                .andExpect(jsonPath("firstName", `is`(userRequest.get("firstName"))))
                .andExpect(jsonPath("lastName", `is`(userRequest.get("lastName"))))
                .andExpect(jsonPath("email", `is`(userRequest.get("email"))))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("avatarUrl", `is`(createUserResponse.get("avatarUrl"))))
                .andExpect(jsonPath("token").doesNotExist())
    }

    @Test
    fun `Get current User with incorrect token should return 403`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/current")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer RANDOM_TOKEN"))
                .andExpect(status().isForbidden)
    }
}
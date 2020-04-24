package com.euvsvirus.euvsvirus.api.usercontroller

import com.euvsvirus.euvsvirus.api.UserController
import org.hamcrest.Matchers.`is`
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController::class])
internal class CreateUserTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `when creating a user, the same user should be returned`() {
        val userRequest = JSONObject().apply {
            put("firstName", "Peter")
            put("lastName", "Parker")
            put("email", "peterparker@mail.com")
            put("password", "thisisasecret")
        }

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userRequest.toString()))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id").isString)
                .andExpect(jsonPath("firstName", `is`(userRequest.get("firstName"))))
                .andExpect(jsonPath("lastName", `is`(userRequest.get("lastName"))))
                .andExpect(jsonPath("email", `is`(userRequest.get("email"))))
                .andExpect(jsonPath("avatarUrl").isString)
                .andExpect(jsonPath("password").doesNotExist())
    }
}
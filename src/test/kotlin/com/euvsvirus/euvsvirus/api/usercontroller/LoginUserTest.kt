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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [UserController::class])
internal class LoginUserTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `When logging in with correct credentials, user information should be returned`() {
        val loginRequest = JSONObject().apply {
            put("email", "peterparker@mail.com")
            put("password", "thisisasecret")
        }

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("id").isString)
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").isString)
                .andExpect(MockMvcResultMatchers.jsonPath("lastName").isString)
                .andExpect(MockMvcResultMatchers.jsonPath("email", `is`(loginRequest.get("email"))))
                .andExpect(MockMvcResultMatchers.jsonPath("password").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("token").isString)
    }
}
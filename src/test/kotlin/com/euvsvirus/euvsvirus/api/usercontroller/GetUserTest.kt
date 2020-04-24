package com.euvsvirus.euvsvirus.api.usercontroller

import com.euvsvirus.euvsvirus.api.UserController
import org.hamcrest.Matchers
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [UserController::class])
internal class GetUserTest(@Autowired val mockMvc: MockMvc) {
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
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.`is`(jsonResponse.get("id"))))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName", Matchers.`is`(userRequest.get("firstName"))))
                .andExpect(MockMvcResultMatchers.jsonPath("lastName", Matchers.`is`(userRequest.get("lastName"))))
                .andExpect(MockMvcResultMatchers.jsonPath("email", Matchers.`is`(userRequest.get("email"))))
                .andExpect(MockMvcResultMatchers.jsonPath("password").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("token").doesNotExist())
    }
}
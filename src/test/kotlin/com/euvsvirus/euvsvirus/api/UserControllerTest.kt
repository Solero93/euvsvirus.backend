package com.euvsvirus.euvsvirus.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [UserController::class])
internal class UserControllerTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun createUserTest() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
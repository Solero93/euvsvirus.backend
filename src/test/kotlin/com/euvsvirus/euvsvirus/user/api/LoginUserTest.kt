package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.user.infrastructure.inmemorydatabase.UserDatabase
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
internal class LoginUserTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `When logging in with correct credentials, user information should be returned`() {
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

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(loginRequest.toString()))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id", `is`(user.id)))
                .andExpect(jsonPath("firstName", `is`(user.firstName)))
                .andExpect(jsonPath("lastName", `is`(user.lastName)))
                .andExpect(jsonPath("email", `is`(user.email)))
                .andExpect(jsonPath("avatarUrl", `is`(user.avatarUrl)))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("token").isString)
    }
}
package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.DatabaseCleaner
import com.euvsvirus.euvsvirus.TokenMother
import com.euvsvirus.euvsvirus.UserMother
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
        val user = UserMother.createPeterParkerUser()
        val token = TokenMother.getTokenForUser(user)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/${user.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $token"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("id").value(user.id))
                .andExpect(jsonPath("firstName").value(user.firstName))
                .andExpect(jsonPath("lastName").value(user.lastName))
                .andExpect(jsonPath("email").value(user.email))
                .andExpect(jsonPath("password").doesNotExist())
                .andExpect(jsonPath("avatarUrl").value(user.avatarUrl))
                .andExpect(jsonPath("token").doesNotExist())
    }
}
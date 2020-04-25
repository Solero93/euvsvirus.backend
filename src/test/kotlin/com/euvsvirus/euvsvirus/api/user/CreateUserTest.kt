package com.euvsvirus.euvsvirus.api.user

import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.DatabaseUser
import com.euvsvirus.euvsvirus.infrastructure.inmemorydatabase.UserDatabase
import org.assertj.core.api.SoftAssertions
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
internal class CreateUserTest(@Autowired val mockMvc: MockMvc) {
    @BeforeEach
    internal fun setUp() {
        DatabaseCleaner.clean()
    }

    @Test
    fun `when creating a user, it should be in database and the same user should be returned`() {
        val userRequest = JSONObject().apply {
            put("firstName", "Peter")
            put("lastName", "Parker")
            put("email", "peterparker@mail.com")
            put("password", "thisisasecret")
        }

        val createdUser = JSONObject(mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
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
                .andReturn().response.contentAsString)

        val userInDatabase: DatabaseUser = UserDatabase.getUser(createdUser.getString("id"))!!
        SoftAssertions.assertSoftly {
            it.assertThat(userInDatabase.id).isEqualTo(createdUser.getString("id"))
            it.assertThat(userInDatabase.firstName).isEqualTo(userRequest.getString("firstName"))
            it.assertThat(userInDatabase.lastName).isEqualTo(userRequest.getString("lastName"))
            it.assertThat(userInDatabase.email).isEqualTo(userRequest.getString("email"))
            it.assertThat(userInDatabase.password).isEqualTo(userRequest.getString("password"))
            it.assertThat(userInDatabase.avatarUrl).isEqualTo(createdUser.getString("avatarUrl"))
        }

    }
}
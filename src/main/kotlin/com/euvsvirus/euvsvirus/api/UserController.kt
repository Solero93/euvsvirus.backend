package com.euvsvirus.euvsvirus.api

import com.euvsvirus.euvsvirus.application.CreateUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserController(@Autowired val createUser: CreateUser) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createUserEndpoint(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse = createUser.invoke(createUserRequest)

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getUserEndpoint(@PathVariable("id") userId: String): GetUserResponse {
        return GetUserResponse(
                id = userId,
                firstName = "Peter",
                lastName = "Parker",
                email = "peterparker@mail.com",
                avatarUrl = "randomUrl"
        )
    }

    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun loginEndpoint(@RequestBody loginRequest: LoginUserRequest): LoginUserResponse {
        return LoginUserResponse(
                id = "randomId",
                firstName = "Peter",
                lastName = "Parker",
                email = loginRequest.email,
                token = "randomToken",
                avatarUrl = "randomUrl"
        )
    }
}
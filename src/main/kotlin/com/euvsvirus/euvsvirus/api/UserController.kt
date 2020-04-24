package com.euvsvirus.euvsvirus.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserController {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        with(createUserRequest) {
            return CreateUserResponse(
                    id = "randomId",
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    avatarUrl = "randomUrl"
            )
        }
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getUser(@PathVariable("id") userId: String): GetUserResponse {
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
    fun login(@RequestBody loginRequest: LoginUserRequest): LoginUserResponse {
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
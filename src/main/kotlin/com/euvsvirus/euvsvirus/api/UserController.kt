package com.euvsvirus.euvsvirus.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        path = ["/api/user"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
)
class UserController {
    @PostMapping
    @ResponseBody
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        with(createUserRequest) {
            return CreateUserResponse(
                    id = "randomId",
                    firstName = firstName,
                    lastName = lastName,
                    email = email
            )
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getUser(@PathVariable("id") userId: String): Unit {
    }
}
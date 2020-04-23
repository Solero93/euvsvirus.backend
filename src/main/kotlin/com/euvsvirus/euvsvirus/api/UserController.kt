package com.euvsvirus.euvsvirus.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path=["/api/user"], produces=[MediaType.APPLICATION_JSON_VALUE])
class UserController {

    @PostMapping
    @ResponseBody
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse {
        with(createUserRequest) {
            return CreateUserResponse(firstName, lastName, email, "thisisatoken")
        }
    }

}
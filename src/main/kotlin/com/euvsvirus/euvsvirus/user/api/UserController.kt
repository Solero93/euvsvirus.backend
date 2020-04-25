package com.euvsvirus.euvsvirus.user.api

import com.euvsvirus.euvsvirus.user.application.AuthorizeUser
import com.euvsvirus.euvsvirus.user.application.CreateUser
import com.euvsvirus.euvsvirus.user.application.GetUser
import com.euvsvirus.euvsvirus.user.application.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserController @Autowired constructor(
        val createUser: CreateUser,
        val getUser: GetUser,
        val loginUser: LoginUser,
        val authorizeUser: AuthorizeUser
) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createUserEndpoint(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse = createUser.invoke(createUserRequest)

    @GetMapping("/current", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getCurrentUserEndpoint(@RequestHeader(value="Authorization") authorization: String): GetUserResponse {
        return getUser.invoke(authorizeUser.invoke(authorization))
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getUserEndpoint(@PathVariable("id") userId: String): GetUserResponse = getUser.invoke(userId)

    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun loginEndpoint(@RequestBody loginRequest: LoginUserRequest): LoginUserResponse = loginUser.invoke(loginRequest)
}
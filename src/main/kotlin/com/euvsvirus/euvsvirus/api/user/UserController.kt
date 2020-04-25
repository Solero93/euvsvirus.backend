package com.euvsvirus.euvsvirus.api.user

import com.euvsvirus.euvsvirus.application.CreateUser
import com.euvsvirus.euvsvirus.application.GetUser
import com.euvsvirus.euvsvirus.application.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/user"])
class UserController @Autowired constructor (val createUser: CreateUser, val getUser: GetUser, val loginUser: LoginUser) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun createUserEndpoint(@RequestBody createUserRequest: CreateUserRequest): CreateUserResponse = createUser.invoke(createUserRequest)

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getUserEndpoint(@PathVariable("id") userId: String): GetUserResponse = getUser.invoke(userId)

    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun loginEndpoint(@RequestBody loginRequest: LoginUserRequest): LoginUserResponse = loginUser.invoke(loginRequest)
}
package com.euvsvirus.euvsvirus.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path=["/api/user"], produces=[MediaType.APPLICATION_JSON_VALUE])
class UserController {

    @PostMapping
    @ResponseBody
    fun createUser(): Unit {
    }

}
package com.euvsvirus.euvsvirus.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path=["/users"], produces=[MediaType.APPLICATION_JSON_VALUE])
class UserController {

    @GetMapping
    @ResponseBody
    fun getUsers(): Unit {
    }

}
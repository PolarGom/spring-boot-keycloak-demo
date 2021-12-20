package com.example.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/api")
class DemoController {

    @GetMapping("/anonymous")
    fun getAnonymous(): ResponseEntity<String> {

        return ResponseEntity.ok("Hello Anonymous")
    }

    @GetMapping("/user")
    fun getUser(): ResponseEntity<String> {

        return ResponseEntity.ok("Hello User")
    }

    @GetMapping("/admin")
    fun getAdmin(): ResponseEntity<String> {

        return ResponseEntity.ok("Hello Admin")
    }

    @GetMapping("/all-user")
    fun getAllUser(): ResponseEntity<String> {

        return ResponseEntity.ok("Hello All")
    }
}
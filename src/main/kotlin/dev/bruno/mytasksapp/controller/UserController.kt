package dev.bruno.mytasksapp.controller

import dev.bruno.mytasksapp.domain.model.dto.CreateUserDto
import dev.bruno.mytasksapp.domain.model.dto.GetUserDto
import dev.bruno.mytasksapp.domain.model.dto.UpdateUserDto
import dev.bruno.mytasksapp.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun register(@RequestBody createUserDto: CreateUserDto): ResponseEntity<Map<String, String>> {
        val user = userService.register(createUserDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to user.id))
    }

    @GetMapping("/{id}")
    fun getUserInfo(@PathVariable("id") userId: String): ResponseEntity<GetUserDto> {
        val user = userService.getById(userId)
        return ResponseEntity.ok(user)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") userId: String,
        @RequestBody updateUserDto: UpdateUserDto
    ): ResponseEntity<Unit> {
        userService.update(updateUserDto = updateUserDto, id = userId)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable("id") userId: String): ResponseEntity<Unit> {
        userService.remove(userId)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }
}
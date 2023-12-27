package dev.bruno.mytasksapp.domain.model.dto

data class CreateUserDto(
    val name: String,
    val email: String,
    val password: String
)

package dev.bruno.mytasksapp.domain.model.dto

import java.time.Instant

data class GetUserDto(
    val id: String,
    val name: String,
    val email: String,
    val createdAt: Instant?,
    val updateAt: Instant?
)
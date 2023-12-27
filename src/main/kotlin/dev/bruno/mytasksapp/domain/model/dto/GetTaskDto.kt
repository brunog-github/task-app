package dev.bruno.mytasksapp.domain.model.dto

import java.time.Instant

data class GetTaskDto(
    val id: String,
    val title: String,
    val description: String,
    val completed: Boolean,
    val createdAt: Instant?,
    val updateAt: Instant?
)
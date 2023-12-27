package dev.bruno.mytasksapp.domain.model.dto

data class UpdateTaskDto(
    val title: String,
    val description: String,
    val completed: Boolean = false
)
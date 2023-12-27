package dev.bruno.mytasksapp.domain

import java.time.Instant

data class ErrorResponse(
    val cause: String,
    val code: Int,
    val message: String,
    val timestamp: Instant
)

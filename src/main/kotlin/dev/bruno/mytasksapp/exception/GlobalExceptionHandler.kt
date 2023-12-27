package dev.bruno.mytasksapp.exception

import dev.bruno.mytasksapp.domain.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponse(
                    cause = HttpStatus.NOT_FOUND.reasonPhrase,
                    code = HttpStatus.NOT_FOUND.value(),
                    message = e.message.toString(),
                    timestamp = Instant.now()
                )
            )
    }

    @ExceptionHandler(IllegalAccessTaskException::class)
    fun handleIllegalAccessTaskException(e: IllegalAccessTaskException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                ErrorResponse(
                    cause = HttpStatus.CONFLICT.reasonPhrase,
                    code = HttpStatus.CONFLICT.value(),
                    message = e.message.toString(),
                    timestamp = Instant.now()
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun genericExceptionHandler(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    cause = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                    code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    message = e.message.toString(),
                    timestamp = Instant.now()
                )
            )
    }
}
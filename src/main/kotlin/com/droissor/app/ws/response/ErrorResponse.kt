package com.droissor.app.ws.response

import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val message: String
)

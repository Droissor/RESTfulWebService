package com.droissor.app.ws.response

data class UserResponse(
        val userId: Long,
        val name: String,
        val email: String,
        val password: String
)
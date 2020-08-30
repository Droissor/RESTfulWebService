package com.droissor.app.ws.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserRequest(
        @field:NotNull("userId cannot be null")
        val userId: Long,

        @field:NotBlank("name cannot be null or blank")
        val name: String,

        @field:NotNull("email cannot be null")
        val email: String,

        @field:NotNull("password cannot be null")
        val password: String
)
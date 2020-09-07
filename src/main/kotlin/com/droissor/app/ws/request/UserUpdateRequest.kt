package com.droissor.app.ws.request

import javax.validation.constraints.NotBlank

data class UserUpdateRequest(
        @field:NotBlank(message = "name cannot be null or blank")
        val name: String
)
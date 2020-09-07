package com.droissor.app.ws.exception

import com.droissor.app.ws.response.ErrorResponse
import com.droissor.app.ws.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [Exception::class])
    fun handleGenericException(exception: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(LocalDateTime.now(), exception.localizedMessage)

        return ResponseEntity(errorResponse, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [UserServiceException::class])
    fun handleUser(exception: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {

        val errorResponse = ErrorResponse(LocalDateTime.now(), exception.localizedMessage)

        return ResponseEntity(errorResponse, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }
}
package com.droissor.app.ws.controller

import com.droissor.app.ws.entity.User
import com.droissor.app.ws.request.UserCreateRequest
import com.droissor.app.ws.request.UserUpdateRequest
import com.droissor.app.ws.service.UserService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("users")
class UserController(val userService: UserService) {

    @GetMapping
    fun getUsers(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "limit", defaultValue = "50") limit: Int,
        @RequestParam(value = "sort", defaultValue = "desc", required = false) sort: String
    ) = "get users was called for page=$page, limit=$limit, sort=$sort"

    @GetMapping(path = ["/{userId}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun getUser(@PathVariable userId: String): ResponseEntity<User> =
        ResponseEntity.ok(userService.findUser(userId))

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun createUser(@Valid @RequestBody userUpdateRequest: UserCreateRequest): ResponseEntity<User> =
        ResponseEntity.ok(userService.createUser(userUpdateRequest))

    @PutMapping(
        path = ["/{userId}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun updateUser(@PathVariable userId: String, @Valid @RequestBody userUpdateRequest: UserUpdateRequest): ResponseEntity<User> =
        ResponseEntity.ok(userService.updateUser(userId, userUpdateRequest))

    @DeleteMapping(path = ["/{userId}"])
    fun deleteUser(@PathVariable userId: String): ResponseEntity<Unit> {
        userService.remove(userId)
        return ResponseEntity.noContent().build()
    }
}

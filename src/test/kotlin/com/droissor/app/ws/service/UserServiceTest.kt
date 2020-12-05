package com.droissor.app.ws.service

import com.droissor.app.ws.exception.UserServiceException
import com.droissor.app.ws.request.UserCreateRequest
import com.droissor.app.ws.request.UserUpdateRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserServiceTest {

    private val userService = UserService()

    @Test
    fun `test create user with success`() {

        val userCreateRequest = getUserCreateRequest()

        val result = userService.createUser(userCreateRequest)

        assertEquals(userCreateRequest.name, result.name)
        assertEquals(userCreateRequest.email, result.email)
        assertEquals(userCreateRequest.password, result.password)
        assertNotNull(result.userId)
    }

    @Test
    fun `test find user with success`() {

        val userCreateRequest = getUserCreateRequest()
        val expectedUser = userService.createUser(userCreateRequest)

        val result = userService.findUser(expectedUser.userId)

        assertEquals(expectedUser, result)
    }

    @Test
    fun `test update user with success`() {
        val userCreateRequest = getUserCreateRequest()
        val user = userService.createUser(userCreateRequest)

        val expectedUser = userService.updateUser(user.userId, UserUpdateRequest("New Name"))

        val result = userService.findUser(expectedUser.userId)

        assertEquals(expectedUser, result)
    }

    @Test
    fun `test remove user with success`() {
        val userCreateRequest = getUserCreateRequest()
        val user = userService.createUser(userCreateRequest)

        userService.remove(user.userId)

        assertThrows<UserServiceException> { userService.findUser(user.userId) }
    }

    @Test
    fun `test find user when not found`() {
        assertThrows<UserServiceException> { userService.findUser("") }
    }

    @Test
    fun `test update user when not found`() {
        assertThrows<UserServiceException> { userService.updateUser("", UserUpdateRequest("New Name")) }
    }

    @Test
    fun `test remove user when not found`() {
        assertThrows<UserServiceException> { userService.remove("") }
    }

    private fun getUserCreateRequest() = UserCreateRequest("TestUser", "testemail@emailserver.com", "123")

}
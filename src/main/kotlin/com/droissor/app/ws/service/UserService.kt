package com.droissor.app.ws.service

import com.droissor.app.ws.entity.User
import com.droissor.app.ws.exception.UserServiceException
import com.droissor.app.ws.request.UserCreateRequest
import com.droissor.app.ws.request.UserUpdateRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService {

    private val users = hashMapOf<String, User>()

    fun findUser(userId: String): User {
        users[userId]?.let { return it }
        throw UserServiceException(createUserNotFoundMessage(userId))
    }

    fun createUser(userCreateRequest: UserCreateRequest): User {
        val user = User(
                userId = UUID.randomUUID().toString(),
                name = userCreateRequest.name,
                email = userCreateRequest.email,
                password = userCreateRequest.password
        )

        users[user.userId] = user

        return user
    }

    fun updateUser(userId: String, userUpdateRequest: UserUpdateRequest): User {
        users[userId]?.let {
            val updatedUser = it.copy(name = userUpdateRequest.name)
            users[it.userId] = updatedUser
            return updatedUser
        }

        throw UserServiceException(createUserNotFoundMessage(userId))
    }

    fun remove(userId: String) = if (users.containsKey(userId)) users.remove(userId) else throw UserServiceException(createUserNotFoundMessage(userId))

    private fun createUserNotFoundMessage(userId: String) = "User not found for id:$userId"

}
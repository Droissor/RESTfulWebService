package com.droissor.app.ws.exception

import java.lang.RuntimeException

class UserServiceException(override val message: String) : RuntimeException(message)
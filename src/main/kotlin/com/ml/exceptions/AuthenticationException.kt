package com.ml.exceptions

import java.lang.RuntimeException

class AuthenticationException(msg: String, val errorCode: String): RuntimeException(msg) {
}

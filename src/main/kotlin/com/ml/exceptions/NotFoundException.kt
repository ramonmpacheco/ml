package com.ml.exceptions

import java.lang.RuntimeException

class NotFoundException(msg: String, val errorCode: String): RuntimeException(msg) {
}

package com.ml.exceptions

import java.lang.RuntimeException

class BadRequestException(msg: String, val errorCode: String): RuntimeException(msg) {
}

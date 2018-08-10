package com.bzawadzki.products.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(override var message:String) : RuntimeException(message)

package com.example.RestApi.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    val errorCode: String,
    override val message: String,
    val status: HttpStatus,
) : RuntimeException(message)

class PersonNotFoundException(id: Int) : BaseException(
    errorCode = "person.not.found",
    message = "Person with id = $id not found",
    status = HttpStatus.NOT_FOUND,
)
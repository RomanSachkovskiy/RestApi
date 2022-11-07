package com.example.RestApi.exception

data class ApiError(
    val message: String,
    val errorCode: String,
)

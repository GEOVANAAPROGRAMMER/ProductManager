package com.example.sprint3.models

data class LoginResponse(
    val userId: Long,
    val token: String,
    val message: String
)

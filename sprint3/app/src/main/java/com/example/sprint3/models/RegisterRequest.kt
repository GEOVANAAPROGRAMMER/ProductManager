package com.example.sprint3.models

data class RegisterRequest(
    val companyName: String,
    val email: String,
    val password: String
)
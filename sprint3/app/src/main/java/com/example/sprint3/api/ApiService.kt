package com.example.sprint3.api

import com.example.sprint3.models.LoginRequest
import com.example.sprint3.models.LoginResponse
import com.example.sprint3.models.RegisterRequest
import com.example.sprint3.models.RegisterResponse
import com.example.sprint3.models.ProductRequest
import com.example.sprint3.models.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/products")
    fun addProduct(@Body productRequest: ProductRequest): Call<ProductResponse>

    @GET("/api/products")
    fun getProducts(): Call<List<ProductResponse>>
}

package com.example.sprint3.api

import com.example.sprint3.models.Produto
import com.example.sprint3.models.Login
import com.example.sprint3.models.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/api/register")
    fun register(@Body registerRequest: Register): Call<String>

    @POST("/api/login")
    fun login(@Body loginRequest: Login): Call<String>

    @POST("/api/products")
    fun addProduct(@Body productRequest: Produto): Call<Unit>

    @GET("/api/products")
    fun getProducts(): Call<List<Produto>>
}

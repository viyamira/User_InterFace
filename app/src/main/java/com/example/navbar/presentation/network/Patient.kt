package com.example.navbar.presentation.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class Patient(
    val id: Int,
    val name: String,
    val room_number: Int,
)
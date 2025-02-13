package com.example.navbar.presentation.network
import com.example.navbar.presentation.network.Patient

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    @GET("api/patients/")
    fun getPatients(): Call<List<Patient>>

    @POST("api/patients/")
    fun addPatient(@Body patient: Patient): Call<Patient>

}

//object RetrofitClient {
//    private const val BASE_URL = "http://127.0.0.1" }

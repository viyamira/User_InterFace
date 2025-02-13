package com.example.navbar.presentation
import com.example.navbar.presentation.network.RetrofitClient
import com.example.navbar.presentation.network.Patient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.MaterialTheme

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                // Test API Call
                RetrofitClient.instance.getPatients().enqueue(object : Callback<List<Patient>> {
                    override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                        if (response.isSuccessful) {
                            val patients = response.body()
                            Log.d("API_TEST", "Patients: $patients")
                        } else {
                            Log.e("API_TEST", "Failed: ${response.errorBody()?.string()}")

                        }
                    }

                    override fun onFailure(call: Call<List<Patient>>, t: Throwable) {
                        Log.e("API_TEST", "Error: ${t.message}")
                    }
                })
            }
        }

        super.onCreate(savedInstanceState)
        setContent {
            // Apply the Wear OS Material Theme
            MaterialTheme {
                // Call the WearableNavigationBarExample composable
                WearableNavigationBarWithScreens()
            }
        }
    }
}

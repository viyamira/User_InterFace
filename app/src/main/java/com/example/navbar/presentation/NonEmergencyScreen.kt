package com.example.navbar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults

@Composable
fun NonEmergencyScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00B4D8)) // Blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Reduced spacing
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Water Button (Same Width as Others)
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Ensures uniform height
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(text = "Water", color = Color.Black)
            }

            // Row for Other & Bathroom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Matches Water button height
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Even spacing
            ) {
                // Other Button
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f), // Same width as Bathroom
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "Other", color = Color.Black)
                }

                // Bathroom Button
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f), // Same width as Other
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "Bathroom", color = Color.Black)
                }
            }
        }
    }
}

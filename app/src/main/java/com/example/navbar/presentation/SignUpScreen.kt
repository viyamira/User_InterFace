package com.example.navbar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavHostController) {
    var patientName by remember { mutableStateOf("") }
    var roomNumber by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val backgroundColor = Color(0xFFF5F5DC)  // Soft Beige for Comfort
    val buttonColor = Color(0xFF4682B4)  // Steel Blue for Better Contrast
    val buttonTextColor = Color.White  // White for High Contrast
    val textColor = Color.Black  // Dark Text for Readability

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .size(192.dp)
                    .background(backgroundColor)
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "New Patient Profile",
                        fontSize = 14.sp, // Slightly larger for readability
                        color = textColor,
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Name Input Field with Properly Visible Hint
                    OutlinedTextField(
                        value = patientName,
                        onValueChange = { patientName = it },
                        label = { Text("Name", fontSize = 12.sp, color = textColor) },
                        placeholder = { Text("Enter your name", fontSize = 12.sp, color = Color.Gray) }, // FIX: Hint Text Now Visible
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, color = textColor),
                        singleLine = true, // FIX: Prevents Overlapping Text
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)  // FIX: Larger Field for Visibility
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Room Number Input Field with Properly Visible Hint
                    OutlinedTextField(
                        value = roomNumber,
                        onValueChange = { roomNumber = it },
                        label = { Text("Room #", fontSize = 12.sp, color = textColor) },
                        placeholder = { Text("Enter room number", fontSize = 6.sp, color = Color.Gray) }, // FIX: Hint Text Now Visible
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 6.sp, color = textColor),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true, // FIX: Prevents Overlapping Text
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)  // FIX: Larger Field for Visibility
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                        // Login Button


                        // Sign Up Button

                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Signed Up  successfully!")
                                        }
                                    },
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),

                        ) {
                            Text(text = "Sign Up", fontSize = 10.sp, color = buttonTextColor)
                        }
                    }
                }

        }
    )
}
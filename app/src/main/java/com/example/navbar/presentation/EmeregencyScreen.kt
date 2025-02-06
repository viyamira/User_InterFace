package com.example.navbar.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import java.io.File

@Composable
fun EmergencyScreen(navController: NavHostController) {
    val context = LocalContext.current
    val audioFile = File(context.externalCacheDir, "emergency_message.3gp")
    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    // Permission Request Launcher
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Microphone permission denied!", Toast.LENGTH_SHORT).show()
        }
    }

    // Check if permission is granted
    val hasRecordPermission = ContextCompat.checkSelfPermission(
        context, Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffCC0000)), // Red background for Emergency
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Emergency Voice Message",
                color = Color.White
            )

            // Start Recording Button
            Button(
                onClick = {
                    if (hasRecordPermission) {
                        startRecording(audioFile, context) { recorder -> mediaRecorder = recorder }
                        isRecording = true
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                },
                enabled = !isRecording
            ) {
                Text("Start Recording")
            }

            // Stop Recording Button
            Button(
                onClick = {
                    stopRecording(mediaRecorder)
                    isRecording = false
                },
                enabled = isRecording
            ) {
                Text("Stop Recording")
            }

            // Play Recorded Message Button
            Button(
                onClick = {
                    if (audioFile.exists()) {
                        if (!isPlaying) {
                            playRecording(audioFile, context) { player -> mediaPlayer = player }
                            isPlaying = true
                        }
                    } else {
                        Toast.makeText(context, "No recording found!", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = !isRecording && !isPlaying
            ) {
                Text("Play Recording")
            }

            // Stop Playing Button
            Button(
                onClick = {
                    stopPlayback(mediaPlayer)
                    isPlaying = false
                },
                enabled = isPlaying
            ) {
                Text("Stop Playback")
            }
        }
    }
}

// Function to Start Recording
fun startRecording(audioFile: File, context: android.content.Context, onRecorderReady: (MediaRecorder) -> Unit) {
    try {
        val mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(audioFile.absolutePath)
            prepare()
            start()
        }
        onRecorderReady(mediaRecorder)
        Toast.makeText(context, "Recording Started", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error starting recording: ${e.message}", Toast.LENGTH_LONG).show()
    }
}

// Function to Stop Recording
fun stopRecording(mediaRecorder: MediaRecorder?) {
    try {
        mediaRecorder?.apply {
            stop()
            release()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Function to Play the Recorded Audio
fun playRecording(audioFile: File, context: android.content.Context, onPlayerReady: (MediaPlayer) -> Unit) {
    try {
        val mediaPlayer = MediaPlayer().apply {
            setDataSource(audioFile.absolutePath)
            prepare()
            start()
        }
        onPlayerReady(mediaPlayer)
        Toast.makeText(context, "Playing Recording", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error playing recording: ${e.message}", Toast.LENGTH_LONG).show()
    }
}

// Function to Stop Playback
fun stopPlayback(mediaPlayer: MediaPlayer?) {
    try {
        mediaPlayer?.apply {
            stop()
            release()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

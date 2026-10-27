package com.example.quadraticapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.quadraticapp.QuadraticSolverUI
import com.example.quadraticapp.ui.theme.QuadraticSolverComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticSolverComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    QuadraticSolverUI()
                }
            }
        }
    }
}

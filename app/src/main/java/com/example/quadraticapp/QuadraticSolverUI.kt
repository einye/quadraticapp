package com.example.quadraticapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quadraticapp.ui.theme.QuadraticSolverComposeTheme
import kotlin.math.sqrt

@Composable
fun QuadraticSolverUI() {
    var aText by remember { mutableStateOf("") }
    var bText by remember { mutableStateOf("") }
    var cText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quadratic Equation Solver",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Solve ax² + bx + c = 0",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        OutlinedTextField(
            value = aText,
            onValueChange = {
                aText = it
                errorMessage = ""
            },
            label = { Text("Coefficient a") },
            singleLine = true,
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bText,
            onValueChange = {
                bText = it
                errorMessage = ""
            },
            label = { Text("Coefficient b") },
            singleLine = true,
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cText,
            onValueChange = {
                cText = it
                errorMessage = ""
            },
            label = { Text("Coefficient c") },
            singleLine = true,
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val a = aText.toDoubleOrNull()
                val b = bText.toDoubleOrNull()
                val c = cText.toDoubleOrNull()

                result = ""
                errorMessage = ""

                if (a == null || b == null || c == null) {
                    errorMessage = "All inputs must be valid numbers."
                } else if (a == 0.0) {
                    errorMessage = "'a' cannot be zero. It's not a quadratic equation."
                } else {
                    val discriminant = b * b - 4 * a * c
                    result = when {
                        discriminant > 0 -> {
                            val root1 = (-b + sqrt(discriminant)) / (2 * a)
                            val root2 = (-b - sqrt(discriminant)) / (2 * a)
                            "Real and distinct roots:\nRoot 1 = $root1\nRoot 2 = $root2"
                        }
                        discriminant == 0.0 -> {
                            val root = -b / (2 * a)
                            "Real and equal roots:\nRoot = $root"
                        }
                        else -> {
                            val realPart = -b / (2 * a)
                            val imagPart = sqrt(-discriminant) / (2 * a)
                            "Complex roots:\nRoot 1 = $realPart + ${imagPart}i\nRoot 2 = $realPart - ${imagPart}i"
                        }
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Solve Equation")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (result.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF00000)),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = result,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuadraticSolverComposeTheme {
        QuadraticSolverUI()
    }
}

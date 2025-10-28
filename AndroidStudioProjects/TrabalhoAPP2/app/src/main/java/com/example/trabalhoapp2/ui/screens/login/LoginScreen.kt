// Em ui/screens/login/LoginScreen.kt
package com.example.trabalhoapp2.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.ui.theme.RicoGrayText
import com.example.trabalhoapp2.ui.theme.RicoOrange

@Composable
fun LoginScreen(navController: NavController) {
    var cpf by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A043C)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Olá, faça seu login", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("Digite seu CPF") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RicoOrange,
                    unfocusedBorderColor = RicoGrayText,
                    focusedLabelColor = RicoOrange,
                    unfocusedLabelColor = RicoGrayText
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)
            ) {
                Text("Acessar")
            }
        }
    }
}
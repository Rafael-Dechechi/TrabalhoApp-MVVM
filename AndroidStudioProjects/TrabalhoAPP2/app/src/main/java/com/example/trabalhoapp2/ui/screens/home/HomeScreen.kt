// Em ui/screens/home/HomeScreen.kt
package com.example.trabalhoapp2.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.ui.theme.RicoDarkBlue
import com.example.trabalhoapp2.ui.theme.RicoDarkBlueLight
import androidx.compose.foundation.layout.systemBarsPadding


@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RicoDarkBlue)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Column(
            modifier = Modifier
                .systemBarsPadding() // <-- ESTA É A CORREÇÃO
                .padding(16.dp),     // Seu padding original
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // 1. O Título
            Text(
                "Bem-vindo Rafael!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 2. Os Menus
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CardMenu(titulo = "Bolsa", modifier = Modifier.weight(1f)) { navController.navigate("bolsa") }
                    CardMenu(titulo = "Carteiras", modifier = Modifier.weight(1f)) { navController.navigate("carteira") }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CardMenu(titulo = "Banco", modifier = Modifier.weight(1f)) { navController.navigate("banco") }
                    CardMenu(titulo = "Configurações", modifier = Modifier.weight(1f)) { navController.navigate("config") }
                }
            }

            // 3. O Botão Sair
            Spacer(modifier = Modifier.height(32.dp))
            LogoutButton(navController = navController)
        }
    }
}

@Composable
fun CardMenu(titulo: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = RicoDarkBlueLight),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(titulo, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun LogoutButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = RicoDarkBlueLight),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Sair", color = Color.White)
    }
}
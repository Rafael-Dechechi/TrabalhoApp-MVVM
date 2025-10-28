// Em ui/screens/config/ConfigScreen.kt
package com.example.trabalhoapp2.ui.screens.config

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.ui.theme.RicoGrayText

@Composable
fun ConfigScreen(navController: NavController) {
    val opcoes = listOf(
        "Aprovações",
        "Ordens",
        "Preferências de notificação",
        "Central de Segurança",
        "Perfil de investimentos",
        "Imposto de renda",
        "Avisos e Regras",
        "Indique e ganhe"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A043C))
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text("Rafael", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Mais informações", color = RicoGrayText, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(opcoes) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {  },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E50))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item, color = Color.White, fontSize = 16.sp)
                        Text(">", color = RicoGrayText)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
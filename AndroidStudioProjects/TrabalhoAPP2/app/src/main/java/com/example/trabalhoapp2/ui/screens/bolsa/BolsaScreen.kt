// Em ui/screens/bolsa/BolsaScreen.kt
package com.example.trabalhoapp2.ui.screens.bolsa

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.ui.theme.RicoGreen
import com.example.trabalhoapp2.ui.theme.RicoRed
import androidx.compose.foundation.layout.systemBarsPadding

@Composable
fun BolsaScreen(navController: NavController) {
    val acoes = listOf(
        "BBSE3F" to "R$ 32,46 ▲0,43%",
        "ITSA4F" to "R$ 11,36 ▲1,97%",
        "CMIG4F" to "R$ 11,36 ▲1,52%",
        "BBAS3F" to "R$ 21,81 ▼0,59%",
        "KLBN4F" to "R$ 3,68 ▲0,55%"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A043C))
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text("Bolsa de Valores", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(acoes) { acao ->
                BolsaItem(nome = acao.first, valor = acao.second)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Voltar")
        }
    }
}

@Composable
fun BolsaItem(nome: String, valor: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E50))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(nome, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(valor, color = if (valor.contains("▲")) RicoGreen else RicoRed, fontSize = 16.sp)
        }
    }
}
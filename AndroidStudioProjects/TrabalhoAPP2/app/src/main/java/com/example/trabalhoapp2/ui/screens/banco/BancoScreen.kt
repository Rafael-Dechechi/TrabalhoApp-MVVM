// Em ui/screens/banco/BancoScreen.kt
package com.example.trabalhoapp2.ui.screens.banco

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.ui.theme.RicoGrayText
import com.example.trabalhoapp2.ui.theme.RicoOrange

@Composable
fun BancoScreen(navController: NavController) {
    val servicos = listOf("Consulta de boletos - DDA", "Configurações Pix", "Seguros")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A043C))
            .systemBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Rafael", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Saldo conta digital", color = Color.White, fontSize = 16.sp)
        Text("R$ 1.000.000,00", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)) {
                Text("Pix e Transferir")
            }
            Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))) {
                Text("Pagar")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E50)), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Cartão Rico", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Saiba como adquirir o cartão ideal", color = RicoGrayText)
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)) {
                    Text("Pedir cartão")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("Serviços", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            servicos.forEach { servico ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E50))
                ) {
                    Text(servico, color = Color.White, modifier = Modifier.padding(16.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
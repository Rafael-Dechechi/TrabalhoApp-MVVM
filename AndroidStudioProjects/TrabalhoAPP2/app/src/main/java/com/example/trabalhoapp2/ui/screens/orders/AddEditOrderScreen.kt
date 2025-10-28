// Em ui/screens/orders/AddEditOrderScreen.kt
package com.example.trabalhoapp2.ui.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.data.model.ValidTickers
import com.example.trabalhoapp2.ui.theme.RicoDarkBlue
import com.example.trabalhoapp2.ui.theme.RicoDarkBlueLight
import com.example.trabalhoapp2.ui.theme.RicoGrayText
import com.example.trabalhoapp2.ui.theme.RicoOrange

@Composable
fun AddEditOrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    orderId: Int? = null
) {
    val uiState = viewModel.uiState
    val title = if (uiState.isEditing) "Editar Ordem" else "Nova Ordem"

    // Efeito que roda UMA VEZ quando a tela abre:
    // Se orderId foi passado, manda o ViewModel carregar os dados.
    // Se não, manda o ViewModel limpar o formulário.
    LaunchedEffect(key1 = orderId) {
        if (orderId != null) {
            viewModel.loadOrderForEdit(orderId)
        } else {
            viewModel.resetOrderState()
        }
    }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RicoDarkBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))


        Text("Ação:", color = RicoGrayText, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(RicoDarkBlueLight)
                .clickable { expanded = true }
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(uiState.selectedTicker, color = Color.White)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ValidTickers.forEach { ticker ->
                    DropdownMenuItem(
                        text = { Text(ticker) },
                        onClick = {
                            viewModel.onTickerChange(ticker)
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tipo:", color = RicoGrayText, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf("COMPRA", "VENDA").forEach { type ->
                Button(
                    onClick = { viewModel.onTypeChange(type) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.selectedType == type) RicoOrange else RicoDarkBlueLight
                    )
                ) {
                    Text(type)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.quantityText,
            onValueChange = { viewModel.onQuantityChange(it) },
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = uiState.priceText,
            onValueChange = { viewModel.onPriceChange(it) },
            label = { Text("Preço Unitário (R$)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                if (viewModel.saveOrder()) {
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)
        ) {
            Text(if (uiState.isEditing) "Salvar Alterações (UPDATE)" else "Enviar Ordem (CREATE)")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = RicoDarkBlueLight)

        ) {
            Text("Cancelar")
        }
    }
}
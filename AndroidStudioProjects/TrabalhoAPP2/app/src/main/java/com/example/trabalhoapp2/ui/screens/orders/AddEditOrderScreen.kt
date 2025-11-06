// Em ui/screens/orders/AddEditOrderScreen.kt
package com.example.trabalhoapp2.ui.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabalhoapp2.data.model.ValidTickers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditOrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    orderId: Int?
) {
    // Observa o UiState do ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Carrega a ordem para edição (apenas uma vez)
    LaunchedEffect(key1 = orderId) {
        viewModel.loadOrderForEdit(orderId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (uiState.isEditing) "Editar Ordem" else "Adicionar Ordem") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.saveOrder()
                navController.popBackStack() // Volta para a tela anterior
            }) {
                Text("Salvar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Campo Ticker
            OutlinedTextField(
                value = uiState.selectedTicker,
                onValueChange = { viewModel.onTickerChange(it) },
                label = { Text("Ticker (ex: BBSE3F)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Quantidade
            OutlinedTextField(
                value = uiState.quantityText,
                onValueChange = { viewModel.onQuantityChange(it) },
                label = { Text("Quantidade") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Preço
            OutlinedTextField(
                value = uiState.priceText,
                onValueChange = { viewModel.onPriceChange(it) },
                label = { Text("Preço") },
                modifier = Modifier.fillMaxWidth()
            )

            // (Opcional: Adicionar seleção de TIPO de ordem - COMPRA/VENDA)
        }
    }
}
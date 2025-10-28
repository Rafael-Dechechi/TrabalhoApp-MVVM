// Em ui/screens/orders/OrdersScreen.kt
package com.example.trabalhoapp2.ui.screens.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoapp2.data.model.Order
import com.example.trabalhoapp2.ui.theme.*

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel
) {
    val orders by viewModel.orders.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RicoDarkBlue)
            .padding(16.dp)
    ) {
        Text("Gestão de Ordens (CRUD)", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("C: + Ordem | R: Lista | U: Lápis | D: Lixeira", color = RicoGrayText, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(orders, key = { it.id }) { order ->
                OrderItem(
                    order = order,
                    onEdit = {

                        navController.navigate("edit_order/${order.id}")
                    },
                    onDelete = {
                        viewModel.deleteOrder(order.id)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("add_order") }, // Apenas navega
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RicoOrange)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Nova Ordem (CREATE)")
        }

        Button(
            onClick = { navController.popBackStack() }, // Volta para a tela Carteira
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RicoDarkBlueLight)
        ) {
            Text("Voltar")
        }
    }
}

@Composable
fun OrderItem(
    order: Order,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    val isBuy = order.type == "COMPRA"
    val typeColor = if (isBuy) RicoGreen else RicoRed
    val statusColor = if (order.status == "Executada") RicoGrayText else RicoOrange

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = RicoDarkBlueLight)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(order.ticker, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(order.type, color = typeColor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("R$ ${String.format("%.2f", order.price)}", color = Color.White, fontSize = 16.sp)
                    Text("${order.quantity} unidades", color = RicoGrayText, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Status: ${order.status}", color = statusColor, fontSize = 12.sp)

                Row {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar Ordem",
                        tint = RicoGrayText,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onEdit(order.id) }
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir Ordem",
                        tint = RicoRed,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDelete(order.id) }
                    )
                }
            }
        }
    }
}
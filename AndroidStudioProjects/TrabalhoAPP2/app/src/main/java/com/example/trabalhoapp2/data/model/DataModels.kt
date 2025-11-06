// Em data/model/DataModels.kt
package com.example.trabalhoapp2.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "orders") // Define o nome da tabela
data class Order(
    @PrimaryKey(autoGenerate = true) // Chave primária autogerada
    val id: Int = 0, // Inicia com 0 para novos pedidos
    val ticker: String,
    val type: String,
    val quantity: Int,
    val price: Double,
    val status: String
)
data class PortfolioItem(
    val name: String,
    val percentage: Int,
    val value: String,
    val rentability: Double
)

data class ServiceItem(val name: String, val icon: ImageVector)


val ValidTickers = listOf("BBSE3F", "ITSA4F", "CMIG4F", "BBAS3F", "KLBN4F")


val ServiceIcons = mapOf(
    "Ordens" to Icons.Default.Receipt,
    "Aprovações" to Icons.Default.CheckCircle,
    "Controle de garantia" to Icons.Default.Bolt
)
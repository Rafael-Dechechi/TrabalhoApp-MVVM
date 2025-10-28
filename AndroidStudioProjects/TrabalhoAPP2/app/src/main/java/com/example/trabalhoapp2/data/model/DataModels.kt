// Em data/model/DataModels.kt
package com.example.trabalhoapp2.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.ui.graphics.vector.ImageVector

data class Order(
    val id: Int,
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
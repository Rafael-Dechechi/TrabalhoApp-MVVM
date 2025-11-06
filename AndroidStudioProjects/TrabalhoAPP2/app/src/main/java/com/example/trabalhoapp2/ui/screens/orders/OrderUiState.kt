// Em ui/screens/orders/OrderUiState.kt
package com.example.trabalhoapp2.ui.screens.orders // (Verifique este 'package')

/**
 * Esta é a data class em seu próprio arquivo.
 */
data class OrderUiState(
    val id: Int = 0,
    val selectedTicker: String = "",
    val selectedType: String = "COMPRA",
    val quantityText: String = "",
    val priceText: String = "",
    val isEditing: Boolean = false
)
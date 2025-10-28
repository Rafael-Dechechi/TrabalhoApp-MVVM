// Em ui/screens/orders/OrdersViewModel.kt
package com.example.trabalhoapp2.ui.screens.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalhoapp2.data.model.Order
import com.example.trabalhoapp2.data.model.ValidTickers
import com.example.trabalhoapp2.data.repository.OrderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

data class OrderUiState(
    val selectedTicker: String = ValidTickers.first(),
    val selectedType: String = "COMPRA",
    val quantityText: String = "",
    val priceText: String = "",
    val isEditing: Boolean = false,
    val orderId: Int? = null,
    val orderStatus: String = "Pendente"
)


class OrdersViewModel : ViewModel() {


    private val repository = OrderRepository


    val orders: StateFlow<List<Order>> = repository.orders
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    var uiState by mutableStateOf(OrderUiState())
        private set


    fun deleteOrder(id: Int) {
        repository.deleteOrder(id)
    }


    fun loadOrderForEdit(orderId: Int) {
        val order = repository.getOrderById(orderId)
        if (order != null) {
            uiState = OrderUiState(
                selectedTicker = order.ticker,
                selectedType = order.type,
                quantityText = order.quantity.toString(),
                priceText = order.price.toString(),
                isEditing = true,
                orderId = order.id,
                orderStatus = order.status
            )
        }
    }


    fun resetOrderState() {
        uiState = OrderUiState()
    }

    fun onTickerChange(ticker: String) { uiState = uiState.copy(selectedTicker = ticker) }
    fun onTypeChange(type: String) { uiState = uiState.copy(selectedType = type) }
    fun onQuantityChange(qty: String) { uiState = uiState.copy(quantityText = qty.filter { it.isDigit() }) }
    fun onPriceChange(price: String) { uiState = uiState.copy(priceText = price.filter { it.isDigit() || it == '.' }) }


    fun saveOrder(): Boolean {
        val qty = uiState.quantityText.toIntOrNull()
        val price = uiState.priceText.toDoubleOrNull()


        if (qty == null || price == null || qty <= 0 || price <= 0) {
            return false
        }

        if (uiState.isEditing && uiState.orderId != null) {

            val updatedOrder = Order(
                id = uiState.orderId!!,
                ticker = uiState.selectedTicker,
                type = uiState.selectedType,
                quantity = qty,
                price = price,
                status = uiState.orderStatus
            )
            repository.updateOrder(updatedOrder)

        } else {

            repository.addOrder(
                ticker = uiState.selectedTicker,
                type = uiState.selectedType,
                qty = qty,
                price = price
            )
        }

        resetOrderState()
        return true
    }
}
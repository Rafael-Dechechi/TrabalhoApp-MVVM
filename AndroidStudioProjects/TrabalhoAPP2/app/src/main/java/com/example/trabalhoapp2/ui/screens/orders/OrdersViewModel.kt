// Em ui/screens/orders/OrdersViewModel.kt
package com.example.trabalhoapp2.ui.screens.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trabalhoapp2.data.model.Order
import com.example.trabalhoapp2.data.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
// O import da sua nova classe será adicionado aqui automaticamente (ou adicione)
// import com.example.trabalhoapp2.ui.screens.orders.OrderUiState

/**
 * O ViewModel NÃO TEM MAIS a data class OrderUiState aqui dentro.
 */
class OrdersViewModel(private val repository: OrderRepository) : ViewModel() {

    // --- Parte 1: Para a Lista de Ordens (OrdersScreen) ---
    val orders: StateFlow<List<Order>> = repository.orders.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun deleteOrder(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOrder(id)
        }
    }

    // --- Parte 2: Para a Tela de Adicionar/Editar (AddEditOrderScreen) ---

    private val _uiState = MutableStateFlow(OrderUiState()) // Usa a classe do outro arquivo
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    // Funções chamadas pelos campos de texto
    fun onTickerChange(ticker: String) {
        _uiState.update { it.copy(selectedTicker = ticker) }
    }
    fun onTypeChange(type: String) {
        _uiState.update { it.copy(selectedType = type) }
    }
    fun onQuantityChange(quantity: String) {
        _uiState.update { it.copy(quantityText = quantity) }
    }
    fun onPriceChange(price: String) {
        _uiState.update { it.copy(priceText = price) }
    }

    // Chamado quando a tela de edição é aberta
    fun loadOrderForEdit(orderId: Int?) {
        if (orderId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val order = repository.getOrderById(orderId)
                if (order != null) {
                    _uiState.update {
                        it.copy(
                            id = order.id,
                            selectedTicker = order.ticker,
                            selectedType = order.type,
                            quantityText = order.quantity.toString(),
                            priceText = order.price.toString(),
                            isEditing = true
                        )
                    }
                }
            }
        } else {
            resetOrderState()
        }
    }

    // Reseta o formulário
    fun resetOrderState() {
        _uiState.value = OrderUiState()
    }

    // Salva (insere ou atualiza) a ordem
    fun saveOrder() {
        val currentState = _uiState.value
        val quantity = currentState.quantityText.toIntOrNull() ?: 0
        val price = currentState.priceText.toDoubleOrNull() ?: 0.0

        if (currentState.selectedTicker.isBlank() || quantity <= 0 || price <= 0) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            if (currentState.isEditing) {
                val updatedOrder = Order(
                    id = currentState.id,
                    ticker = currentState.selectedTicker,
                    type = currentState.selectedType,
                    quantity = quantity,
                    price = price,
                    status = "Pendente"
                )
                repository.updateOrder(updatedOrder)
            } else {
                repository.addOrder(
                    ticker = currentState.selectedTicker,
                    type = currentState.selectedType,
                    qty = quantity,
                    price = price
                )
            }
        }
    }
}

/**
 * A Fábrica.
 * Certifique-se de que esta é a ÚNICA definição dela.
 */
class OrdersViewModelFactory(
    private val repository: OrderRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrdersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrdersViewModel(repository) as T // <-- Corrigido
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
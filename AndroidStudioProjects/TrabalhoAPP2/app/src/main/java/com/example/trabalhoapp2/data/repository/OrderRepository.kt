// Em data/repository/OrderRepository.kt
package com.example.trabalhoapp2.data.repository

import com.example.trabalhoapp2.data.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object OrderRepository {

    private var nextId = 1
    private val _orders = MutableStateFlow(
        mutableListOf(
            Order(0, "BBSE3F", "COMPRA", 100, 32.00, "Executada"),
            Order(-1, "ITSA4F", "VENDA", 50, 11.50, "Pendente")
        )
    )
    val orders: Flow<List<Order>> = _orders.asStateFlow()

    fun addOrder(ticker: String, type: String, qty: Int, price: Double) {
        val newOrder = Order(nextId, ticker, type, qty, price, "Pendente")
        nextId++


        _orders.update { currentList ->
            (listOf(newOrder) + currentList).toMutableList()
        }
    }

    fun updateOrder(updatedOrder: Order) {
        _orders.update { currentList ->
            val index = currentList.indexOfFirst { it.id == updatedOrder.id }
            if (index != -1) {
                currentList[index] = updatedOrder
            }

            currentList.toMutableList()
        }
    }

    fun deleteOrder(id: Int) {
        _orders.update { currentList ->
            currentList.removeAll { it.id == id }

            currentList.toMutableList()
        }
    }

    fun getOrderById(id: Int): Order? {

        return _orders.value.find { it.id == id }
    }
}
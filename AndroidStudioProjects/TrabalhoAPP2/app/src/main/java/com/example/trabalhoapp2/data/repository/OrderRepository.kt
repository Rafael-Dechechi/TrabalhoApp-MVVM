// Em data/repository/OrderRepository.kt
package com.example.trabalhoapp2.data.repository

import com.example.trabalhoapp2.data.model.Order
import kotlinx.coroutines.flow.Flow

/**
 * Este é o código CORRETO com construtor primário.
 * class OrderRepository(private val orderDao: OrderDao)
 */
class OrderRepository(private val orderDao: OrderDao) {

    val orders: Flow<List<Order>> = orderDao.getAllOrders()

    suspend fun addOrder(ticker: String, type: String, qty: Int, price: Double) {
        val newOrder = Order(
            ticker = ticker,
            type = type,
            quantity = qty,
            price = price,
            status = "Pendente"
        )
        orderDao.insert(newOrder)
    }

    suspend fun updateOrder(updatedOrder: Order) {
        orderDao.update(updatedOrder)
    }

    suspend fun deleteOrder(id: Int) {
        orderDao.deleteById(id)
    }

    suspend fun getOrderById(id: Int): Order? {
        return orderDao.getOrderById(id)
    }
}
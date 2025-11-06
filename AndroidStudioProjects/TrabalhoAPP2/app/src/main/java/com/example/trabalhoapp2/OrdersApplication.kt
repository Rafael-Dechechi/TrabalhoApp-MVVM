// Em com/example/trabalhoapp2/OrdersApplication.kt
package com.example.trabalhoapp2

import android.app.Application
import com.example.trabalhoapp2.data.repository.AppDatabase
import com.example.trabalhoapp2.data.repository.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class OrdersApplication : Application() {

    // Um Scope de Coroutine para tarefas de background da aplicação
    val applicationScope = CoroutineScope(SupervisorJob())

    // Inicialização "lazy" (preguiçosa) do banco e do repositório
    // O 'scope' é passado para o banco para popular os dados iniciais
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val orderRepository by lazy { OrderRepository(database.orderDao()) }
}
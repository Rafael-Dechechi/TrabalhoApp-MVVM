package com.example.trabalhoapp2.data.repository // ou .data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trabalhoapp2.data.model.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    /**
     * Insere uma nova ordem. Se já existir, substitui.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order)

    /**
     * Atualiza uma ordem existente.
     */
    @Update
    suspend fun update(order: Order)

    /**
     * Deleta uma ordem específica pelo seu ID.
     */
    @Query("DELETE FROM orders WHERE id = :id")
    suspend fun deleteById(id: Int)

    /**
     * Busca uma ordem específica pelo seu ID.
     */
    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrderById(id: Int): Order?

    /**
     * Retorna todas as ordens da tabela, ordenadas pela mais recente.
     * Usa Flow para emitir automaticamente novas listas quando os dados mudam.
     */
    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getAllOrders(): Flow<List<Order>>
}
// Em data/repository/AppDatabase.kt
package com.example.trabalhoapp2.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.trabalhoapp2.data.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Order::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // --- ESTA É A MUDANÇA ---
        // Agora a função aceita (Context, CoroutineScope)
        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallback(scope)) // Adiciona o Callback
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Callback para pré-popular o banco
        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.orderDao())
                    }
                }
            }

            suspend fun populateDatabase(orderDao: OrderDao) {
                // Insere os dados iniciais
                orderDao.insert(Order(0, "BBSE3F", "COMPRA", 100, 32.00, "Executada"))
                orderDao.insert(Order(0, "ITSA4F", "VENDA", 50, 11.50, "Pendente"))
            }
        }
    }
}
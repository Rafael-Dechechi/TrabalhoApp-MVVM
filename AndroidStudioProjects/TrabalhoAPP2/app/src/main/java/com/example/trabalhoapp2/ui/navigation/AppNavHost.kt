package com.example.trabalhoapp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabalhoapp2.ui.screens.orders.AddEditOrderScreen
import com.example.trabalhoapp2.ui.screens.orders.OrdersScreen
import com.example.trabalhoapp2.ui.screens.orders.OrdersViewModel
import com.example.trabalhoapp2.ui.screens.banco.BancoScreen
import com.example.trabalhoapp2.ui.screens.bolsa.BolsaScreen
import com.example.trabalhoapp2.ui.screens.carteira.CarteiraScreen
import com.example.trabalhoapp2.ui.screens.config.ConfigScreen
import com.example.trabalhoapp2.ui.screens.home.HomeScreen
import com.example.trabalhoapp2.ui.screens.login.LoginScreen
import com.example.trabalhoapp2.OrdersApplication
import com.example.trabalhoapp2.ui.screens.orders.OrdersViewModelFactory


@Composable
fun AppNavHost() {
    val navController = rememberNavController()


    val application = (LocalContext.current.applicationContext as OrdersApplication)

    // 2. Crie a Factory, passando o repositório da Application
    val viewModelFactory = OrdersViewModelFactory(application.orderRepository)


    val ordersViewModel: OrdersViewModel = viewModel(factory = viewModelFactory)

    // --- FIM DA MUDANÇA ---

    NavHost(navController = navController, startDestination = "login") {

        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("bolsa") { BolsaScreen(navController) }
        composable("carteira") { CarteiraScreen(navController) }
        composable("config") { ConfigScreen(navController) }
        composable("banco") { BancoScreen(navController) }


        composable("orders_list") {
            OrdersScreen(
                navController = navController,
                viewModel = ordersViewModel // Agora ele é injetado corretamente
            )
        }
        composable("add_order") {
            AddEditOrderScreen(
                navController = navController,
                viewModel = ordersViewModel, // Injetado aqui também
                orderId = null
            )
        }
        composable("edit_order/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId")?.toIntOrNull()
            AddEditOrderScreen(
                navController = navController,
                viewModel = ordersViewModel, // E aqui também
                orderId = orderId
            )
        }
    }
}
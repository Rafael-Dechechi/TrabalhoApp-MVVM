// Em MainActivity.kt
package com.example.trabalhoapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.trabalhoapp2.ui.navigation.AppNavHost
import com.example.trabalhoapp2.ui.theme.TrabalhoAPP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoAPP2Theme {
                AppNavHost()
            }
        }
    }
}
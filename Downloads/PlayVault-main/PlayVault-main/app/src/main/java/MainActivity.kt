package com.playvault.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.playvault.navigation.AppNavGraph
import com.playvault.ui.design.PlayVaultTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            PlayVaultTheme {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    context = this
                )
            }
        }
    }
}

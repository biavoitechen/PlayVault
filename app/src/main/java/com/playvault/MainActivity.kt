package com.playvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.playvault.navigation.AppNavGraph
import com.playvault.ui.design.PlayVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayVaultTheme {
                val nav = rememberNavController()
                Scaffold {
                    AppNavGraph(navController = nav, context = applicationContext)
                }
            }
        }
    }
}

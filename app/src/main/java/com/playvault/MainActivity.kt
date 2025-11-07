package com.playvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
<<<<<<< HEAD
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.playvault.navigation.AppNavGraph
=======
import com.playvault.navigation.AppNav
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
import com.playvault.ui.design.PlayVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayVaultTheme {
<<<<<<< HEAD
                val nav = rememberNavController()
                Scaffold {
                    AppNavGraph(navController = nav, context = applicationContext)
                }
=======
                AppNav()
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
            }
        }
    }
}

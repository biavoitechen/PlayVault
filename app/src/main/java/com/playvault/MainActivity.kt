package com.playvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.playvault.navigation.AppNav
import com.playvault.ui.design.PlayVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayVaultTheme {
                AppNav()
            }
        }
    }
}

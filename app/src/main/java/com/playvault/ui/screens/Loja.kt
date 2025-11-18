package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.playvault.ui.components.ScreenScaffold

@Composable
fun LojaScreen(
    navController: NavHostController
) {
    ScreenScaffold(title = "Loja") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Loja de jogos")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("detalhe/1") }
            ) {
                Text(text = "Ver detalhes do jogo exemplo")
            }
        }
    }
}
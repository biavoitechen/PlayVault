package com.playvault.ui.screens

import androidx.compose.foundation.layout.*
<<<<<<< HEAD
import androidx.compose.material3.*
=======
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RowScope
import androidx.compose.material3.Text
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScreenScaffold(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            actions()
        }
        Spacer(Modifier.height(12.dp))
        content()
    }
}

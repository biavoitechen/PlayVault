package com.playvault.ui.design

<<<<<<< HEAD
import androidx.compose.material3.Shapes

val AppShapes = Shapes()
=======
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

fun pvShapes() = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
)
>>>>>>> 60b768c8855b23a486de5187baddd325ccf00000

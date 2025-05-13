package com.example.hitech.features.home.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CloudDecoration(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "cloudMove")
    val offsetX by infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = 12.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cloudOffset"
    )

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Cloud(
            modifier = Modifier
                .offset(x = offsetX, y = 24.dp)
                .align(Alignment.TopStart)
        )
        Cloud(
            modifier = Modifier
                .offset(x = -offsetX, y = 0.dp)
                .align(Alignment.TopEnd)
        )
    }
}
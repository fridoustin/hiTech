package com.example.hitech.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Cloud(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .graphicsLayer {
                alpha = 0.5f // efek transparan
            }
    ) {
        // Lingkaran utama
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, shape = CircleShape)
                .align(Alignment.CenterStart)
        )
        // Lingkaran tambahan
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.White, shape = CircleShape)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color.White, shape = CircleShape)
                .align(Alignment.CenterEnd)
        )
    }
}
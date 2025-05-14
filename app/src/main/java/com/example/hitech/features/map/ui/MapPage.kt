package com.example.hitech.features.map.ui

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitech.R
import kotlinx.coroutines.launch

// Data class for destination items
data class Destination(
    val id: Int,
    val name: String,
    val imageRes: Int
)

@Composable
fun MapPage() {
    val destinations = remember {
        listOf(
            Destination(1, "Dalila Routes", R.drawable.tongkonan),
            Destination(2, "Riyadh", R.drawable.tongkonan),
            Destination(3, "Diriyah", R.drawable.tongkonan),
            Destination(4, "Jazan", R.drawable.tongkonan),
            Destination(5, "Yanbu", R.drawable.tongkonan),
            Destination(6, "AlUla", R.drawable.tongkonan),
            Destination(7, "Madinah", R.drawable.tongkonan),
            Destination(8, "Al Ahsa", R.drawable.tongkonan),
            Destination(9, "Dammam", R.drawable.tongkonan),
            Destination(10, "Hail", R.drawable.tongkonan)
        )
    }

    // Bottom sheet states
    val bottomSheetCollapsedHeight = 300.dp
    val bottomSheetExpandedHeight = 1200.dp

    var bottomSheetExpanded by remember { mutableStateOf(false) }

    val bottomSheetHeight by animateDpAsState(
        targetValue = if (bottomSheetExpanded) bottomSheetExpandedHeight else bottomSheetCollapsedHeight,
        animationSpec = tween(300)
    )

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // This is where you would integrate Google Maps
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))
        ) {
            // Placeholder for Google Maps
            Text(
                "Google Maps API will be integrated here",
                modifier = Modifier.align(Alignment.Center),
                color = Color.Gray
            )
        }

        // Top Bar
        TopAppBar(
            modifier = Modifier.padding(top = 16.dp)
        )

        // Bottom Sheet
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSheet(
                expanded = bottomSheetExpanded,
                onExpandChange = { bottomSheetExpanded = it },
                height = bottomSheetHeight,
                destinations = destinations
            )
        }
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Category tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryTab("Accommodation", true)
            CategoryTab("Events", false)
            CategoryTab("Experiences", false)
        }
    }
}

@Composable
fun CategoryTab(text: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun BottomSheet(
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    height: Dp,
    destinations: List<Destination>
) {
    val dragSensitivity = 100f
    var dragOffset by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (dragOffset < -dragSensitivity && !expanded) {
                            onExpandChange(true)
                        } else if (dragOffset > dragSensitivity && expanded) {
                            onExpandChange(false)
                        }
                        dragOffset = 0f
                    },
                    onDragCancel = {
                        dragOffset = 0f
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        dragOffset += dragAmount.y
                    }
                )
            }
    ) {
        Column {
            // Handle indicator
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.LightGray)
            )

            // Header
            if (expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .clickable { onExpandChange(false) }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Explore Destinations",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            } else {
                Text(
                    text = "Explore Destinations",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // Destinations Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(destinations) { destination ->
                    DestinationCard(destination)
                }
            }
        }
    }
}

@Composable
fun DestinationCard(destination: Destination) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .clickable { /* Handle click */ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDDDDDD))
        ) {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            // Destination name overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = destination.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }


    }
}

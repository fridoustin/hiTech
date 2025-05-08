package com.example.hitech.features.home.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import com.example.hitech.R

@Composable
fun HomePage() {
    val skyBlueLight = Color(0xFF87CEEB)
    val skyBlueDark = Color(0xFFFFFFFF)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            skyBlueLight,
            Color(0xFFFFFFFF),
            skyBlueDark)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent, // Make scaffold transparent to show background
            bottomBar = { CustomBottomBar() }
        ) { paddingValues ->
            HomeContent(paddingValues)
        }

        // Cloud decorations - these would be replaced with actual cloud images in a real app
        CloudDecoration(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 60.dp, start = 20.dp)
        )
    }
}

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
                .offset(x = offsetX, y = 12.dp)
                .align(Alignment.TopStart)
        )
        Cloud(
            modifier = Modifier
                .offset(x = -offsetX, y = 64.dp)
                .align(Alignment.TopEnd)
        )
    }
}

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

@Composable
private fun HomeContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderSection()
        SearchBarSection()
        ActivityCategoriesSection()
        RecommendedSection()

        // Add extra padding at the bottom
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello, Traveler!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 80.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = "Ready to begin your next adventure?",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))
    }
}


@Composable
private fun SearchBarSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Search your place",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
private fun ActivityCategoriesSection() {
    // Section header
    Text(
        text = "Browse by activity",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
    )

    // List of activity categories
    val categories = listOf(
        ActivityCategory("Hiking", Color(0xFF1FCBF1)),
        ActivityCategory("Biking", Color(0xFFFFA500)),
        ActivityCategory("Climbing", Color(0xFF6C9BC7)),
        ActivityCategory("Running", Color(0xFFE9967A)),
        ActivityCategory("Jumping", Color(0xFF99CC66))
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(21.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { category ->
            ActivityCategoryItem(category)
        }
    }
}

@Composable
private fun ActivityCategoryItem(category: ActivityCategory) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(60.dp)
    ) {
        // In a real app, use actual images instead of colored circles
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(category.backgroundColor)
        ) {
            // This would be replaced with an actual image
            // For example: Image(painter = ..., contentDescription = category.name)
        }

        Text(
            text = category.name,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black
        )
    }
}

@Composable
private fun RecommendedSection() {
    // Section header with "See all" button
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recommended",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("See all", color = Color.Black)
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "See all",
                tint = Color.Black
            )
        }
    }

    // List of recommended activities
    val recommendations = listOf(
        Recommendation("Hill Climbing", "San Francisco"),
        Recommendation("Hill Climbing", "San Francisco")
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        recommendations.forEach { recommendation ->
            RecommendationItem(
                recommendation,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun RecommendationItem(recommendation: Recommendation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Image area (60% of card height)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .background(Color(0xFFE0F7FA))
            ) {
                // This would be replaced with an actual image in a real app
                // Image(
                //     painter = painterResource(id = R.drawable.hill_climbing),
                //     contentDescription = recommendation.activityName,
                //     contentScale = ContentScale.Crop,
                //     modifier = Modifier.fillMaxSize()
                // )

                // Favorite button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.8f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Color(0xFFFF6B6B),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Info area (40% of card height)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .padding(12.dp)
            ) {
                Text(
                    text = recommendation.activityName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = recommendation.location,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }

                // Travelled icons - these would be replaced with actual icons/images
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Travelled",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    // User icons
                    Row {
                        for (i in 0..2) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(end = if (i < 2) 4.dp else 0.dp)
                                    .clip(CircleShape)
                                    .background(when (i) {
                                        0 -> Color(0xFF64B5F6)
                                        1 -> Color(0xFFFFB74D)
                                        else -> Color(0xFF81C784)
                                    })
                            )
                        }
                    }
                }
            }
        }
    }
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavBar() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home - Selected
            BottomNavItemComponent(
                icon = Icons.Default.Home,
                title = "Home",
                isSelected = true
            )

            // Map/Search
            BottomNavItemComponent(
                icon = Icons.Default.Search,
                title = "Search",
                isSelected = false
            )

            // Profile
            BottomNavItemComponent(
                icon = Icons.Default.Person,
                title = "Profile",
                isSelected = false
            )

            // Bookmarks
            BottomNavItemComponent(
                icon = Icons.Default.Settings,
                title = "Saved",
                isSelected = false
            )
        }
    }
}

@Composable
private fun BottomNavItemComponent(icon: ImageVector, title: String, isSelected: Boolean) {
    val background = if (isSelected) Color(0xFF1976D2) else Color.Transparent
    val contentColor = if (isSelected) Color.White else Color.Gray

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .then(
                if (isSelected) {
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(background)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                } else {
                    Modifier
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )

            if (isSelected) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = title,
                    color = contentColor,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun CustomBottomBar() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
    ) {
        // Bottom bar background dengan shadow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(4.dp, RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(Color.White)
                .align(Alignment.BottomCenter)
        )

        // Layout untuk item menu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = Icons.Default.Home, // Ganti dengan resource icon Anda
                text = "Home",
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )

            BottomBarItem(
                icon = Icons.Default.Search, // Ganti dengan resource icon Anda
                text = "Explore",
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )

            // Spacer untuk tombol tengah
            Spacer(modifier = Modifier.width(80.dp))

            BottomBarItem(
                icon = Icons.Default.Star, // Ganti dengan resource icon Anda
                text = "Challenge",
                selected = selectedTab == 3,
                onClick = { selectedTab = 3 }
            )

            BottomBarItem(
                icon = Icons.Default.Person, // Ganti dengan resource icon Anda
                text = "Profile",
                selected = selectedTab == 4,
                onClick = { selectedTab = 4 }
            )
        }

        // Tombol tengah yang menonjol
        Box(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-20).dp)
                .zIndex(1f)
                .shadow(6.dp, CircleShape)
                .clip(CircleShape)
                .background(Color(0xFF8BC34A)) // Warna hijau
                .clickable { selectedTab = 2 }
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            // Ikon bintang putih
            Icon(
                painter = painterResource(id = R.drawable.chatbot), // Ganti dengan resource icon Anda
                contentDescription = "Action",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun BottomBarItem(
    icon: ImageVector, // Resource ID untuk icon
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 8.dp)
    ) {
        // Indikator hijau di atas ikon jika terpilih
        if (selected) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(1.5.dp))
                    .background(Color(0xFF8BC34A))
                    .padding(bottom = 8.dp)
            )
        } else {
            // Spacer untuk menjaga layout tetap konsisten
            Spacer(
                modifier = Modifier
                    .width(24.dp)
                    .height(3.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = if (selected) Color(0xFF8BC34A) else Color.LightGray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
            color = if (selected) Color(0xFF8BC34A) else Color.LightGray,
            textAlign = TextAlign.Center
        )
    }
}

// Data classes
data class ActivityCategory(
    val name: String,
    val backgroundColor: Color
)

data class Recommendation(
    val activityName: String,
    val location: String
)
package com.example.hitech.features.home.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import com.example.hitech.features.home.components.RecommendationItem
import com.example.hitech.features.home.components.SearchBar
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import com.example.hitech.R
import com.example.hitech.features.home.components.CloudDecoration
import com.example.hitech.features.home.components.DestinationCarousel
import com.example.hitech.features.home.data.Carousel
import com.example.hitech.features.home.data.Recommendation

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
        Box(){
            CloudDecoration(
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            HeaderSection()

        }
        val destinations = listOf(
            Carousel("Tongkonan","Kete Kesu, Tana Toraja", "Wisata", R.drawable.tongkonan),
            Carousel("Tongkonan","Kete Kesu, Tana Toraja", "Wisata", R.drawable.tongkonan),
            Carousel("Tongkonan","Kete Kesu, Tana Toraja", "Wisata", R.drawable.tongkonan),
            Carousel("Tongkonan","Kete Kesu, Tana Toraja", "Wisata", R.drawable.tongkonan)
        )
        val recommendations = listOf(
            Recommendation("Kete Kesu", "Tana Toraja", R.drawable.tongkonan),
            Recommendation("Kete Kesu", "Tana Toraja", R.drawable.tongkonan),
        )

        SearchBar()
        Spacer(modifier = Modifier.padding(top = 8.dp))
        DestinationCarousel(destinations = destinations)
        ActivityCategoriesSection()
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
            text = "Ready to explore South Sulawesi?",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        categories.forEach{ category ->
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
                    .background(Color(0xFF87CEEB))
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
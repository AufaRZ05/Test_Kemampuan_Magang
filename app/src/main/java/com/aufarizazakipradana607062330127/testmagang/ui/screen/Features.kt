package com.aufarizazakipradana607062330127.testmagang.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.testmagang.R
import com.aufarizazakipradana607062330127.testmagang.data.AuthRepository
import com.aufarizazakipradana607062330127.testmagang.ui.theme.TestMagangTheme

// Data class untuk setiap item fitur (tidak ada perubahan)
data class FeatureItem(
    val imageResId: Int,
    val title: String
)

@Composable
fun Features(navController: NavController) { // <-- UBAH NAMA FUNGSI COMPOSE KE "Features"
    // Ambil username dari AuthRepository
    val username = AuthRepository.loggedInUser?.username ?: "Pengguna" // Default "Pengguna" jika tidak ada user login

    // Daftar fitur yang akan ditampilkan (tidak ada perubahan)
    val features = remember {
        listOf(
            FeatureItem(R.drawable.lucky_draw, "Lucky Draw"),
            FeatureItem(R.drawable.book_test_drive, "Book Test Drive"),
            FeatureItem(R.drawable.book_service, "Book Service"),
            FeatureItem(R.drawable.ths, "THS"),
            FeatureItem(R.drawable.catalog, "Catalog"),
            FeatureItem(R.drawable.calculator, "Calculator"),
            FeatureItem(R.drawable.claim_insurance, "Claim Insurance"),
            FeatureItem(R.drawable.service_berkala, "Service Berkala"),
            FeatureItem(R.drawable.tips_and_trick, "Tips & Trick"),
            FeatureItem(R.drawable.trade_in, "Trade In"),
            FeatureItem(R.drawable.dealer_location, "Dealer Location")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello, $username",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Tambahkan Spacer tambahan untuk jarak lebih jauh
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(80.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "160",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Kumpulkan Koin Hasjrat",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp)) // Jarak antara Card dengan grid fitur

        LazyVerticalGrid(
            columns = GridCells.Fixed(4), // 4 kolom
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(features) { item ->
                FeatureCard(item = item)
            }
        }
    }
}

@Composable
fun FeatureCard(item: FeatureItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.title,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            lineHeight = 12.sp
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun FeaturesPreview() { // <-- UBAH NAMA FUNGSI PREVIEW KE "FeaturesPreview"
    TestMagangTheme {
        Features(navController = rememberNavController())
    }
}
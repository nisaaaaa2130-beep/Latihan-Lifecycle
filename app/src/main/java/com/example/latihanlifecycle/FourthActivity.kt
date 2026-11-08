package com.example.latihanlifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FourthActivity : ComponentActivity() {

    companion object {
        const val EXTRA_NAMA_HEWAN = "extra_nama_hewan"
        const val EXTRA_KATEGORI_HEWAN = "extra_kategori_hewan"
        const val EXTRA_IMAGE_RES = "extra_image_res"
        const val EXTRA_HABITAT = "extra_habitat"
        const val EXTRA_MAKANAN = "extra_makanan"
        const val EXTRA_BERKEMBANG = "extra_berkembang"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val namaHewan = intent.getStringExtra(EXTRA_NAMA_HEWAN) ?: "Hewan"
        val kategoriHewan = intent.getStringExtra(EXTRA_KATEGORI_HEWAN) ?: "-"
        val imageRes = intent.getIntExtra(EXTRA_IMAGE_RES, R.drawable.jaguar)
        val habitat = intent.getStringExtra(EXTRA_HABITAT) ?: "-"
        val makanan = intent.getStringExtra(EXTRA_MAKANAN) ?: "-"
        val berkembang = intent.getStringExtra(EXTRA_BERKEMBANG) ?: "-"
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI) ?: "-"
        val user = intent.getStringExtra(EXTRA_USER) ?: "Pengunjung"

        setContent {
            val forestGreenDark = Color(0xFF1B5E20)
            val forestGreenLight = Color(0xFF2E7D32)

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(forestGreenDark, forestGreenLight, Color(0xFF0D3B12))
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Tombol Kembali
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Button(
                                onClick = { finish() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                            ) {
                                Text("← Kembali ke Daftar", color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Foto Utama Hewan
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = namaHewan,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Card Informasi Detail
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xEEFFFFFF))
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = namaHewan,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20)
                                )
                                Text(
                                    text = "Kategori: $kategoriHewan",
                                    fontSize = 14.sp,
                                    color = Color(0xFF388E3C),
                                    fontWeight = FontWeight.SemiBold
                                )

                                Divider(modifier = Modifier.padding(vertical = 12.dp))

                                DetailInfoItem(label = "🌍 Habitat / Asal", value = habitat)
                                DetailInfoItem(label = "🥩 Jenis Makanan", value = makanan)
                                DetailInfoItem(label = "🐣 Perkembangbiakan", value = berkembang)

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "📖 Deskripsi:",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1B5E20),
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = deskripsi,
                                    fontSize = 14.sp,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Dilihat oleh: $user | Ensiklopedia Hutan Rimba",
                            fontSize = 12.sp,
                            color = Color(0xFFC8E6C9)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailInfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF2E7D32))
        Text(text = value, fontSize = 14.sp, color = Color.Black)
    }
}
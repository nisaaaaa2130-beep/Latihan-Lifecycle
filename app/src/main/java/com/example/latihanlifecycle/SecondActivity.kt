package com.example.latihanlifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

// Model data untuk Kategori
data class KategoriHewan(
    val namaKategori: String,
    val deskripsiSingkat: String,
    val imageRes: Int
)

class SecondActivity : ComponentActivity() {

    companion object {
        const val EXTRA_KATEGORI = "extra_kategori"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getStringExtra(EXTRA_USER) ?: "Pengunjung"

        // Daftar kategori utama
        val daftarKategori = listOf(
            KategoriHewan("Unggas", "Berbulu, memiliki paruh, dan berkembang biak dengan bertelur.", R.drawable.angsa_putih),
            KategoriHewan("Mamalia", "Hewan menyusui yang umumnya berdarah panas dan berambut/berbulu.", R.drawable.kucing_liar),
            KategoriHewan("Hewan Buas", "Predator alami pemangsa daging dengan insting bertahan hidup tinggi.", R.drawable.jaguar),
            KategoriHewan("Reptil & Amfibi", "Hewan berdarah dingin bersisik atau yang hidup di dua alam.", R.drawable.ular_kobra),
            KategoriHewan("Hewan Peliharaan", "Hewan jinak yang sering dirawat dan dipelihara di dalam rumah.", R.drawable.kucing_persia)
        )

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
                            .padding(16.dp)
                    ) {
                        // Header Sapaan
                        Text(
                            text = "Halo, $user! 👋",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Pilih kategori ensiklopedia hewan di bawah ini:",
                            fontSize = 14.sp,
                            color = Color(0xFFC8E6C9),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // List Kategori menggunakan LazyColumn
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(daftarKategori) { kategori ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val intent = Intent(this@SecondActivity, ThirdActivity::class.java).apply {
                                                putExtra(EXTRA_KATEGORI, kategori.namaKategori)
                                                putExtra(EXTRA_USER, user)
                                            }
                                            startActivity(intent)
                                        },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xEEFFFFFF))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        // Gambar Sampul Kategori
                                        Image(
                                            painter = painterResource(id = kategori.imageRes),
                                            contentDescription = kategori.namaKategori,
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(RoundedCornerShape(12.dp)),
                                            contentScale = ContentScale.Crop
                                        )

                                        Spacer(modifier = Modifier.width(16.dp))

                                        // Teks Keterangan Kategori
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = kategori.namaKategori,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF1B5E20)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = kategori.deskripsiSingkat,
                                                fontSize = 12.sp,
                                                color = Color.DarkGray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
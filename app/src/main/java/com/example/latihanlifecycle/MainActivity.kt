package com.example.latihanlifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_KATEGORI = "extra_kategori"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var nama by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var selectedCategory by remember { mutableStateOf("Anak-anak (5-12 Tahun)") }

            // Variabel untuk menyimpan pesan error masing-masing input
            var namaError by remember { mutableStateOf<String?>(null) }
            var emailError by remember { mutableStateOf<String?>(null) }

            val categories = listOf("Anak-anak (5-12 Tahun)", "Remaja (13-19 Tahun)", "Dewasa (20+ Tahun)")
            var expanded by remember { mutableStateOf(false) }

            // Nuansa hijau hutan yang segar dan ramai
            val forestBrush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1B5E20), // Hijau tua hutan
                    Color(0xFF2E7D32), // Hijau daun
                    Color(0xFF81C784), // Hijau muda cerah
                    Color(0xFFC8E6C9)  // Hijau pastel lembut di bawah
                )
            )

            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(forestBrush)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
                        elevation = CardDefaults.cardElevation(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🦁 🌿 🐼 🦜 🐯",
                                fontSize = 26.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "SELAMAT DATANG DI\nPETUALANGAN HUTAN RIMBA! 🌲",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF1B5E20),
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Yuk masuk dan jelajahi suara hewan bersama!",
                                fontSize = 12.sp,
                                color = Color(0xFF33691E),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Input Nama
                            OutlinedTextField(
                                value = nama,
                                onValueChange = {
                                    nama = it
                                    if (namaError != null) namaError = null // Reset error saat diketik ulang
                                },
                                label = { Text("Nama Petualang") },
                                placeholder = { Text("Minimal 2 karakter") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                isError = namaError != null, // Garis jadi merah jika ada error
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF2E7D32),
                                    focusedLabelColor = Color(0xFF2E7D32)
                                )
                            )
                            // Menampilkan teks error merah di bawah input nama jika tidak valid
                            if (namaError != null) {
                                Text(
                                    text = namaError!!,
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp, top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Input Email
                            OutlinedTextField(
                                value = email,
                                onValueChange = {
                                    email = it
                                    if (emailError != null) emailError = null // Reset error saat diketik ulang
                                },
                                label = { Text("Alamat Email (Wajib ada '@')") },
                                placeholder = { Text("contoh: rina@gmail.com") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                isError = emailError != null, // Garis jadi merah jika ada error
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color(0xFF2E7D32),
                                    focusedLabelColor = Color(0xFF2E7D32)
                                )
                            )
                            // Menampilkan teks error merah di bawah input email jika kurang dari 2 karakter / tidak ada @
                            if (emailError != null) {
                                Text(
                                    text = emailError!!,
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp, top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Dropdown Kategori Umur
                            Text(
                                text = "Pilih Kategori Usia Kamu:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B5E20),
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Box(modifier = Modifier.fillMaxWidth()) {
                                OutlinedButton(
                                    onClick = { expanded = true },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF2E7D32))
                                ) {
                                    Text(text = "🏷️ $selectedCategory", fontWeight = FontWeight.Bold)
                                }

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    modifier = Modifier.fillMaxWidth(0.85f)
                                ) {
                                    categories.forEach { category ->
                                        DropdownMenuItem(
                                            text = { Text(category, fontWeight = FontWeight.Medium) },
                                            onClick = {
                                                selectedCategory = category
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Tombol Masuk
                            Button(
                                onClick = {
                                    val trimNama = nama.trim()
                                    val trimEmail = email.trim()

                                    // Validasi dengan menampilkan pesan error merah di bawah field
                                    when {
                                        trimNama.isEmpty() -> {
                                            namaError = "Nama tidak boleh kosong!"
                                        }
                                        trimNama.length < 2 -> {
                                            namaError = "Nama minimal harus 2 karakter!"
                                        }
                                        trimEmail.isEmpty() -> {
                                            emailError = "Email tidak boleh kosong!"
                                        }
                                        trimEmail.length < 2 -> {
                                            emailError = "Email minimal harus 2 karakter!"
                                        }
                                        !trimEmail.contains("@") -> {
                                            emailError = "Format email wajib mengandung karakter '@'!"
                                        }
                                        else -> {
                                            // Jika lolos semua validasi, reset error dan pindah halaman
                                            namaError = null
                                            emailError = null

                                            Toast.makeText(this@MainActivity, "Login Berhasil, Halo $trimNama! 🐒", Toast.LENGTH_SHORT).show()

                                            val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                                                putExtra(EXTRA_NAMA, trimNama)
                                                putExtra(EXTRA_EMAIL, trimEmail)
                                                putExtra(EXTRA_KATEGORI, selectedCategory)
                                            }
                                            startActivity(intent)
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                            ) {
                                Text(
                                    text = "MASUK KE HUTAN 🌲🐾",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
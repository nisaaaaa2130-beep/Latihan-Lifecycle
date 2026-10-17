package com.example.latihanlifecycle

import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleEventObserver
import java.util.Locale

private val Blue = Color(0xFF5366C9)
private val DarkBlue = Color(0xFF35469E)
private val Pink = Color(0xFFFFD8E9)
private val Cream = Color(0xFFFFF0D6)
private val DarkText = Color(0xFF424242)
private val SoftGray = Color(0xFF777783)

data class Animal(
    val name: String,
    val emoji: String,
    val food: String,
    val foodEmoji: String,
    val sound: String,
    val cardColor: Color,
    val spokenSound: String
)

class MainActivity : ComponentActivity() {

    private val tag = "LifecycleDemo"

    private lateinit var lifecycleObserver: LifecycleEventObserver

    private var textToSpeech: TextToSpeech? = null
    private var ttsReady = false
    private var mediaPlayer: MediaPlayer? = null

    private val animals = listOf(
        Animal(
            "Kucing", "🐱", "Ikan Segar", "🐟",
            "Meow~ Meow~", Color(0xFFFFCC80),
            "Meong meong"
        ),
        Animal(
            "Anjing", "🐶", "Tulang & Ayam", "🦴",
            "Guk! Guk!", Color(0xFFCE93D8),
            "Guk guk"
        ),
        Animal(
            "Kelinci", "🐰", "Wortel Segar", "🥕",
            "Ngit! Ngit!", Color(0xFFA5D6A7),
            "Kelinci, ngit ngit"
        ),
        Animal(
            "Sapi", "🐮", "Rumput Segar", "🌿",
            "Moo~ Moo~", Color(0xFF90CAF9),
            "Sapi berbunyi moo moo"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(tag, "onCreate")
        enableEdgeToEdge()

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(
                    Locale("id", "ID")
                )
                ttsReady =
                    result != TextToSpeech.LANG_MISSING_DATA &&
                            result != TextToSpeech.LANG_NOT_SUPPORTED
            }
        }

        val lifecycleState = mutableStateOf("ON_CREATE")

        lifecycleObserver = LifecycleEventObserver { _, event ->
            Log.d(tag, event.name)
            lifecycleState.value = event.name
        }

        lifecycle.addObserver(lifecycleObserver)

        setContent {
            var showHome by remember { mutableStateOf(true) }

            val backgroundBrush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF8EDCFA),
                    Color(0xFFC9C7FA),
                    Color(0xFFFF9FD0)
                )
            )

            BackHandler(enabled = !showHome) {
                showHome = true
            }

            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundBrush)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (showHome) {
                        HomeScreen(
                            onStart = { showHome = false }
                        )
                    } else {
                        AnimalScreen(
                            animals = animals,
                            lifecycleState = lifecycleState.value,
                            onHome = { showHome = true },
                            onSpeak = { animal -> playAnimalSound(animal.name) }
                        )
                    }
                }
            }
        }
    }

    private fun playAnimalSound(animalName: String) {
        val soundResource = when (animalName) {
            "Kucing" -> R.raw.kucing
            "Anjing" -> R.raw.anjing
            "Kelinci" -> R.raw.kelinci
            "Sapi" -> R.raw.sapi
            else -> {
                Log.e(tag, "Suara tidak ditemukan untuk: $animalName")
                return
            }
        }

        // Hentikan dan lepaskan suara sebelumnya jika masih berjalan.
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
        }
        mediaPlayer = null

        mediaPlayer = MediaPlayer.create(this, soundResource)
        if (mediaPlayer == null) {
            Log.e(tag, "Gagal memuat audio untuk: $animalName")
            return
        }

        mediaPlayer?.setOnCompletionListener { player ->
            player.release()
            if (mediaPlayer === player) {
                mediaPlayer = null
            }
        }
        mediaPlayer?.start()
        Log.d(tag, "Memutar suara: $animalName")
    }

    private fun speakAnimal(animal: Animal) {
        if (ttsReady) {
            textToSpeech?.speak(
                "${animal.name}. Suaranya ${animal.spokenSound}. " +
                        "Makanan favoritnya ${animal.food}.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "animal_${animal.name}"
            )
        } else {
            Log.d(tag, "Text-to-Speech belum siap")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        if (::lifecycleObserver.isInitialized) {
            lifecycle.removeObserver(lifecycleObserver)
        }

        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
        }
        mediaPlayer = null

        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null

        Log.d(tag, "onDestroy")
        super.onDestroy()
    }
}

@Composable
fun HomeScreen(
    onStart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.98f),
        shape = RoundedCornerShape(34.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.96f)
        ),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🐾 Belajar Mengenal Hewan 🐾",
                fontSize = 21.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Blue,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Cream
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Halo, Teman Kecil!",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Yuk, kenalan sama hewan-hewan lucu! 🌟",
                        fontSize = 14.sp,
                        color = SoftGray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(205.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFE2A5),
                                Color(0xFFFFBE62)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐱",
                    fontSize = 115.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Selamat Datang! 💗",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Kenali nama, makanan favorit, dan suara mereka bersama-sama!",
                fontSize = 15.sp,
                lineHeight = 23.sp,
                color = SoftGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "🐱     🐶     🐰     🐮",
                fontSize = 33.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Text(
                    text = "MULAI BELAJAR  ➜",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Belajar jadi lebih seru bersama hewan lucu! 💕",
                fontSize = 12.sp,
                color = Blue,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AnimalScreen(
    animals: List<Animal>,
    lifecycleState: String,
    onHome: () -> Unit,
    onSpeak: (Animal) -> Unit
) {
    var selectedAnimal by remember {
        mutableStateOf(animals[0])
    }

    var messageText by remember {
        mutableStateOf(
            "Halo! Yuk kenalan sama hewan-hewan lucu! 🌟"
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.98f),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.97f)
        ),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color(0xFFEEF0FF),
                    modifier = Modifier.clickable { onHome() }
                ) {
                    Text(
                        text = "⌂  HOME",
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 9.dp
                        ),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "🐾 HEWAN LUCU",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SoftGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "🐾 Belajar Mengenal Hewan 🐾",
                fontSize = 20.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Blue,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(22.dp),
                color = Cream,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = messageText,
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Suara: \"${selectedAnimal.sound}\"",
                        fontSize = 13.sp,
                        color = SoftGray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(172.dp)
                    .clip(CircleShape)
                    .background(selectedAnimal.cardColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedAnimal.emoji,
                    fontSize = 94.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ini adalah ${selectedAnimal.name}",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Makanan favoritnya:",
                fontSize = 14.sp,
                color = SoftGray
            )

            Text(
                text = "${selectedAnimal.food} ${selectedAnimal.foodEmoji}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Blue,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { onSpeak(selectedAnimal) },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Blue
                )
            ) {
                Text(
                    text = "🔊 Dengarkan suara",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "👇 Pilih hewan di bawah ini:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = SoftGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                animals.forEach { animal ->
                    val isSelected = selectedAnimal.name == animal.name

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .animateContentSize()
                            .clickable {
                                selectedAnimal = animal
                                messageText =
                                    "Hore! Kamu memilih ${animal.name}! ${animal.emoji}"
                            },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = animal.cardColor.copy(
                                alpha = if (isSelected) 1f else 0.55f
                            )
                        ),
                        border = if (isSelected) {
                            androidx.compose.foundation.BorderStroke(
                                2.dp,
                                Color(0xFFFF8A80)
                            )
                        } else {
                            null
                        },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (isSelected) 5.dp else 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 12.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = animal.emoji,
                                fontSize = 31.sp
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = animal.name,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkText,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = Cream
            ) {
                Column(
                    modifier = Modifier.padding(13.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "◷  Status Lifecycle (Logcat Tugas)",
                        fontSize = 11.sp,
                        color = SoftGray,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = lifecycleState,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFEF6C00)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Terus belajar dan sayangi hewan! 💕",
                fontSize = 12.sp,
                color = Blue,
                textAlign = TextAlign.Center
            )
        }
    }
}

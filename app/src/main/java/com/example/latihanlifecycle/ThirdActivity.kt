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

data class Hewan(
    val nama: String,
    val kategori: String,
    val imageRes: Int,
    val habitat: String,
    val makanan: String,
    val berkembangbiak: String,
    val deskripsi: String
)

class ThirdActivity : ComponentActivity() {

    companion object {
        const val EXTRA_KATEGORI = "extra_kategori"
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kategoriDipilih = intent.getStringExtra(EXTRA_KATEGORI) ?: "Semua"
        val user = intent.getStringExtra(EXTRA_USER) ?: "Pengunjung"

        // MASTER DATA LENGKAP SEMUA HEWAN
        val semuaHewan = listOf(
            // --- KATEGORI: UNGGAS ---
            Hewan("Angsa Putih", "Unggas", R.drawable.angsa_putih, "Danau, sungai, dan lahan basah", "Rumput, biji-bijian, dan tanaman air", "Bertelur", "Angsa adalah burung air berukuran besar yang dikenal setia pada pasangannya."),
            Hewan("Bebek", "Unggas", R.drawable.bebek, "Area perairan tawar dan sawah", "Serangga air, rumput, dan biji-bijian", "Bertelur", "Bebek adalah unggas air yang memiliki selaput di kakinya untuk memudahkan berenang."),
            Hewan("Ayam Kampung", "Unggas", R.drawable.ayam_kampung, "Pedesaan dan pekarangan rumah", "Biji-bijian, cacing, dan sisa makanan", "Bertelur", "Ayam peliharaan yang banyak dibudidayakan masyarakat untuk diambil daging dan telurnya."),
            Hewan("Ayam Hutan", "Unggas", R.drawable.ayam_hutan, "Hutan tropis dan semak belukar", "Biji-bijian, buah jatuh, dan serangga", "Bertelur", "Ayam liar nenek moyang dari berbagai jenis ayam domestik."),
            Hewan("Burung Hantu", "Unggas", R.drawable.burung_hantu, "Pohon tinggi, gua, dan bangunan terbengkalai", "Kecil seperti tikus dan serangga", "Bertelur", "Burung pemangsa nokturnal dengan penglihatan dan pendengaran yang sangat tajam di malam hari."),
            Hewan("Burung Kakatua", "Unggas", R.drawable.burung_kakatua, "Hutan tropis dan pepohonan", "Biji-bijian, kacang, dan buah", "Bertelur", "Burung cerdas berbulu indah yang bisa menirukan berbagai suara manusia."),
            Hewan("Burung Merpati", "Unggas", R.drawable.burung_merpati, "Atap bangunan, taman, dan kota", "Biji-bijian dan remah roti", "Bertelur", "Burung jinak yang sering dijadikan simbol perdamaian dan memiliki kemampuan navigasi pulang."),
            Hewan("Burung Unta", "Unggas", R.drawable.burung_unta, "Padang rumput dan gurun Afrika", "Tanaman, akar, dan serangga", "Bertelur", "Burung terbesar di dunia yang tidak bisa terbang namun berlari sangat kencang."),
            Hewan("Pelikan", "Unggas", R.drawable.pelikan, "Pantai, danau, dan pesisir", "Ikan air tawar dan air laut", "Bertelur", "Burung air yang memiliki kantung besar di paruhnya untuk menangkap ikan."),

            // --- KATEGORI: MAMALIA ---
            Hewan("Kucing Liar", "Mamalia", R.drawable.kucing_liar, "Alam bebas dan pemukiman manusia", "Daging dan ikan (Karnivora)", "Melahirkan (Vivipar)", "Mamalia kecil berbulu yang lincah dan memiliki pendengaran tajam."),
            Hewan("Kelinci", "Mamalia", R.drawable.kelinci, "Padang rumput dan hutan semak", "Wortel, rumput, dan sayuran hijau", "Melahirkan (Vivipar)", "Mamalia bertelinga panjang yang bergerak dengan cara melompat-lompat."),
            Hewan("Sapi", "Mamalia", R.drawable.sapi, "Peternakan dan padang rumput", "Rumput dan hijauan", "Melahirkan (Vivipar)", "Hewan ternak besar pemakan rumput penghasil susu dan daging utama."),
            Hewan("Kambing", "Mamalia", R.drawable.kambing_etawa, "Pedesaan dan perbukitan", "Rumput dan dedaunan", "Melahirkan (Vivipar)", "Mamalia herbivora lincah yang biasa diternakkan untuk diambil daging dan susunya."),
            Hewan("Kuda", "Mamalia", R.drawable.kuda, "Padang rumput terbuka", "Rumput dan jerami", "Melahirkan (Vivipar)", "Hewan perkasa berukuran besar yang dahulu sering dijadikan alat transportasi."),
            Hewan("Kuda Nil", "Mamalia", R.drawable.kudanil, "Sungai dan rawa-rawa Afrika", "Rumput air", "Melahirkan (Vivipar)", "Mamalia semi-akuatik bertubuh besar yang tampak tenang namun sangat agresif."),
            Hewan("Kangguru", "Mamalia", R.drawable.kangguru, "Padang rumput Australia", "Rumput dan dedaunan", "Melahirkan (Vivipar)", "Mamalia berkantung khas Australia yang bergerak dengan melompat menggunakan kaki belakang."),
            Hewan("Koala", "Mamalia", R.drawable.koala, "Pohon eukaliptus Australia", "Daun eukaliptus", "Melahirkan (Vivipar)", "Mamalia pemalas yang menghabiskan sebagian besar hidupnya di atas pohon."),
            Hewan("Keledai", "Mamalia", R.drawable.keledai, "Area kering dan perbukitan", "Rumput kering dan semak", "Melahirkan (Vivipar)", "Mamalia kerabat kuda yang dikenal kuat dan tahan banting di medan berat."),
            Hewan("Kera Hitam Sulawesi", "Mamalia", R.drawable.kera_hitam_sulawesi, "Hutan hujan tropis Sulawesi", "Buah-buahan, biji, dan serangga", "Melahirkan (Vivipar)", "Primata endemik Indonesia yang memiliki ciri khas jambul di atas kepalanya."),
            Hewan("Gajah", "Mamalia", R.drawable.gajah, "Hutan dan savana luas", "Rumput, buah, dan kulit pohon", "Melahirkan (Vivipar)", "Mamalia darat terbesar di dunia yang memiliki belalai panjang dan gading."),
            Hewan("Jerapah", "Mamalia", R.drawable.jerapah, "Savana terbuka", "Daun pohon tinggi (Akasia)", "Melahirkan (Vivipar)", "Mamalia darat tertinggi di dunia dengan leher yang sangat panjang."),
            Hewan("Panda", "Mamalia", R.drawable.panda, "Hutan pegunungan China", "Bambu", "Melahirkan (Vivipar)", "Mamalia berbulu hitam-putih menggemaskan yang makanan utamanya adalah batang bambu."),
            Hewan("Zebra", "Mamalia", R.drawable.zebra, "Padang rumput Afrika", "Rumput liar", "Melahirkan (Vivipar)", "Mamalia bertubuh mirip kuda dengan corak belang-belang hitam putih yang unik."),
            Hewan("Tupai Kelapa", "Mamalia", R.drawable.tupai_kelapa, "Pohon kelapa dan kebun", "Buah, biji-bijian, dan serangga", "Melahirkan (Vivipar)", "Rodensia kecil pemanjat pohon yang sangat lincah."),
            Hewan("Tupai Tanah", "Mamalia", R.drawable.tupai_tanah, "Area tanah lapang dan padang rumput", "Biji-bijian dan akar tanaman", "Melahirkan (Vivipar)", "Tupai yang menghabiskan sebagian besar waktunya menggali lubang di tanah."),
            Hewan("Platypus", "Mamalia", R.drawable.platypus, "Sungai air tawar Australia", "Cacing, serangga air, dan udang kecil", "Bertelur (Mamalia unik)", "Mamalia semi-akuatik unik dari Australia yang berparuh seperti bebek dan bertelur."),
            Hewan("Kelelawar", "Mamalia", R.drawable.kelelawar, "Gua gelap dan pohon tinggi", "Buah-buahan atau serangga", "Melahirkan (Vivipar)", "Satu-satunya mamalia yang memiliki sayap dan bisa terbang aktif di malam hari."),

            // --- KATEGORI: HEWAN BUAS ---
            Hewan("Jaguar", "Hewan Buas", R.drawable.jaguar, "Hutan hujan tropis Amerika", "Daging hewan buruan (Karnivora)", "Melahirkan (Vivipar)", "Kucing besar soliter yang sangat kuat dan merupakan predator puncak."),
            Hewan("Singa", "Hewan Buas", R.drawable.singa, "Padang rumput sabana Afrika", "Daging mangsa besar (Karnivora)", "Melahirkan (Vivipar)", "Dikenal sebagai 'Raja Hutan' yang hidup berkelompok dalam satu kawanan."),
            Hewan("Macan Tutul", "Hewan Buas", R.drawable.macan_tutul, "Hutan dan wilayah berbatu", "Daging hewan buruan", "Melahirkan (Vivipar)", "Kucing besar yang sangat lihai memanjat pohon dan membawa mangsanya ke atas."),
            Hewan("Harimau Sumatera", "Hewan Buas", R.drawable.harimau_sumatera, "Hutan tropis Pulau Sumatera", "Daging rusa, babi hutan (Karnivora)", "Melahirkan (Vivipar)", "Subspesies harimau endemik Indonesia yang kini berada di ambang kepunahan."),
            Hewan("Serigala", "Hewan Buas", R.drawable.serigala, "Hutan dan pegunungan dingin", "Daging hewan buruan", "Melahirkan (Vivipar)", "Hewan sosial yang cerdas dan berburu secara berkelompok menggunakan strategi."),
            Hewan("Beruang Madu", "Hewan Buas", R.drawable.beruang, "Hutan hujan tropis Asia Tenggara", "Madu, buah, dan serangga", "Melahirkan (Vivipar)", "Jenis beruang terkecil di dunia yang memiliki tanda kuning di dadanya."),
            Hewan("Hyena", "Hewan Buas", R.drawable.hyena, "Savana dan gurun Afrika", "Daging dan bangkai hewan", "Melahirkan (Vivipar)", "Karnivora penjelajah savana yang terkenal dengan suara tawa khasnya."),
            Hewan("Cheetah", "Hewan Buas", R.drawable.citah, "Padang rumput terbuka Afrika", "Daging mangsa cepat (Antelop)", "Melahirkan (Vivipar)", "Hewan darat tercepat di dunia dengan corak totol-totol di tubuhnya."),

            // --- KATEGORI: REPTIL & AMFIBI ---
            Hewan("Ular Kobra", "Reptil & Amfibi", R.drawable.ular_kobra, "Semak-semak dan perkebunan", "Tikus dan kodok", "Bertelur", "Ular berbisa tinggi yang dapat mengembangkan lehernya saat terancam."),
            Hewan("Ular Piton", "Reptil & Amfibi", R.drawable.ular_piton, "Hutan lembap dan rawa", "Mamalia sedang", "Bertelur", "Ular raksasa tidak berbisa yang membunuh mangsanya dengan cara melilit."),
            Hewan("Ular Anakonda", "Reptil & Amfibi", R.drawable.ular_anakonda, "Sungai Amazon dan rawa Amerika", "Hewan air dan mamalia", "Bertelur", "Ular terbesar dan terberat di dunia yang hidup di perairan tropis."),
            Hewan("Ular Sanca", "Reptil & Amfibi", R.drawable.ular_sanca, "Hutan sekunder dan semak", "Hewan berdarah panas", "Bertelur", "Ular pembelit berukuran besar yang sering ditemui di kawasan tropis."),
            Hewan("Ular Derik", "Reptil & Amfibi", R.drawable.ular_derik, "Padang rumput kering dan gurun", "Krodensia kecil", "Bertelur", "Ular berbisa yang memiliki ekor berbunyi derik untuk memperingatkan musuh."),
            Hewan("Buaya", "Reptil & Amfibi", R.drawable.buaya, "Sungai besar dan rawa-rawa", "Ikan, burung, dan mamalia air", "Bertelur", "Reptil purba berdarah dingin dengan rahang kuat dan kulit bersisik keras."),
            Hewan("Komodo", "Reptil & Amfibi", R.drawable.komodo, "Pulau Komodo dan Rinca, NTT", "Daging dan bangkai hewan", "Bertelur", "Kadal terbesar di dunia yang merupakan hewan purba asli Indonesia."),
            Hewan("Kadal", "Reptil & Amfibi", R.drawable.kadal, "Pohon, bebatuan, dan dinding", "Serangga kecil", "Bertelur", "Reptil berkaki empat yang gesit dan sering berjemur di bawah matahari."),
            Hewan("Bunglon", "Reptil & Amfibi", R.drawable.bunglon, "Pohon dan semak", "Serangga", "Bertelur", "Reptil unik yang bisa mengubah warna kulitnya untuk beradaptasi."),
            Hewan("Iguana", "Reptil & Amfibi", R.drawable.iguana_hijau, "Pohon di dekat air tropis", "Daun, bunga, dan buah", "Bertelur", "Kadal herbivora besar yang memiliki duri di sepanjang punggungnya."),
            Hewan("Kura-Kura", "Reptil & Amfibi", R.drawable.kura_kura, "Darat, sungai, atau laut", "Tumbuhan dan hewan kecil", "Bertelur", "Reptil bercangkang keras yang bergerak sangat lambat dan berumur panjang."),
            Hewan("Kodok Bangkong", "Reptil & Amfibi", R.drawable.kodok_bangkong, "Tanah lembap dan dekat air", "Serangga dan cacing tanah", "Bertelur", "Amfibi berkulit kasar yang mengalami metamorfosis dari berudu."),
            Hewan("Tokek", "Reptil & Amfibi", R.drawable.tokek, "Dinding rumah dan batang pohon", "Serangga", "Bertelur", "Cicak berukuran besar yang sering bersuara nyaring di malam hari."),

            // --- KATEGORI: HEWAN PELIHARAAN ---
            Hewan("Kucing Persia", "Hewan Peliharaan", R.drawable.kucing_persia, "Lingkungan rumah / dalam ruangan", "Makanan khusus kucing (daging/ikan)", "Melahirkan (Vivipar)", "Kucing berbulu panjang dan lebat yang memiliki wajah bulat menggemaskan serta sifat yang tenang."),
            Hewan("Kucing Anggora", "Hewan Peliharaan", R.drawable.kucing_anggora, "Lingkungan rumah / dalam ruangan", "Makanan khusus kucing (daging/ikan)", "Melahirkan (Vivipar)", "Kucing elegan asal Turki dengan bulu putih lembut dan mata yang seringkali berbeda warna."),
            Hewan("Kelinci", "Hewan Peliharaan", R.drawable.kelinci, "Kandang peliharaan / pekarangan rumput", "Wortel, rumput, hay, dan sayuran hijau", "Melahirkan (Vivipar)", "Mamalia bertelinga panjang yang sangat lucu, jinak, dan bergerak dengan cara melompat-lompat."),
            Hewan("Kucing Domestik", "Hewan Peliharaan", R.drawable.kucing_domestik, "Lingkungan rumah dan sekitar pemukiman", "Ikan, daging, dan makanan kucing", "Melahirkan (Vivipar)", "Kucing kampung atau lokal beraneka corak warna yang sangat mandiri, aktif, dan setia kepada pemiliknya."),
            Hewan("Ikan Hias", "Hewan Peliharaan", R.drawable.ikan_hias, "Akuarium air tawar", "Pelet ikan, cacing darah, dan jentik", "Bertelur", "Ikan beraneka warna dan bentuk indah yang mempercantik dan menenangkan suasana ruangan."),
            Hewan("Hamster", "Hewan Peliharaan", R.drawable.hamster, "Kandang peliharaan / akuarium khusus", "Biji-bijian, biji bunga matahari, dan buah", "Melahirkan (Vivipar)", "Rodensia kecil aktif yang sangat suka menyimpan makanan di dalam kantong pipinya."),
            Hewan("Marmut", "Hewan Peliharaan", R.drawable.marmut, "Kandang peliharaan / pekarangan", "Rumput, hay, sayuran hijau, dan vitamin C", "Melahirkan (Vivipar)", "Rodensia berukuran lebih besar dari hamster yang jinak, ramah, dan suka bersuara nyaring saat lapar.")
        )

        // Filter daftar hewan sesuai kategori yang diklik
        val hewanTerfilter = semuaHewan.filter { it.kategori == kategoriDipilih }

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
                        // Tombol Kembali
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Button(
                                onClick = { finish() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                            ) {
                                Text("← Ganti Kategori", color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Kategori: $kategoriDipilih",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Total hewan: ${hewanTerfilter.size} jenis",
                            fontSize = 14.sp,
                            color = Color(0xFFC8E6C9),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // LazyColumn Daftar Hewan
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(hewanTerfilter) { hewan ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val intent = Intent(this@ThirdActivity, FourthActivity::class.java).apply {
                                                putExtra(FourthActivity.EXTRA_NAMA_HEWAN, hewan.nama)
                                                putExtra(FourthActivity.EXTRA_KATEGORI_HEWAN, hewan.kategori)
                                                putExtra(FourthActivity.EXTRA_IMAGE_RES, hewan.imageRes)
                                                putExtra(FourthActivity.EXTRA_HABITAT, hewan.habitat)
                                                putExtra(FourthActivity.EXTRA_MAKANAN, hewan.makanan)
                                                putExtra(FourthActivity.EXTRA_BERKEMBANG, hewan.berkembangbiak)
                                                putExtra(FourthActivity.EXTRA_DESKRIPSI, hewan.deskripsi)
                                                putExtra(FourthActivity.EXTRA_USER, user)
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
                                        Image(
                                            painter = painterResource(id = hewan.imageRes),
                                            contentDescription = hewan.nama,
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(RoundedCornerShape(12.dp)),
                                            contentScale = ContentScale.Crop
                                        )

                                        Spacer(modifier = Modifier.width(16.dp))

                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = hewan.nama,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF1B5E20)
                                            )
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "🌍 ${hewan.habitat}",
                                                fontSize = 12.sp,
                                                color = Color.DarkGray
                                            )
                                            Text(
                                                text = "Ketuk untuk lihat detail →",
                                                fontSize = 11.sp,
                                                color = Color(0xFF388E3C),
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier.padding(top = 4.dp)
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
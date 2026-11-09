# Ensiklopedia Hewan (Aplikasi Mobile 4-Tier)

Aplikasi **Belajar Mengenal Hewan** berbasis Android yang dikembangkan menggunakan Kotlin dan Jetpack Compose/XML di Android Studio. Aplikasi ini dirancang untuk tugas pemrograman mobile guna mempelajari navigasi multi-layar, *lifecycle*, dan transfer data antar *Activity*.

## 📱 Fitur & Layar Aplikasi (4-Tier Architecture)

1. **Layar 1 (MainActivity)**: Halaman Sambutan / Beranda dengan input nama dinamis (*Extra User*) dan tombol navigasi ke menu kategori hewan.
2. **Layar 2 (SecondActivity)**: Halaman Kategori Hewan dengan pilihan kelompok umur dan transisi data intent.
3. **Layar 3 (ThirdActivity)**: Halaman Daftar Hewan Peliharaan (*Hewan Peliharaan*) yang menampilkan detail data seperti Kucing Persia, Kucing Anggora, Kucing Domestik, Kelinci, Ikan Hias, Hamster, dan Marmut.
4. **Layar 4 (FourthActivity)**: Halaman Detail / Informasi Lanjutan terkait karakteristik dan perawatan hewan.

## 🛠️ Komponen & Teknologi yang Digunakan

- **Bahasa & Framework**: Kotlin, Jetpack Compose / XML Layouts
- **Komponen Utama**: Activity Lifecycle, Intent Data Passing (`EXTRA_USER`, `EXTRA_KATEGORI`), RecyclerView / LazyColumn
- **Resource Management**: `strings.xml`, `colors.xml`

## 🚀 Cara Menjalankan Aplikasi (Langsung di HP)

1. **Persiapan HP**:
   - Aktifkan **Developer Options** (Opsi Pengembang) di HP Android kamu.
   - Aktifkan fitur **USB Debugging** di dalam menu Opsi Pengembang.
   - Sambungkan HP ke laptop menggunakan kabel USB.
2. **Clone & Buka Project di Android Studio**:
   - Buka terminal/command prompt, lalu unduh repository dengan perintah:
     ```bash
     git clone [https://github.com/nisaaaaa2130-beep/Latihan-Lifecycle.git](https://github.com/nisaaaaa2130-beep/Latihan-Lifecycle.git)
     ```
   - Buka folder hasil *clone* tersebut melalui Android Studio.
   - Tunggu proses *Gradle Sync* sampai selesai.
3. **Jalankan Aplikasi**:
   - Di bagian atas Android Studio, pastikan perangkat yang dipilih adalah nama HP fisikmu (bukan emulator).
   - Klik tombol **Run (ikon play hijau / Shift + F10)** untuk *build* dan *install* aplikasi secara otomatis langsung ke HP-mu.

## 📸 Tangkapan Layar (Screenshot)

| Layar 1 (Beranda) | Layar 2 (invalid input) |Layar 3 (Kategori) | Layar 4 (Hewan Peliharaan) | Layar 5 (Detail) |
| :---: | :---: | :---: | :---: | :---: |
| ![Layar 1](screen_1.jpeg) | ![Layar 2](screen_2.jpeg) | ![Layar 3](screen_3.jpeg) | ![Layar 4](screen_4.jpeg) | ![Layar 4](screen_5.jpeg)

## 🎥 Demo Aplikasi

Berikut adalah video demonstrasi penggunaan aplikasi:

📹 **Video Demo Aplikasi:** 👉 **[Download & Tonton Video Demo (MP4)](demo_aplikasi.mp4)**  
*(Klik tautan di atas atau pilih file `demo_aplikasi.mp4` di repository GitHub, lalu klik "View raw" untuk mengunduh atau memutar video demonya).*

---
*Dikembangkan untuk pembelajaran Pemrograman Mobile.*

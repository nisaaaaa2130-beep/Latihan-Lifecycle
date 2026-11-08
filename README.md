# Tugas - Ensiklopedia Hewan (Aplikasi Mobile 4-Tier)

Aplikasi **Belajar Mengenal Hewan** berbasis Android yang dikembangkan menggunakan Kotlin dan Jetpack Compose/XML di Android Studio.

## 📱 Fitur & Layar Aplikasi (4-Tier Architecture)

1. **Layar 1 (MainActivity)**: Halaman Sambutan / Beranda dengan input nama dinamis (*Extra User*) dan tombol navigasi ke menu kategori hewan.
2. **Layar 2 (SecondActivity)**: Halaman Kategori Hewan dengan pilihan kelompok umur dan transisi data intent.
3. **Layar 3 (ThirdActivity)**: Halaman Daftar Hewan Peliharaan (*Hewan Peliharaan*) yang menampilkan detail data seperti Kucing Persia, Kucing Anggora, Kucing Domestik, Kelinci, Ikan Hias, Hamster, dan Marmut.
4. **Layar 4 (FourthActivity)**: Halaman Detail / Informasi Lanjutan terkait karakteristik dan perawatan hewan.

## 🛠️ Komponen & Teknologi yang Digunakan

- **Bahasa**: Kotlin, Jetpack Compose / XML Layouts
- **Komponen**: Activity Lifecycle, Intent Data Passing (`EXTRA_USER`, `EXTRA_KATEGORI`), RecyclerView / LazyColumn
- **Resource Management**: strings.xml, colors.xml

## 🚀 Cara Menjalankan Aplikasi

1. Buka project di Android Studio.
2. Hubungkan perangkat fisik atau nyalakan Emulator Android.
3. Klik tombol **Run (Shift + F10)** untuk *build* dan *install* aplikasi ke perangkat.

## 📸 Tangkapan Layar (Screenshot)

| Layar 1 (Beranda) | Layar 2 (Kategori) | Layar 3 (Hewan Peliharaan) | Layar 4 (Detail) |
| :---: | :---: | :---: | :---: |
| ![Layar 1](screen_1.jpeg) | ![Layar 2](screen_2.jpeg) | ![Layar 3](screen_3.jpeg) | ![Layar 4](screen_4.jpeg) |

# Notif Color Icon

Module LSPosed kecil untuk **memaksa setiap notifikasi memakai ikon aplikasi asli (berwarna)** — di status bar maupun di panel notifikasi (shade) — sebagai ganti ikon `small icon` monokrom yang di-tint.

## Apa ini

Cuma **satu file APK kecil** yang nge-hook ke **SystemUI** (`com.android.systemui`).

- Tidak ada service.
- Tidak minta permission.
- Tidak mengirim data keluar.
- Tidak mengubah file sistem.
- Semua kerja hanya di dalam proses SystemUI saat notifikasi dirender.

## Kenapa perlu

Android / AOSP menampilkan notifikasi memakai *small icon* (ikon putih monokrom yang diwarnai otomatis oleh sistem). Akibatnya ikon notifikasi sering tidak mirip aplikasi pengirimnya. Beberapa ROM (mis. realme UI / Heytap) menampilkan sebagian ikon berwarna, sebagian monokrom — jadi campur.

Module ini menyeragamkan: **setiap notifikasi tampil dengan ikon aplikasi pengirimnya**, baik di status bar maupun di baris notifikasi (shade).

## Tujuan & dukungan perangkat

- Dikembangkan & diuji di **Android 15**, basis **AOSP + komponen OEM (realme UI 15)** — contoh: **Realme C53 (RMX3760)**.
- Menyasar kelas SystemUI standar AOSP, jadi **berpeluang jalan di device AOSP-based Android 12–15 lain** (termasuk ROM GO / AOSP ringan).
- **Tidak dijamin** untuk OEM yang mengganti/menghapus kelas SystemUI-nya. Kalau tidak jalan, cek log LSPosed dengan tag modul.

## Persyaratan

- Root + **LSPosed / Vector**
- Android **12+** (diuji di 15)
- Scope: **`com.android.systemui`**

## Cara install

1. Install `notif-color-icon.apk`.
2. Buka LSPosed → aktifkan module ini.
3. Pastikan scope `com.android.systemui` tercentang.
4. Restart SystemUI / reboot.

## Ingin tahu isinya?

Source tidak dipublikasikan. Kalau penasaran, silakan decompile APK-nya sendiri.

Package: `com.arxxcc.notificon`

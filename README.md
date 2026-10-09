# Notif Color Icon

Modul LSPosed yang membuat setiap notifikasi memakai **ikon asli aplikasi pengirim (berwarna)** — di status bar maupun di shade.

## Latar belakang

Di Android 15 basis AOSP (termasuk realme UI / realme seri GO), ikon notifikasi di status bar dan shade dirender lewat `com.android.internal.widget.CachingIconView` dan `NotificationRowIconView`.

Untuk notifikasi yang menyetel `color` (umpama WhatsApp, Tokopedia), SystemUI memanggil `setOriginalIconColor()` dan `setGrayedOut()`, yang menjalankan:

```
drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
```

Akibatnya ikon jadi monokrom/kelabu. Notifikasi yang tidak menyetel `color` (umpama Glints) tidak di-tint, jadi tetap berwarna. Hasilnya tampilan ikon tidak konsisten per aplikasi/notifikasi — sebagian berwarna, sebagian putih/kelabu. Paling kelihatan pada notifikasi grup yang di-expand.

## Yang dilakukan modul

- Scope ke `com.android.systemui`.
- Mengambil package pengirim dari notifikasi: `Notification.extras["android.appInfo"]` → `ApplicationInfo.packageName`.
- Mengganti drawable ikon (`StatusBarIconView`, `CachingIconView`, `NotificationRowIconView`) dengan `PackageManager.getApplicationIcon(pkg)` — di status bar dan shade, termasuk baris notifikasi grup.
- Menghapus color filter yang dipasang `setOriginalIconColor()` / `setGrayedOut()` agar ikon berwarna tidak ikut di-mono-kan.

Modul **tidak** melakukan "monet" / themed icon. Yang dipakai adalah ikon asli aplikasi (berwarna), bukan ikon yang disesuaikan ke tema.

## Sifat

- Hanya APK kecil. Tanpa service, tanpa permission, tanpa akses jaringan/storage.
- Bekerja in-process di SystemUI saat notifikasi dirender.

## Kompatibilitas

- Dikembangkan & diuji: Android 15 AOSP + realme UI (Realme C53 / RMX3760).
- Menyasar kelas SystemUI standar AOSP, jadi berpeluang jalan di AOSP-based Android 12–15 lain (termasuk ROM GO / AOSP ringan).
- Tidak dijamin pada OEM yang mengubah atau menghapus kelas SystemUI tersebut.

## Persyaratan

- Root + LSPosed / Vector
- Scope: `com.android.systemui`

## Install

1. Unduh APK dari halaman **Releases**.
2. Install APK.
3. Buka LSPosed, aktifkan modul ini, pastikan scope `com.android.systemui` tercentang.
4. Restart SystemUI / reboot.

## Unduh

APK tersedia di halaman Releases: <https://github.com/arriRgb31/lsposed-notif-hider/releases/latest>

Package: `com.arxxcc.notificon`

Source tidak dipublikasikan.

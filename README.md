# Notif Color Icon

Modul LSPosed yang membuat notifikasi memakai **ikon asli aplikasi pengirim (berwarna)** — di status bar dan shade.

Package: `com.arxxcc.notificon`

## Masalah

Di Android 15 basis AOSP (realme UI / realme seri GO), ikon notifikasi dirender lewat `com.android.internal.widget.CachingIconView` dan `NotificationRowIconView`.

Untuk notifikasi yang menyetel `color` (umpama WhatsApp, Tokopedia), SystemUI memanggil `setOriginalIconColor()` dan `setGrayedOut()`, yang menjalankan:

```
drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
```

Ikon jadi monokrom/kelabu. Notifikasi yang tidak menyetel `color` (umpama Glints) tidak di-tint, jadi tetap berwarna. Karena itu tampilan ikon tidak konsisten per aplikasi/notifikasi — sebagian berwarna, sebagian putih/kelabu. Paling kelihatan pada notifikasi grup yang di-expand.

## Yang dilakukan modul

- Scope ke `com.android.systemui`.
- Mengambil package pengirim dari notifikasi: `Notification.extras["android.appInfo"]` → `ApplicationInfo.packageName`.
- Mengganti drawable ikon (`StatusBarIconView`, `CachingIconView`, `NotificationRowIconView`) dengan `PackageManager.getApplicationIcon(pkg)` — di status bar dan shade, termasuk baris notifikasi grup.
- Menghapus color filter yang dipasang `setOriginalIconColor()` / `setGrayedOut()` supaya ikon berwarna tidak ikut di-mono-kan.

Modul **tidak** melakukan "monet" / themed icon. Yang dipakai adalah ikon asli aplikasi (berwarna), bukan ikon yang disesuaikan ke tema.

## Sifat

- Hanya APK kecil. Tanpa service, tanpa permission, tanpa akses jaringan/storage.
- Bekerja in-process di SystemUI saat notifikasi dirender.

## Kompatibilitas

- Dikembangkan & diuji: Android 15 AOSP + realme UI (Realme C53 / RMX3760).
- Menyasar kelas SystemUI standar AOSP, jadi berpeluang jalan di AOSP-based Android 12–15 lain.
- Tidak dijamin pada OEM yang mengubah atau menghapus kelas SystemUI tersebut.

## Persyaratan

- Root.
- Framework Xposed: **LSPosed (Jing Matrix)**. LSPosed sendiri adalah framework/modul Xposed — modul ini dijalankan lewat LSPosed, bukan aplikasi mandiri.
- Scope modul: `com.android.systemui`

## Install

1. Install APK.
2. Buka LSPosed, aktifkan modul ini.
3. Pastikan scope `com.android.systemui` tercentang.
4. Restart SystemUI / reboot.

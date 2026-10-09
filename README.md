# LSPosed Notification Hider (SystemUI)

## Kegunaan
Module LSPosed kecil untuk mengontrol ikon notifikasi aplikasi di status bar (notification icons) pada SystemUI. Fokus utamanya: menyembunyikan/tampilkan ikon notifikasi per aplikasi atau global sesuai kebutuhan.

Tujuannya agar ikon notifikasi di status bar bisa dikontrol (hide/show) tanpa mengubah warna monochrome (bukan tweak monochrome, bukan AOSP "icon color" tweak).

## Target Device
- Realme C series (Realme C53/C series)
- Kemungkinan kompatibel dengan ROM AOSP-based lain (tergantung struktur SystemUI)

## Dibutuhkan
- LSPosed / JingMatrix (dibutuhkan untuk method hooking)
- Aplikasi target terinstall
- SystemUI sebagai scope (sudah diset di `xposed_scope`)

## Struktur
- `app/src/main/java/arri/lsposed/notifhider/XposedInit.java` - entry hook SystemUI
- `module.prop` - info module LSPosed
- `AndroidManifest.xml` - deklarasi xposed + scope
- `res/values/arrays.xml` - scope `com.android.systemui`

## Implementasi (rencana)
Hook class-class SystemUI terkait notification icons:
- `com.android.systemui.statusbar.phone.NotificationIconAreaController`
- `com.android.systemui.statusbar.StatusBarIconView`
- `com.android.systemui.statusbar.phone.IconMerger` (jika ada)
- atau class terkait `NotificationIconContainer`

Tujuannya: mengontrol visibility (setVisibility/GONE/VISIBLE) untuk ikon notifikasi aplikasi di status bar. Bukan mengubah tint/color jadi monochrome.

## Build
Build pakai Android Studio / gradle. Hasilkan `.apk` lalu install + enable di LSPosed/JingMatrix, reboot/restart SystemUI sesuai kebutuhan.

## Link
- JingMatrix (dibutuhkan untuk hook): https://github.com/JingMatrix/LSPosed
- LSPosed: https://github.com/LSPosed/LSPosed

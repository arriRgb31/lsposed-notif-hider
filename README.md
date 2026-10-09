# LSPosed Notification Hider (SystemUI)

## Kegunaan
Module LSPosed kecil untuk mengaktifkan/mengubah perilaku notifikasi di SystemUI. Fokus: kontrol ikon notifikasi/status bar terkait notifikasi (hide/show) sesuai kebutuhan.

## Tujuan
Membangun module minimal yang bisa hook SystemUI di Android AOSP-like (Realme C series, kemungkinan bisa dipakai di AOSP lain). Mengandalkan JingMatrix/LSPosed API untuk method hooking.

## Struktur (rencana)
- app/ (module LSPosed) dengan `module.prop`, `AndroidManifest.xml`
- src/main/java/... dengan Xposed hooks ke SystemUI (class terkait StatusBar/NotificationIconArea/IconMerger dll)
- gunakan lib `de.robv.android.xposed:api` (provided) sesuai LSPosed

## Link terkait
- JingMatrix (dibutuhkan untuk hook): https://github.com/JingMatrix/LSPosed
- LSPosed: https://github.com/LSPosed/LSPosed

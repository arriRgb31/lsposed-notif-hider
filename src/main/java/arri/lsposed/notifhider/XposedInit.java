package arri.lsposed.notifhider;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedInit implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!"com.android.systemui".equals(lpparam.packageName)) {
            return;
        }
        XposedBridge.log("NotifIconHider: Hooking com.android.systemui");

        // Try NotificationIconAreaController
        Class<?> iconArea = XposedHelpers.findClassIfExists(
                "com.android.systemui.statusbar.phone.NotificationIconAreaController",
                lpparam.classLoader);
        if (iconArea != null) {
            XposedBridge.log("NotifIconHider: Found NotificationIconAreaController");
            // Common method: updateIcons / addNotification / updateState
            try {
                XposedHelpers.findAndHookMethod(iconArea, "updateIcons", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        // Placeholder: inspect if needed
                    }
                });
            } catch (Throwable ignored) {}
        }

        // Try StatusBarIconView
        Class<?> iconView = XposedHelpers.findClassIfExists(
                "com.android.systemui.statusbar.StatusBarIconView",
                lpparam.classLoader);
        if (iconView != null) {
            XposedBridge.log("NotifIconHider: Found StatusBarIconView");
            try {
                XposedHelpers.findAndHookMethod(iconView, "setVisibleState", int.class, boolean.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        // TODO: filter by notification info if needed
                    }
                });
            } catch (Throwable ignored) {}
        }

        // Try IconMerger
        Class<?> iconMerger = XposedHelpers.findClassIfExists(
                "com.android.systemui.statusbar.phone.IconMerger",
                lpparam.classLoader);
        if (iconMerger != null) {
            XposedBridge.log("NotifIconHider: Found IconMerger");
        }

        // Try NotificationIconContainer
        Class<?> iconContainer = XposedHelpers.findClassIfExists(
                "com.android.systemui.statusbar.phone.NotificationIconContainer",
                lpparam.classLoader);
        if (iconContainer != null) {
            XposedBridge.log("NotifIconHider: Found NotificationIconContainer");
        }
    }
}

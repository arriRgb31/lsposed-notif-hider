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
        XposedBridge.log("NotifIconHider: Hooking SystemUI - " + lpparam.versionName);
        // TODO: Hook NotificationIconArea/IconMerger/StatusBarIconView as needed
        // Example (safe placeholder): try to hook common methods if present
        try {
            Class<?> cls = XposedHelpers.findClassIfExists("com.android.systemui.statusbar.phone.NotificationIconAreaController", lpparam.classLoader);
            if (cls != null) {
                XposedBridge.log("NotifIconHider: Found NotificationIconAreaController");
            }
        } catch (Throwable t) {
            XposedBridge.log("NotifIconHider: " + t.getMessage());
        }
    }
}

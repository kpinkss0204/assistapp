package androidx.camera.extensions.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionDisabledQuirk implements Quirk {
    static boolean load() {
        return isPixel5() || isMoto();
    }

    public boolean shouldDisableExtension() {
        if (!isPixel5() || isAdvancedExtenderSupported()) {
            return isMoto() && ExtensionVersion.isMaximumCompatibleVersion(Version.VERSION_1_1);
        }
        return true;
    }

    private static boolean isPixel5() {
        return "google".equalsIgnoreCase(Build.BRAND) && "redfin".equalsIgnoreCase(Build.DEVICE);
    }

    private static boolean isMoto() {
        return "motorola".equalsIgnoreCase(Build.BRAND);
    }

    private static boolean isAdvancedExtenderSupported() {
        return ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_2) && ExtensionVersion.isAdvancedExtenderSupported();
    }
}

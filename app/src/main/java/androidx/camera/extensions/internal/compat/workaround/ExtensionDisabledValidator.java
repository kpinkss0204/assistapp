package androidx.camera.extensions.internal.compat.workaround;

import androidx.camera.extensions.internal.compat.quirk.DeviceQuirks;
import androidx.camera.extensions.internal.compat.quirk.ExtensionDisabledQuirk;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionDisabledValidator {
    private final ExtensionDisabledQuirk mQuirk = (ExtensionDisabledQuirk) DeviceQuirks.get(ExtensionDisabledQuirk.class);

    public boolean shouldDisableExtension() {
        ExtensionDisabledQuirk extensionDisabledQuirk = this.mQuirk;
        return extensionDisabledQuirk != null && extensionDisabledQuirk.shouldDisableExtension();
    }
}

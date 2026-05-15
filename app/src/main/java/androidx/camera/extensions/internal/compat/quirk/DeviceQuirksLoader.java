package androidx.camera.extensions.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceQuirksLoader {
    private DeviceQuirksLoader() {
    }

    static List<Quirk> loadQuirks() {
        ArrayList arrayList = new ArrayList();
        if (ExtensionDisabledQuirk.load()) {
            arrayList.add(new ExtensionDisabledQuirk());
        }
        if (CrashWhenOnDisableTooSoon.load()) {
            arrayList.add(new CrashWhenOnDisableTooSoon());
        }
        if (GetAvailableKeysNeedsOnInit.load()) {
            arrayList.add(new GetAvailableKeysNeedsOnInit());
        }
        if (ImageAnalysisUnavailableQuirk.load()) {
            arrayList.add(new ImageAnalysisUnavailableQuirk());
        }
        if (ExtraSupportedSurfaceCombinationsQuirk.load()) {
            arrayList.add(new ExtraSupportedSurfaceCombinationsQuirk());
        }
        return arrayList;
    }
}

package androidx.camera.extensions.internal.compat.workaround;

import androidx.camera.extensions.internal.compat.quirk.DeviceQuirks;
import androidx.camera.extensions.internal.compat.quirk.ExtraSupportedSurfaceCombinationsQuirk;
import androidx.camera.extensions.internal.compat.quirk.ImageAnalysisUnavailableQuirk;

/* JADX INFO: loaded from: classes.dex */
public class ImageAnalysisAvailability {
    ImageAnalysisUnavailableQuirk mImageAnalysisUnavailableQuirk = (ImageAnalysisUnavailableQuirk) DeviceQuirks.get(ImageAnalysisUnavailableQuirk.class);
    ExtraSupportedSurfaceCombinationsQuirk mExtraSupportedSurfaceCombinationsQuirk = (ExtraSupportedSurfaceCombinationsQuirk) DeviceQuirks.get(ExtraSupportedSurfaceCombinationsQuirk.class);

    public boolean isAvailable(String str, int i, int i2, boolean z, boolean z2) {
        ImageAnalysisUnavailableQuirk imageAnalysisUnavailableQuirk = this.mImageAnalysisUnavailableQuirk;
        if (imageAnalysisUnavailableQuirk != null && imageAnalysisUnavailableQuirk.isUnavailable(str, i2)) {
            return false;
        }
        if (this.mExtraSupportedSurfaceCombinationsQuirk != null) {
            return true;
        }
        if (z || z2) {
            return (!z || z2) ? i == 1 || i == 3 : i == 0 || i == 1 || i == 3;
        }
        return true;
    }
}

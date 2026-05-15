package androidx.camera.extensions.internal.sessionprocessor;

import android.view.Surface;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class SurfaceOutputConfig implements Camera2OutputConfig {
    abstract Surface getSurface();

    static SurfaceOutputConfig create(int i, int i2, String str, List<Camera2OutputConfig> list, Surface surface) {
        return new AutoValue_SurfaceOutputConfig(i, i2, str, list, surface);
    }

    static SurfaceOutputConfig create(int i, Surface surface) {
        return create(i, -1, null, Collections.emptyList(), surface);
    }
}

package androidx.camera.extensions.internal.sessionprocessor;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class MultiResolutionImageReaderOutputConfig implements Camera2OutputConfig {
    abstract int getImageFormat();

    abstract int getMaxImages();

    static MultiResolutionImageReaderOutputConfig create(int i, int i2, String str, List<Camera2OutputConfig> list, int i3, int i4) {
        return new AutoValue_MultiResolutionImageReaderOutputConfig(i, i2, str, list, i3, i4);
    }
}

package androidx.camera.extensions.internal.sessionprocessor;

import android.media.Image;

/* JADX INFO: loaded from: classes.dex */
public interface ImageReference {
    boolean decrement();

    Image get();

    boolean increment();
}

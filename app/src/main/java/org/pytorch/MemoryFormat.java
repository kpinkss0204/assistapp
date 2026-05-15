package org.pytorch;

/* JADX INFO: loaded from: classes4.dex */
public enum MemoryFormat {
    CONTIGUOUS(1),
    CHANNELS_LAST(2),
    CHANNELS_LAST_3D(3);

    final int jniCode;

    MemoryFormat(int i) {
        this.jniCode = i;
    }
}

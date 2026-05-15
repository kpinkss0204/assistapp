package org.pytorch;

/* JADX INFO: loaded from: classes4.dex */
public enum DType {
    UINT8(1),
    INT8(2),
    INT32(3),
    FLOAT32(4),
    INT64(5),
    FLOAT64(6);

    final int jniCode;

    DType(int i) {
        this.jniCode = i;
    }
}

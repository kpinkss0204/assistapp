package org.pytorch;

/* JADX INFO: loaded from: classes4.dex */
public enum Device {
    CPU(1),
    VULKAN(2);

    final int jniCode;

    Device(int i) {
        this.jniCode = i;
    }
}

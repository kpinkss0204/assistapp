package com.kakao.vectormap.internal;

/* JADX INFO: compiled from: EngineHandler.java */
/* JADX INFO: loaded from: classes4.dex */
enum DeviceType {
    Phone(0),
    Watch(1),
    PC(2);

    private final int value;

    DeviceType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

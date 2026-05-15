package com.kakao.vectormap.shape;

/* JADX INFO: loaded from: classes4.dex */
public enum PolylineCap {
    Round(0),
    Square(1),
    Butt(2);

    private final int value;

    PolylineCap(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

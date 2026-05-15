package com.kakao.vectormap.label;

/* JADX INFO: loaded from: classes4.dex */
public enum OrderingType {
    Rank(0),
    LeftBottom(1);

    private final int value;

    OrderingType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

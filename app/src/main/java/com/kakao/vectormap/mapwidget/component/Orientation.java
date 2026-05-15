package com.kakao.vectormap.mapwidget.component;

/* JADX INFO: loaded from: classes4.dex */
public enum Orientation {
    Horizontal(0),
    Vertical(1);

    private final int value;

    Orientation(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Orientation getEnum(int i) {
        if (i == 0) {
            return Horizontal;
        }
        if (i == 1) {
            return Vertical;
        }
        return Horizontal;
    }
}

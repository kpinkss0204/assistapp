package com.kakao.vectormap.mapwidget.component;

/* JADX INFO: loaded from: classes4.dex */
public enum Vertical {
    Top(0),
    Center(1),
    Bottom(2);

    private final int value;

    Vertical(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Vertical getEnum(int i) {
        if (i == 0) {
            return Top;
        }
        if (i == 1) {
            return Center;
        }
        if (i == 2) {
            return Bottom;
        }
        return Center;
    }
}

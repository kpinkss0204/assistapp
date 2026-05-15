package com.kakao.vectormap.mapwidget.component;

/* JADX INFO: loaded from: classes4.dex */
public enum Horizontal {
    Left(0),
    Center(1),
    Right(2);

    private final int value;

    Horizontal(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Horizontal getEnum(int i) {
        if (i == 0) {
            return Left;
        }
        if (i == 1) {
            return Center;
        }
        if (i == 2) {
            return Right;
        }
        return Center;
    }
}

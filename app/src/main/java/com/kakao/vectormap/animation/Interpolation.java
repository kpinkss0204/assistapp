package com.kakao.vectormap.animation;

/* JADX INFO: loaded from: classes4.dex */
public enum Interpolation {
    Linear(0),
    CubicIn(4),
    CubicOut(5),
    CubicInOut(6);

    private final int value;

    Interpolation(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Interpolation getType(int i) {
        Interpolation interpolation = Linear;
        if (i == interpolation.getValue()) {
            return interpolation;
        }
        Interpolation interpolation2 = CubicIn;
        if (i == interpolation2.getValue()) {
            return interpolation2;
        }
        Interpolation interpolation3 = CubicOut;
        if (i == interpolation3.getValue()) {
            return interpolation3;
        }
        Interpolation interpolation4 = CubicInOut;
        if (i == interpolation4.getValue()) {
            return interpolation4;
        }
        throw new RuntimeException("Interpolation invalid type.");
    }
}

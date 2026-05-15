package com.kakao.vectormap.label;

/* JADX INFO: loaded from: classes4.dex */
public enum TransformMethod {
    None(-1),
    AbsoluteRotation(0),
    Default(1),
    AbsoluteRotation_KeepUpright(2),
    AbsoluteRotation_Decal(3),
    Decal(4);

    private final int value;

    TransformMethod(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static TransformMethod getEnum(int i) {
        if (i == 0) {
            return Default;
        }
        if (i == 1) {
            return AbsoluteRotation;
        }
        if (i == 2) {
            return AbsoluteRotation_KeepUpright;
        }
        if (i == 3) {
            return AbsoluteRotation_Decal;
        }
        if (i == 4) {
            return Decal;
        }
        return None;
    }
}

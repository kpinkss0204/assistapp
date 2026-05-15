package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum MapCoordType {
    WCONG(0),
    WTM(1),
    WGS84(3),
    Undefined(5);

    private final int value;

    MapCoordType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static MapCoordType getEnum(int i) {
        if (i == 0) {
            return WCONG;
        }
        if (i == 1) {
            return WTM;
        }
        if (i == 3) {
            return WGS84;
        }
        return Undefined;
    }
}

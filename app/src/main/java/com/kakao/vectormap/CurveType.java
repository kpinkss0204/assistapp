package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum CurveType {
    None(0),
    LeftCurve(1),
    RightCurve(2);

    private final int value;

    CurveType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static CurveType getEnum(int i) {
        CurveType curveType = None;
        if (i == curveType.getValue()) {
            return curveType;
        }
        CurveType curveType2 = LeftCurve;
        if (i == curveType2.getValue()) {
            return curveType2;
        }
        CurveType curveType3 = RightCurve;
        if (i == curveType3.getValue()) {
            return curveType3;
        }
        MapLogger.e("CurveType getEnum failure. invalid value.");
        return curveType;
    }
}

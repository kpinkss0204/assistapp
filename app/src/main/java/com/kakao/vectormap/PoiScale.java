package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum PoiScale {
    SMALL(0),
    REGULAR(1),
    LARGE(2),
    XLARGE(3);

    private final int value;

    PoiScale(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static PoiScale getEnum(int i) {
        PoiScale poiScale = SMALL;
        if (i == poiScale.getValue()) {
            return poiScale;
        }
        PoiScale poiScale2 = REGULAR;
        if (i == poiScale2.getValue()) {
            return poiScale2;
        }
        PoiScale poiScale3 = LARGE;
        if (i == poiScale3.getValue()) {
            return poiScale3;
        }
        PoiScale poiScale4 = XLARGE;
        if (i == poiScale4.getValue()) {
            return poiScale4;
        }
        MapLogger.e("PoiScale getEnum failure. invalid value.");
        return poiScale2;
    }
}

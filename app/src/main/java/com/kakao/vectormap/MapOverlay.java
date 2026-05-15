package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum MapOverlay {
    ROADVIEW_LINE("roadview_line"),
    HILLSHADING("hill_shading"),
    BICYCLE_ROAD("bicycle_road"),
    SKYVIEW_HYBRID("hybrid");

    private final String value;

    MapOverlay(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static MapOverlay getEnum(String str) {
        if (str == null) {
            throw new RuntimeException("MapOverlay.getEnum value is null.");
        }
        MapOverlay mapOverlay = ROADVIEW_LINE;
        if (str.equals(mapOverlay.getValue())) {
            return mapOverlay;
        }
        MapOverlay mapOverlay2 = HILLSHADING;
        if (str.equals(mapOverlay2.getValue())) {
            return mapOverlay2;
        }
        MapOverlay mapOverlay3 = BICYCLE_ROAD;
        if (str.equals(mapOverlay3.getValue())) {
            return mapOverlay3;
        }
        MapOverlay mapOverlay4 = SKYVIEW_HYBRID;
        if (str.equals(mapOverlay4.getValue())) {
            return mapOverlay4;
        }
        throw new RuntimeException("MapOverlay, No Matching Value(" + str + ")");
    }
}

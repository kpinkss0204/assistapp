package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum MapType {
    NORMAL("map"),
    SKYVIEW("skyview");

    private final String value;

    MapType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static MapType getEnum(String str) {
        if (str == null) {
            throw new RuntimeException("MapType.getEnum value is null.");
        }
        MapType mapType = NORMAL;
        if (str.equals(mapType.getValue())) {
            return mapType;
        }
        MapType mapType2 = SKYVIEW;
        if (str.equals(mapType2.getValue())) {
            return mapType2;
        }
        throw new RuntimeException("MapType, No Matching Value(" + str + ")");
    }
}

package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum PoiCategory {
    POLICY_CATEGORY(0),
    ADDRESS(1),
    DISTRICT(2),
    BUS_STOP(3),
    RAILROAD_PLATFORM(4),
    RAILROAD_ENTRANCE(5),
    RAILROAD_LINE(6),
    BRAND(7),
    PLACE(8),
    CLIMB(9),
    CLIMB_WAVE(10),
    ROAD(11),
    ROAD_NUMBER(12),
    ROAD_INFO(13),
    FACILITIES(14),
    PUBLIC_FACILITIES(15),
    APART_NUMBER(16),
    APART_DETAIL(17),
    HOUSE_NUMBER(18),
    LAND(19),
    INDOOR(20),
    INDOOR_PLACE(21),
    DETAIL_CATEGORY(1000);

    private final int value;

    PoiCategory(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static PoiCategory getEnum(int i) {
        for (PoiCategory poiCategory : values()) {
            if (poiCategory.getValue() == i) {
                return poiCategory;
            }
        }
        throw new RuntimeException("PoiCategory, No Matching Value(" + i + ")");
    }
}

package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class Poi {
    public final boolean isPoi;
    public final String layerId;
    public final String name;
    public final String poiId;

    public Poi(boolean z) {
        this.isPoi = z;
        this.layerId = "";
        this.poiId = "";
        this.name = "";
    }

    public Poi(boolean z, String str, String str2, String str3) {
        this.isPoi = z;
        this.layerId = str2;
        this.poiId = str;
        this.name = str3;
    }

    public String getPoiId() {
        return this.poiId;
    }

    public boolean isPoi() {
        return this.isPoi;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public String getName() {
        return this.name;
    }
}

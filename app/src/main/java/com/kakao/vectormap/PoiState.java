package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class PoiState {
    private final boolean isClickable;
    private final boolean isVisible;
    private final String poiLanguage;
    private final PoiScale poiScale;
    private final int poiVisibleMaxLevel;

    public PoiState(boolean z, boolean z2, PoiScale poiScale, String str, int i) {
        this.isVisible = z;
        this.isClickable = z2;
        this.poiScale = poiScale;
        this.poiLanguage = str;
        this.poiVisibleMaxLevel = i;
    }

    public PoiScale getPoiScale() {
        return this.poiScale;
    }

    public String getPoiLanguage() {
        return this.poiLanguage;
    }

    public boolean isClickable() {
        return this.isClickable;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public String toString() {
        return "PoiState{isClickable=" + this.isClickable + ", isVisible=" + this.isVisible + ", poiScale=" + this.poiScale + ", poiLanguage='" + this.poiLanguage + '}';
    }
}

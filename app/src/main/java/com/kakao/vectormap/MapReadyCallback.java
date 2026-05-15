package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MapReadyCallback {
    private LatLng position;
    private final String viewName;
    private int zoomLevel = 15;
    private Object tag = "";
    private boolean isDev = false;
    private boolean isVisible = true;
    private int timeout = 5000;

    MapReadyCallback(String str) {
        this.viewName = str;
    }

    public boolean isDev() {
        return this.isDev;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public String getViewName() {
        return this.viewName;
    }

    public Object getTag() {
        return this.tag;
    }

    public LatLng getPosition() {
        return this.position;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public int getTimeout() {
        return this.timeout;
    }
}

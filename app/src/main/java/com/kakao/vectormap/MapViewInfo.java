package com.kakao.vectormap;

import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class MapViewInfo {
    protected static final String DEFAULT_APP_NAME = "openmap";
    protected static final String DEFAULT_MAP_STYLE = "default";
    protected static final MapType DEFAULT_MAP_TYPE = MapType.NORMAL;
    private final String appName;
    private String mapStyle;
    private String mapType;

    MapViewInfo(String str, String str2) {
        if (MapUtils.isNullOrEmpty(str)) {
            MapLogger.w("MapViewInfo appName param is null or empty. Replace with default values.");
            this.appName = DEFAULT_APP_NAME;
        } else {
            this.appName = str;
        }
        if (str2 == null) {
            MapLogger.w("ViewInfo MapType param is null. Replace with default values.");
            this.mapType = DEFAULT_MAP_TYPE.getValue();
        } else {
            this.mapType = str2;
        }
        this.mapStyle = DEFAULT_MAP_STYLE;
    }

    MapViewInfo(String str, MapType mapType) {
        if (MapUtils.isNullOrEmpty(str)) {
            MapLogger.w("MapViewInfo appName param is null or empty. Replace with default values.");
            this.appName = DEFAULT_APP_NAME;
        } else {
            this.appName = str;
        }
        if (mapType == null) {
            MapLogger.w("ViewInfo MapType param is null. Replace with default values.");
            this.mapType = DEFAULT_MAP_TYPE.getValue();
        } else {
            this.mapType = mapType.getValue();
        }
        this.mapStyle = DEFAULT_MAP_STYLE;
    }

    public static MapViewInfo from(String str) {
        return new MapViewInfo(str, DEFAULT_MAP_TYPE);
    }

    public static MapViewInfo from(String str, MapType mapType) {
        return new MapViewInfo(str, mapType);
    }

    public static MapViewInfo from(String str, String str2) {
        return new MapViewInfo(str, str2);
    }

    public MapViewInfo setMapStyle(String str) {
        try {
            if (MapUtils.isNullOrEmpty(str)) {
                str = DEFAULT_MAP_STYLE;
            }
            this.mapStyle = str;
            return this;
        } catch (Exception e) {
            MapLogger.e(e.getMessage());
            return this;
        }
    }

    public MapViewInfo setMapType(String str) {
        this.mapType = str;
        return this;
    }

    public MapViewInfo setMapType(MapType mapType) {
        try {
            this.mapType = mapType.getValue();
            return this;
        } catch (Exception e) {
            MapLogger.e(e.getMessage());
            return this;
        }
    }

    public String getMapType() {
        return this.mapType;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getMapStyle() {
        return this.mapStyle;
    }

    public String toString() {
        return "MapViewInfo{appName='" + this.appName + "', mapType='" + this.mapType + "', mapStyle='" + this.mapStyle + "'}";
    }
}

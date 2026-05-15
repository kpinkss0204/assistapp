package com.kakao.vectormap;

import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class RoadViewInfo {
    public static final String DEFAULT_APP_NAME = "roadview";
    public static final String DEFAULT_NAME = "roadview";
    private final String appName;
    private final String style;
    private final String viewInfoName;

    RoadViewInfo(String str, String str2) {
        if (MapUtils.isNullOrEmpty(str)) {
            MapLogger.w("RoadViewInfo appName param is null or empty. Replace with default values.");
            this.appName = "roadview";
        } else {
            this.appName = str;
        }
        if (MapUtils.isNullOrEmpty(str)) {
            MapLogger.w("RoadViewInfo viewInfoName param is null or empty. Replace with default values.");
            this.viewInfoName = "roadview";
        } else {
            this.viewInfoName = str;
        }
        this.style = "default";
    }

    public static RoadViewInfo from(String str, String str2) {
        return new RoadViewInfo(str, str2);
    }

    public String getAppName() {
        return this.appName;
    }

    public String getViewInfoName() {
        return this.viewInfoName;
    }

    public String getStyle() {
        return this.style;
    }
}

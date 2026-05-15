package com.kakao.vectormap.internal;

import com.kakao.vectormap.Coordinate;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapCoordType;

/* JADX INFO: loaded from: classes4.dex */
public class RenderViewController {
    static native void addFont(long j, String str, String str2, byte[] bArr);

    static native void changeViewInfo(long j, String str, boolean z, String str2, String str3, String str4);

    static native void clearAllCache(long j, String str);

    static native void clearDiskCache(long j, String str);

    static native void disableFixedCenterPoint(long j, String str, int[] iArr);

    static native float distance(long j, double d, double d2, double d3, double d4);

    static native void enableFixedCenterPoint(long j, String str, int[] iArr);

    static native int getClickIntervalTime(long j, String str);

    static native float getMapDpScale(long j, String str);

    static native float getMapHeightScale(long j, String str);

    static native int getMaxLevel(long j, String str);

    static native int getMinLevel(long j, String str);

    static native String getPoiLanguage(long j, String str);

    static native int getPoiScale(long j, String str);

    static native int getPoiVisibleMaxLevel(long j, String str);

    static native String[] getSupportedLanguages(long j, String str);

    static native void hideOverlayMap(long j, String str, String str2, String str3, boolean z);

    static native boolean isMapClickable(long j, String str);

    static native boolean isPoiClickable(long j, String str);

    static native boolean isPoiVisible(long j, String str);

    public static native LatLng[] makeCurvePoints(double d, double d2, double d3, double d4, int i);

    static native void requestPoiState(long j, String str, String str2);

    static native void resetPoiVisibleMaxCount(long j, String str, int i);

    static native void setCameraMoveEndListener(long j, String str, boolean z);

    static native void setCameraMoveStartListener(long j, String str, boolean z);

    static native void setClickIntervalTime(long j, String str, int i);

    static native void setCompassBackToNorth(long j, String str, boolean z);

    static native void setCompassPosition(long j, String str, int i, float f, float f2);

    static native void setCompassVisible(long j, String str, boolean z);

    static native void setEventListener(long j, String str, EventListener eventListener);

    static native void setGuiClickListener(long j, String str, boolean z);

    static native void setLogoPosition(long j, String str, int i, float f, float f2);

    static native void setMapGestureEnable(long j, String str, int i, boolean z);

    static native void setMapHeightScale(long j, String str, float f);

    static native void setMapResizeListener(long j, String str, boolean z);

    static native void setPaddingResizeListener(long j, String str, boolean z);

    static native void setPoiClickListener(long j, String str, boolean z);

    static native void setPoiClickable(long j, String str, boolean z);

    static native boolean setPoiLanguage(long j, String str, String str2);

    static native void setPoiScale(long j, String str, int i);

    static native void setPoiVisible(long j, String str, boolean z);

    static native void setPoiVisibleMaxCount(long j, String str, int i, int i2);

    static native void setPoiVisibleMaxLevel(long j, String str, int i);

    static native void setRenderViewClickListener(long j, String str, boolean z);

    static native void setScaleBarAutoHide(long j, String str, boolean z);

    static native void setScaleBarFadeInOutOption(long j, String str, int i, int i2, int i3);

    static native void setScaleBarOptions(long j, String str, boolean z, int i, int i2, int i3);

    static native void setScaleBarPosition(long j, String str, int i, float f, float f2);

    static native void setScaleBarVisible(long j, String str, boolean z);

    static native void setTerrainClickListener(long j, String str, boolean z);

    static native void setTrackingRoll(long j, String str, boolean z);

    static native void setViewInfoChangeListener(long j, String str, boolean z);

    static native void setVisible(long j, String str, boolean z);

    static native void setVisibleChangeListener(long j, String str, boolean z);

    static native void setVisiblePoiCategory(long j, String str, int i, boolean z);

    static native void setZoneEventListener(long j, String str, boolean z);

    static native void showOverlayMap(long j, String str, String str2, String str3, boolean z);

    static native void startTrackingLabel(long j, String str, String str2, String str3);

    static native void stopTracking(long j, String str);

    public static native LatLng toLatLngFromWCong(double d, double d2);

    public static native LatLng toLatLngFromWTM(double d, double d2);

    public static native Coordinate toWCongFromLatLng(double d, double d2);

    public static native Coordinate toWTMFromLatLng(double d, double d2);

    public static Coordinate toWCongFromWTM(double d, double d2) {
        return new Coordinate(d * 2.5d, d2 * 2.5d, MapCoordType.WCONG);
    }

    public static Coordinate toWTMFromWCong(double d, double d2) {
        return new Coordinate(d * 0.4d, d2 * 0.4d, MapCoordType.WTM);
    }
}

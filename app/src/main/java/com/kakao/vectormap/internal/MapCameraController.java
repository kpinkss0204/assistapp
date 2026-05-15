package com.kakao.vectormap.internal;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.camera.CameraPosition;

/* JADX INFO: loaded from: classes4.dex */
class MapCameraController {
    static native boolean canShowMapPoints(long j, String str, double d, double d2, double d3, double d4, int i);

    static native void fitMapPoints(long j, String str, double d, double d2, double d3, double d4, int i, int i2, boolean z, boolean z2, boolean z3, int i3);

    static native int getCameraMaxLevel(long j, String str);

    static native int getCameraMinLevel(long j, String str);

    static native CameraPosition getCameraPosition(long j, String str);

    static native LatLng getMapPoint(long j, String str, int i, int i2);

    static native int getMaxZoomLevel(long j, String str);

    static native int getMinZoomLevel(long j, String str);

    static native int[] getScreenPoint(long j, String str, double d, double d2);

    static native int getZoomLevel(long j, String str);

    static native void nativeInit();

    static native void newCameraPosition(long j, String str, double d, double d2, int i, double d3, double d4, double d5, boolean z, boolean z2, boolean z3, int i2);

    static native void newCenterPoint(long j, String str, double d, double d2, int i, boolean z, boolean z2, boolean z3, int i2);

    static native void requestCameraPosition(long j, String str, String str2);

    static native void rotateTo(long j, String str, double d, boolean z, boolean z2, int i);

    static native void setCameraMaxLevel(long j, String str, int i);

    static native void setCameraMinLevel(long j, String str, int i);

    static native void setEnableCameraAnimation(long j, String str, boolean z);

    static native void setVirtualViewport(long j, String str, float f, float f2, float f3, float f4);

    static native void tiltTo(long j, String str, double d, boolean z, boolean z2, int i);

    static native void zoomIn(long j, String str, boolean z, boolean z2, int i);

    static native void zoomOut(long j, String str, boolean z, boolean z2, int i);

    static native void zoomTo(long j, String str, int i, boolean z, boolean z2, int i2);

    MapCameraController() {
    }

    static {
        nativeInit();
    }
}

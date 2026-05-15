package com.kakao.vectormap.internal;

import com.kakao.vectormap.RoadViewRequest;

/* JADX INFO: loaded from: classes4.dex */
public class RoadViewController {
    static native double getRoadViewPanAngle(long j, String str);

    static native double getRoadViewTiltAngle(long j, String str);

    static native void linkMapToRoadView(long j, String str, String str2);

    static native void moveToRoadView(long j, String str, int i);

    static native void requestNextRoadView(long j, String str, String str2, double d, double d2, int i, int i2, int i3);

    static native void requestRoadView(long j, String str, String str2, double d, double d2, int i, int i2, double d3, double d4, double d5, double d6, int i3, int i4, RoadViewRequest.Marker[] markerArr, byte[][] bArr);

    static native void setEventListener(long j, String str, RoadViewEventListener roadViewEventListener);

    static native void setLogoPosition(long j, String str, int i, float f, float f2);

    static native void setRenderViewClickListener(long j, String str, boolean z);

    static native void setSearchRange(long j, String str, int i, int i2);

    static native void setViewportResizeListener(long j, String str, boolean z);

    static native void unlinkMapFromRoadView(long j, String str);
}

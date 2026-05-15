package com.kakao.vectormap.zone;

import android.graphics.PointF;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IZoneDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class ZoneManager {
    private final IZoneDelegate delegate;

    public ZoneManager(IZoneDelegate iZoneDelegate) {
        this.delegate = iZoneDelegate;
    }

    public synchronized void showDetailZone(ZoneEvent zoneEvent) {
        try {
            this.delegate.showDetailZone(zoneEvent.getZoneType(), zoneEvent.getZoneId(), zoneEvent.getDetailZoneId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showDetailZone(String str, String str2, String str3) {
        try {
            this.delegate.showDetailZone(str, str2, str3);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideDetailZone(String str) {
        try {
            this.delegate.hideDetailZone(str);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setZoneBoundScale(String str, int i, PointF pointF) {
        try {
            this.delegate.setZoneBoundScale(str, i, pointF.x, pointF.y);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}

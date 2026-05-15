package com.kakao.vectormap.label;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ITrackingDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class TrackingManager {
    private ITrackingDelegate delegate;

    public TrackingManager(ITrackingDelegate iTrackingDelegate) {
        this.delegate = iTrackingDelegate;
    }

    public void startTracking(Label label) {
        try {
            this.delegate.startTracking(label);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void stopTracking() {
        try {
            this.delegate.stopTracking();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setTrackingRotation(boolean z) {
        try {
            this.delegate.setTrackingRotation(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}

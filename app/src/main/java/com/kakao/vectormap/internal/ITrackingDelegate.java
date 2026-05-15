package com.kakao.vectormap.internal;

import com.kakao.vectormap.label.Label;

/* JADX INFO: loaded from: classes4.dex */
public interface ITrackingDelegate {
    void setTrackingRotation(boolean z) throws RuntimeException;

    void startTracking(Label label) throws RuntimeException;

    void stopTracking() throws RuntimeException;
}

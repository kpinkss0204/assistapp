package com.kakao.vectormap;

import com.kakao.vectormap.internal.IKakaoMapDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleBar {
    private boolean autoHide;
    private IKakaoMapDelegate delegate;
    private int fadeInTime;
    private int fadeOutTime;
    private int retentionTime;
    private Object tag;

    public ScaleBar(IKakaoMapDelegate iKakaoMapDelegate) {
        this.autoHide = true;
        this.fadeInTime = 1000;
        this.fadeOutTime = 1000;
        this.retentionTime = 5000;
        this.delegate = iKakaoMapDelegate;
    }

    public ScaleBar(IKakaoMapDelegate iKakaoMapDelegate, boolean z) {
        this.fadeInTime = 1000;
        this.fadeOutTime = 1000;
        this.retentionTime = 5000;
        this.delegate = iKakaoMapDelegate;
        this.autoHide = z;
    }

    public synchronized void setAutoHide(boolean z) {
        try {
            this.delegate.setScaleBarAutoHide(z);
            this.autoHide = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isAutoHide() {
        return this.autoHide;
    }

    public void show() {
        try {
            this.delegate.setScaleBarVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void hide() {
        try {
            this.delegate.setScaleBarVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setPosition(int i, float f, float f2) {
        try {
            this.delegate.setScaleBarPosition(i, f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setFadeInOutTime(int i, int i2, int i3) {
        try {
            this.delegate.setScaleBarFadeInOutTime(i, i2, i3);
            this.fadeInTime = i;
            this.fadeOutTime = i2;
            this.retentionTime = i3;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getFadeInTime() {
        return this.fadeInTime;
    }

    public synchronized int getFadeOutTime() {
        return this.fadeOutTime;
    }

    public synchronized int getRetentionTime() {
        return this.retentionTime;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }
}

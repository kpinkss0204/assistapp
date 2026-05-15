package com.kakao.vectormap;

import com.kakao.vectormap.internal.IKakaoMapDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class Compass {
    private boolean backToNorthOnClick;
    private IKakaoMapDelegate delegate;
    private Object tag;

    public Compass(IKakaoMapDelegate iKakaoMapDelegate) {
        this.backToNorthOnClick = true;
        this.delegate = iKakaoMapDelegate;
    }

    public Compass(IKakaoMapDelegate iKakaoMapDelegate, boolean z) {
        this.delegate = iKakaoMapDelegate;
        this.backToNorthOnClick = z;
    }

    public void show() {
        try {
            this.delegate.setCompassVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void hide() {
        try {
            this.delegate.setCompassVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setPosition(int i, float f, float f2) {
        try {
            this.delegate.setCompassPosition(i, f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isBackToNorthOnClick() {
        return this.backToNorthOnClick;
    }

    public synchronized void setBackToNorthOnClick(boolean z) {
        try {
            this.delegate.setCompassBackToNorth(z);
            this.backToNorthOnClick = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }
}

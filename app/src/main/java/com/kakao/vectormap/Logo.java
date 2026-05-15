package com.kakao.vectormap;

import com.kakao.vectormap.internal.RenderViewDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class Logo {
    private RenderViewDelegate delegate;
    private Object tag;

    public Logo(RenderViewDelegate renderViewDelegate) {
        this.delegate = renderViewDelegate;
    }

    public void setPosition(int i, float f, float f2) {
        try {
            this.delegate.setLogoPosition(i, f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }
}

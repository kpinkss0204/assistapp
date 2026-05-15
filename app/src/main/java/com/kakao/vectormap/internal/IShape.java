package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IShape {
    private boolean visible;

    protected IShape(boolean z) {
        this.visible = z;
    }

    protected void setVisible(boolean z) {
        synchronized (this) {
            this.visible = z;
        }
    }

    protected boolean isVisible() {
        boolean z;
        synchronized (this) {
            z = this.visible;
        }
        return z;
    }
}

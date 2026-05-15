package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ILabel {
    protected final ILabelDelegate delegate;
    protected final String labelId;
    protected boolean visible;

    protected ILabel(ILabelDelegate iLabelDelegate, String str, boolean z) {
        this.delegate = iLabelDelegate;
        this.labelId = str;
        this.visible = z;
    }

    public String getLabelId() {
        return this.labelId;
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

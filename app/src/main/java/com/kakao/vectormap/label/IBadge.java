package com.kakao.vectormap.label;

import android.graphics.PointF;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IBadge {
    protected final ILabelDelegate delegate;
    protected final String groupId;
    protected final String id;
    protected final boolean isLod;
    protected final String labelId;
    protected PointF offset;
    protected Object tag;
    protected boolean visible = false;
    protected int zOrder;

    IBadge(ILabelDelegate iLabelDelegate, String str, boolean z, String str2, String str3, int i, Object obj, PointF pointF) {
        this.delegate = iLabelDelegate;
        this.id = str;
        this.isLod = z;
        this.groupId = str2;
        this.labelId = str3;
        this.zOrder = i;
        this.tag = obj;
        this.offset = pointF;
    }

    public String getId() {
        return this.id;
    }

    public String getLayerId() {
        return this.groupId;
    }

    public String getLabelId() {
        return this.labelId;
    }

    public synchronized void show() {
        try {
            this.delegate.setBadgeVisible(this.isLod, this.groupId, this.labelId, this.id, true);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setBadgeVisible(this.isLod, this.groupId, this.labelId, this.id, false);
            this.visible = false;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized PointF getOffset() {
        return this.offset;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public void remove() {
        try {
            this.delegate.removeBadge(this.isLod, this.groupId, this.labelId, this.id);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }
}

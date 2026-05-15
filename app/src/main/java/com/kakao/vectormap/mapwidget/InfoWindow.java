package com.kakao.vectormap.mapwidget;

import android.graphics.PointF;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IGuiDelegate;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiView;

/* JADX INFO: loaded from: classes4.dex */
public class InfoWindow {
    private boolean applyDpScale;
    private GuiView body;
    private PointF bodyOffset;
    private final IGuiDelegate delegate;
    private String id;
    private LatLng position;
    private Object tag;
    private GuiImage tail;
    private PointF tailOffset;
    private boolean visible;
    private int zOrder;

    InfoWindow(IGuiDelegate iGuiDelegate, LatLng latLng, String str, boolean z, int i, boolean z2, PointF pointF, PointF pointF2, GuiView guiView, GuiImage guiImage, Object obj) {
        this.id = str;
        this.zOrder = i;
        this.delegate = iGuiDelegate;
        this.position = latLng;
        this.visible = z;
        this.applyDpScale = z2;
        this.bodyOffset = new PointF(pointF.x, pointF.y);
        this.tailOffset = new PointF(pointF2.x, pointF2.y);
        this.body = guiView;
        this.tail = guiImage;
        this.tag = obj;
    }

    public String getId() {
        return this.id;
    }

    public InfoWindowLayer getLayer() {
        return this.delegate.getInfoWindowLayer();
    }

    public synchronized LatLng getPosition() {
        return this.position;
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized void show() {
        try {
            this.delegate.setVisible(this.id, true, false, 0);
            this.visible = true;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void show(boolean z) {
        try {
            this.delegate.setVisible(this.id, true, z, 300);
            this.visible = true;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void show(boolean z, int i) {
        try {
            this.delegate.setVisible(this.id, true, z, i);
            this.visible = true;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setVisible(this.id, false, false, 0);
            this.visible = false;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setZOrder(int i) {
        try {
            this.delegate.setZOrder(this.id, i);
            this.zOrder = i;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public synchronized boolean isApplyDpScale() {
        return this.applyDpScale;
    }

    public synchronized PointF getBodyOffset() {
        return this.bodyOffset;
    }

    public synchronized PointF getTailOffset() {
        return this.tailOffset;
    }

    public synchronized GuiView getBody() {
        return this.body;
    }

    public synchronized GuiImage getTail() {
        return this.tail;
    }

    public synchronized void setBodyOffset(PointF pointF) {
        this.bodyOffset = pointF;
    }

    public synchronized void setTailOffset(PointF pointF) {
        this.tailOffset = pointF;
    }

    public synchronized void setBody(GuiView guiView) {
        this.body = guiView;
    }

    public synchronized void setTail(GuiImage guiImage) {
        this.tail = guiImage;
    }

    public synchronized void changeBody(GuiView guiView) {
        try {
            this.delegate.update(this.id, this.position, guiView, this.tail, this.bodyOffset, this.tailOffset, this.visible, this.applyDpScale);
            this.body = guiView;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void update() {
        try {
            this.delegate.update(this.id, this.position, this.body, this.tail, this.bodyOffset, this.tailOffset, this.visible, this.applyDpScale);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveTo(LatLng latLng) {
        try {
            this.delegate.moveTo(this.id, latLng, 0L);
            this.position = latLng;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveTo(LatLng latLng, long j) {
        try {
            this.delegate.moveTo(this.id, latLng, j);
            this.position = latLng;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove() {
        try {
            this.delegate.getInfoWindowLayer().remove(this);
        } catch (Exception e) {
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

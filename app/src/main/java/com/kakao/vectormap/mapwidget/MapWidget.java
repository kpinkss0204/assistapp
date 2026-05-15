package com.kakao.vectormap.mapwidget;

import android.graphics.PointF;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IGuiDelegate;
import com.kakao.vectormap.mapwidget.component.GuiView;

/* JADX INFO: loaded from: classes4.dex */
public class MapWidget {
    private IGuiDelegate delegate;
    private String id;
    private int mapGravity;
    private PointF position;
    private GuiView rootView;
    private float rotation;
    private Object tag;
    private boolean visible;
    private int zOrder;

    MapWidget(IGuiDelegate iGuiDelegate, String str, int i, PointF pointF, float f, boolean z, int i2, GuiView guiView, Object obj) {
        this.id = str;
        this.delegate = iGuiDelegate;
        this.mapGravity = i;
        this.rotation = f;
        this.position = new PointF(pointF.x, pointF.y);
        this.visible = z;
        this.zOrder = i2;
        this.rootView = guiView;
        this.tag = obj;
    }

    public String getId() {
        return this.id;
    }

    public GuiView getRootView() {
        return this.rootView;
    }

    public int getMapGravity() {
        return this.mapGravity;
    }

    public synchronized float getRotation() {
        return this.rotation;
    }

    public synchronized PointF getPosition() {
        return this.position;
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized void show() {
        try {
            this.delegate.setMapWidgetVisible(this.id, true);
            this.visible = true;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setMapWidgetVisible(this.id, false);
            this.visible = false;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setPosition(int i, float f, float f2) {
        try {
            this.delegate.setMapWidgetPosition(this.id, i, f, f2);
            this.position = new PointF(f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setRotation(float f) {
        try {
            this.delegate.setMapWidgetRotation(this.id, f);
            this.rotation = f;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setZOrder(int i) {
        try {
            this.delegate.setMapWidgetZOrder(this.id, i);
            this.zOrder = i;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }
}

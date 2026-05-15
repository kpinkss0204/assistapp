package com.kakao.vectormap.mapwidget;

import android.graphics.PointF;
import com.kakao.vectormap.mapwidget.component.GuiView;
import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class MapWidgetOptions {
    private int gravity;
    private String id;
    private PointF position;
    private GuiView rootView;
    private Object tag;
    private boolean visible = true;
    private int zOrder = 0;
    private float rotation = 0.0f;

    MapWidgetOptions(String str, int i, int i2, int i3) {
        this.id = MapUtils.getUniqueId(str);
        this.gravity = i;
        this.position = new PointF(i2, i3);
    }

    public static MapWidgetOptions from(String str) {
        return new MapWidgetOptions(str, 5, 0, 0);
    }

    public static MapWidgetOptions from(int i, int i2, int i3) {
        return new MapWidgetOptions("", i, i2, i3);
    }

    public static MapWidgetOptions from(String str, int i, int i2, int i3) {
        return new MapWidgetOptions(str, i, i2, i3);
    }

    public MapWidgetOptions setRootView(GuiView guiView) {
        this.rootView = guiView;
        return this;
    }

    public MapWidgetOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public MapWidgetOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public MapWidgetOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public MapWidgetOptions setRotation(float f) {
        this.rotation = f;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public int getMapGravity() {
        return this.gravity;
    }

    public PointF getPosition() {
        return this.position;
    }

    public GuiView getRootView() {
        return this.rootView;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public float getRotation() {
        return this.rotation;
    }

    public Object getTag() {
        return this.tag;
    }
}

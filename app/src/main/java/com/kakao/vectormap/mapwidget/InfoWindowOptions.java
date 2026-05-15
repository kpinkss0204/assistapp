package com.kakao.vectormap.mapwidget;

import android.graphics.Point;
import android.graphics.PointF;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiView;
import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class InfoWindowOptions {
    public GuiView body;
    public String id;
    public LatLng position;
    public Object tag;
    public GuiImage tail;
    public boolean visible = true;
    public PointF bodyOffset = new PointF(0.0f, 0.0f);
    public PointF tailOffset = new PointF(0.0f, 0.0f);
    public boolean applyDpScale = true;
    public boolean autoMove = false;
    public int autoMoveDuration = 300;
    private int zOrder = 0;

    InfoWindowOptions(String str, LatLng latLng) {
        this.id = MapUtils.getUniqueId(str);
        this.position = latLng;
    }

    public static InfoWindowOptions from(LatLng latLng) {
        return new InfoWindowOptions("", latLng);
    }

    public static InfoWindowOptions from(String str, LatLng latLng) {
        return new InfoWindowOptions(str, latLng);
    }

    public InfoWindowOptions setBody(GuiView guiView) {
        this.body = guiView;
        return this;
    }

    public InfoWindowOptions setTail(GuiImage guiImage) {
        this.tail = guiImage;
        return this;
    }

    public InfoWindowOptions setPosition(LatLng latLng) {
        this.position = latLng;
        return this;
    }

    public InfoWindowOptions setBodyOffset(Point point) {
        return point == null ? this : setBodyOffset(point.x, point.y);
    }

    public InfoWindowOptions setBodyOffset(float f, float f2) {
        this.bodyOffset.set(f, f2);
        return this;
    }

    public PointF getBodyOffset() {
        return this.bodyOffset;
    }

    public InfoWindowOptions setTailOffset(Point point) {
        return point == null ? this : setTailOffset(point.x, point.y);
    }

    public PointF getTailOffset() {
        return this.tailOffset;
    }

    public InfoWindowOptions setTailOffset(float f, float f2) {
        this.tailOffset.set(f, f2);
        return this;
    }

    public InfoWindowOptions setApplyDpScale(boolean z) {
        this.applyDpScale = z;
        return this;
    }

    public InfoWindowOptions setAutoMove(boolean z) {
        this.autoMove = z;
        return this;
    }

    public InfoWindowOptions setAutoMove(boolean z, int i) {
        this.autoMove = z;
        this.autoMoveDuration = i;
        return this;
    }

    public InfoWindowOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public boolean isApplyDpScale() {
        return this.applyDpScale;
    }

    public InfoWindowOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public LatLng getPosition() {
        return this.position;
    }

    public String getId() {
        return this.id;
    }

    public GuiView getBody() {
        return this.body;
    }

    public GuiImage getTail() {
        return this.tail;
    }

    public InfoWindowOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public boolean isAutoMove() {
        return this.autoMove;
    }

    public int getAutoMoveDuration() {
        return this.autoMoveDuration;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public Object getTag() {
        return this.tag;
    }
}

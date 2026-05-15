package com.kakao.vectormap.shape;

import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineStyle {
    public int color;
    public float lineWidth;
    public int strokeColor;
    public float strokeWidth;
    public int zoomLevel;

    PolylineStyle(int i, float f, int i2, float f2, int i3) {
        this.zoomLevel = i;
        this.lineWidth = f;
        this.color = i2;
        this.strokeWidth = f2;
        this.strokeColor = i3;
    }

    public static PolylineStyle from(float f, int i) {
        return new PolylineStyle(0, f, i, 0.0f, 0);
    }

    public static PolylineStyle from(float f, int i, float f2, int i2) {
        return new PolylineStyle(0, f, i, f2, i2);
    }

    public static PolylineStyle from(int i, float f, int i2, float f2, int i3) {
        return new PolylineStyle(i, f, i2, f2, i3);
    }

    public PolylineStyle setZoomLevel(int i) {
        this.zoomLevel = i;
        return this;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public int getColor() {
        return this.color;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolylineStyle)) {
            return false;
        }
        PolylineStyle polylineStyle = (PolylineStyle) obj;
        return getZoomLevel() == polylineStyle.getZoomLevel() && getLineWidth() == polylineStyle.getLineWidth() && getColor() == polylineStyle.getColor() && Float.compare(polylineStyle.getStrokeWidth(), getStrokeWidth()) == 0 && getStrokeColor() == polylineStyle.getStrokeColor();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getZoomLevel()), Float.valueOf(getLineWidth()), Integer.valueOf(getColor()), Float.valueOf(getStrokeWidth()), Integer.valueOf(getStrokeColor()));
    }
}

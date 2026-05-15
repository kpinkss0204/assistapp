package com.kakao.vectormap.shape;

import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolygonStyle {
    public int color;
    public int strokeColor;
    public float strokeWidth;
    public int zoomLevel;

    PolygonStyle(int i, int i2, float f, int i3) {
        this.zoomLevel = i;
        this.color = i2;
        this.strokeWidth = f;
        this.strokeColor = i3;
    }

    public static PolygonStyle from(int i) {
        return new PolygonStyle(0, i, 0.0f, 0);
    }

    public static PolygonStyle from(int i, float f, int i2) {
        return new PolygonStyle(0, i, f, i2);
    }

    public static PolygonStyle from(int i, int i2, float f, int i3) {
        return new PolygonStyle(i, i2, f, i3);
    }

    public PolygonStyle setZoomLevel(int i) {
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolygonStyle)) {
            return false;
        }
        PolygonStyle polygonStyle = (PolygonStyle) obj;
        return getZoomLevel() == polygonStyle.getZoomLevel() && getColor() == polygonStyle.getColor() && Float.compare(polygonStyle.getStrokeWidth(), getStrokeWidth()) == 0 && getStrokeColor() == polygonStyle.getStrokeColor();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getZoomLevel()), Integer.valueOf(getColor()), Float.valueOf(getStrokeWidth()), Integer.valueOf(getStrokeColor()));
    }
}

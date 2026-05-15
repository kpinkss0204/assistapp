package com.kakao.vectormap.shape.animation;

/* JADX INFO: loaded from: classes4.dex */
public class CircleWave {
    private float endAlpha;
    private float endRadius;
    private float startAlpha;
    private float startRadius;
    private int zoomLevel;

    CircleWave(int i, float f, float f2, float f3, float f4) {
        this.zoomLevel = i;
        this.startAlpha = f;
        this.endAlpha = f2;
        this.startRadius = f3;
        this.endRadius = f4;
    }

    public static CircleWave from(float f, float f2, float f3, float f4) {
        return new CircleWave(0, f, f2, f3, f4);
    }

    public static CircleWave from(int i, float f, float f2, float f3, float f4) {
        return new CircleWave(i, f, f2, f3, f4);
    }

    public CircleWave setZoomLevel(int i) {
        this.zoomLevel = i;
        return this;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public float getStartAlpha() {
        return this.startAlpha;
    }

    public CircleWave setStartAlpha(float f) {
        this.startAlpha = f;
        return this;
    }

    public float getEndAlpha() {
        return this.endAlpha;
    }

    public CircleWave setEndAlpha(float f) {
        this.endAlpha = f;
        return this;
    }

    public float getStartRadius() {
        return this.startRadius;
    }

    public CircleWave setStartRadius(float f) {
        this.startRadius = f;
        return this;
    }

    public float getEndRadius() {
        return this.endRadius;
    }

    public CircleWave setEndRadius(float f) {
        this.endRadius = f;
        return this;
    }

    public String toString() {
        return "CircleWave{zoomLevel=" + this.zoomLevel + ", startAlpha=" + this.startAlpha + ", endAlpha=" + this.endAlpha + ", startRadius=" + this.startRadius + ", endRadius=" + this.endRadius + '}';
    }
}

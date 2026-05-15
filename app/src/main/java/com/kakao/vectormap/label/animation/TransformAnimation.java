package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Interpolation;

/* JADX INFO: loaded from: classes4.dex */
public class TransformAnimation {
    private PointF pixelTranslation;
    private float rotation;
    private PointF scale;
    private Interpolation interpolation = Interpolation.Linear;
    private int duration = 100;
    private float alpha = 1.0f;

    TransformAnimation(PointF pointF, PointF pointF2, float f) {
        this.pixelTranslation = pointF;
        this.scale = pointF2;
        this.rotation = f;
    }

    public static TransformAnimation from() {
        return new TransformAnimation(new PointF(0.0f, 0.0f), new PointF(1.0f, 1.0f), 0.0f);
    }

    public static TransformAnimation from(PointF pointF) {
        return new TransformAnimation(pointF, new PointF(1.0f, 1.0f), 0.0f);
    }

    public static TransformAnimation from(float f) {
        return new TransformAnimation(new PointF(0.0f, 0.0f), new PointF(1.0f, 1.0f), f);
    }

    public TransformAnimation setScale(PointF pointF) {
        this.scale = pointF;
        return this;
    }

    public PointF getScale() {
        return this.scale;
    }

    public TransformAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public TransformAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public TransformAnimation setPixelTranslation(PointF pointF) {
        this.pixelTranslation = pointF;
        return this;
    }

    public PointF getPixelTranslation() {
        return this.pixelTranslation;
    }

    public TransformAnimation setRotation(float f) {
        this.rotation = f;
        return this;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public TransformAnimation setAlpha(float f) {
        this.alpha = f;
        return this;
    }
}

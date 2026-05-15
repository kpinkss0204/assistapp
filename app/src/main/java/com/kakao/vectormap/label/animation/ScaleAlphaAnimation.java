package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Interpolation;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleAlphaAnimation {
    private float alpha;
    private int duration;
    private Interpolation interpolation;
    private PointF scale;

    ScaleAlphaAnimation(PointF pointF, float f, Interpolation interpolation, int i) {
        this.scale = pointF;
        this.alpha = f;
        this.interpolation = interpolation;
        this.duration = i;
    }

    public static ScaleAlphaAnimation from(float f, float f2, float f3) {
        return new ScaleAlphaAnimation(new PointF(f, f2), f3, Interpolation.Linear, 100);
    }

    public ScaleAlphaAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public ScaleAlphaAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public ScaleAlphaAnimation setScale(float f, float f2) {
        this.scale = new PointF(f, f2);
        return this;
    }

    public PointF getScale() {
        return this.scale;
    }

    public ScaleAlphaAnimation setAlpha(float f) {
        this.alpha = f;
        return this;
    }

    public float getAlpha() {
        return this.alpha;
    }
}

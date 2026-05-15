package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Interpolation;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleAnimation {
    private int duration;
    private Interpolation interpolation;
    private PointF scale;

    ScaleAnimation(PointF pointF, Interpolation interpolation, int i) {
        this.scale = pointF;
        this.interpolation = interpolation;
        this.duration = i;
    }

    public static ScaleAnimation from(float f, float f2) {
        return new ScaleAnimation(new PointF(f, f2), Interpolation.Linear, 100);
    }

    public ScaleAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public ScaleAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public ScaleAnimation setScale(float f, float f2) {
        this.scale = new PointF(f, f2);
        return this;
    }

    public PointF getScale() {
        return this.scale;
    }
}

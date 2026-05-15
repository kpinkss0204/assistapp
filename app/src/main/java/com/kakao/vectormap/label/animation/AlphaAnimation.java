package com.kakao.vectormap.label.animation;

import com.kakao.vectormap.animation.Interpolation;

/* JADX INFO: loaded from: classes4.dex */
public class AlphaAnimation {
    private float alpha;
    private int duration;
    private Interpolation interpolation;

    AlphaAnimation(float f, Interpolation interpolation, int i) {
        this.alpha = f;
        this.interpolation = interpolation;
        this.duration = i;
    }

    public static AlphaAnimation from(float f) {
        return new AlphaAnimation(f, Interpolation.Linear, 100);
    }

    public AlphaAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public AlphaAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public AlphaAnimation setAlpha(float f) {
        this.alpha = f;
        return this;
    }

    public float getAlpha() {
        return this.alpha;
    }
}

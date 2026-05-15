package com.kakao.vectormap.route.animation;

import com.kakao.vectormap.animation.Interpolation;

/* JADX INFO: loaded from: classes4.dex */
public class ProgressAnimation {
    private ProgressDirection direction;
    private int duration;
    private boolean hideAtStop;
    private String id;
    private Interpolation interpolation;
    private boolean resetToInitialState;
    private ProgressType type;

    ProgressAnimation(String str, boolean z, boolean z2, Interpolation interpolation, int i, ProgressType progressType, ProgressDirection progressDirection) {
        this.id = str;
        this.hideAtStop = z;
        this.resetToInitialState = z2;
        this.interpolation = interpolation;
        this.duration = i;
        this.type = progressType;
        this.direction = progressDirection;
    }

    public static ProgressAnimation from(String str) {
        return new ProgressAnimation(str, true, false, Interpolation.Linear, 1000, ProgressType.ToHide, ProgressDirection.StartFirst);
    }

    public static ProgressAnimation from(String str, int i) {
        return new ProgressAnimation(str, true, false, Interpolation.Linear, i, ProgressType.ToHide, ProgressDirection.StartFirst);
    }

    public ProgressAnimation setHideAtStop(boolean z) {
        this.hideAtStop = z;
        return this;
    }

    public ProgressAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public ProgressAnimation setProgressDirection(ProgressDirection progressDirection) {
        this.direction = progressDirection;
        return this;
    }

    public ProgressAnimation setProgressType(ProgressType progressType) {
        this.type = progressType;
        return this;
    }

    public ProgressAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public ProgressAnimation setResetToInitialState(boolean z) {
        this.resetToInitialState = z;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public int getDuration() {
        return this.duration;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public ProgressDirection getProgressDirection() {
        return this.direction;
    }

    public ProgressType getProgressType() {
        return this.type;
    }

    public boolean isHideAtStop() {
        return this.hideAtStop;
    }

    public boolean isResetToInitialState() {
        return this.resetToInitialState;
    }
}

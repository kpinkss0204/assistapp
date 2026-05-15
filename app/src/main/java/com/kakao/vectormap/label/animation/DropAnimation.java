package com.kakao.vectormap.label.animation;

import com.google.logging.type.LogSeverity;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.animation.Interpolation;
import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class DropAnimation implements Animation {
    private String id;
    private float pixelHeight;
    private Interpolation interpolation = Interpolation.Linear;
    private boolean hideShapeAtStop = true;
    private boolean removeLabelAtStop = false;
    private int repeatCount = 3;
    private int duration = LogSeverity.ERROR_VALUE;

    DropAnimation(String str, float f) {
        this.id = MapUtils.getUniqueId(str);
        this.pixelHeight = f;
    }

    public static DropAnimation from(String str) {
        return new DropAnimation(str, 500.0f);
    }

    public static DropAnimation from(float f) {
        return new DropAnimation("", f);
    }

    public float getPixelHeight() {
        return this.pixelHeight;
    }

    public DropAnimation setPixelHeight(float f) {
        this.pixelHeight = f;
        return this;
    }

    public DropAnimation setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public DropAnimation setHideShapeAtStop(boolean z) {
        this.hideShapeAtStop = z;
        return this;
    }

    public boolean isHideShapeAtStop() {
        return this.hideShapeAtStop;
    }

    public DropAnimation setRemoveLabelAtStop(boolean z) {
        this.removeLabelAtStop = z;
        return this;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public DropAnimation setInterpolation(Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }

    public DropAnimation setRepeatCount(int i) {
        this.repeatCount = i;
        return this;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }
}

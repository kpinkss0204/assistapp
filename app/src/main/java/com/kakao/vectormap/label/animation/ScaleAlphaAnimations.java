package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleAlphaAnimations implements Animation {
    private List<ScaleAlphaAnimation> animations;
    private String id;
    private PointF initScale = new PointF(1.0f, 1.0f);
    private float initAlpha = 1.0f;
    private boolean removeLabelAtStop = false;
    private boolean hideLabelAtStop = false;
    private boolean resetToInitialState = true;

    ScaleAlphaAnimations(String str, ScaleAlphaAnimation... scaleAlphaAnimationArr) {
        this.id = MapUtils.getUniqueId(str);
        ArrayList arrayList = new ArrayList();
        this.animations = arrayList;
        if (scaleAlphaAnimationArr != null) {
            arrayList.addAll(Arrays.asList(scaleAlphaAnimationArr));
        }
    }

    public static ScaleAlphaAnimations from(String str) {
        return new ScaleAlphaAnimations(str, null);
    }

    public static ScaleAlphaAnimations from(ScaleAlphaAnimation... scaleAlphaAnimationArr) {
        return new ScaleAlphaAnimations("", scaleAlphaAnimationArr);
    }

    public static ScaleAlphaAnimations from(String str, ScaleAlphaAnimation... scaleAlphaAnimationArr) {
        return new ScaleAlphaAnimations(str, scaleAlphaAnimationArr);
    }

    public ScaleAlphaAnimations setInitScale(PointF pointF) {
        this.initScale = pointF;
        return this;
    }

    public ScaleAlphaAnimations setInitScale(float f, float f2) {
        this.initScale = new PointF(f, f2);
        return this;
    }

    public PointF getInitScale() {
        return this.initScale;
    }

    public ScaleAlphaAnimations setInitAlpha(float f) {
        this.initAlpha = f;
        return this;
    }

    public float getInitAlpha() {
        return this.initAlpha;
    }

    public ScaleAlphaAnimations setHideLabelAtStop(boolean z) {
        this.hideLabelAtStop = z;
        return this;
    }

    public boolean isHideLabelAtStop() {
        return this.hideLabelAtStop;
    }

    public ScaleAlphaAnimations setRemoveLabelAtStop(boolean z) {
        this.removeLabelAtStop = z;
        return this;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public ScaleAlphaAnimations setResetToInitialState(boolean z) {
        this.resetToInitialState = z;
        return this;
    }

    public boolean isResetToInitialState() {
        return this.resetToInitialState;
    }

    public ScaleAlphaAnimations addScaleAlphaAnimation(ScaleAlphaAnimation... scaleAlphaAnimationArr) {
        if (scaleAlphaAnimationArr != null) {
            this.animations.addAll(Arrays.asList(scaleAlphaAnimationArr));
        }
        return this;
    }

    public List<ScaleAlphaAnimation> getScaleAlphaAnimations() {
        return this.animations;
    }

    public int getScaleAlphaAnimationCount() {
        return this.animations.size();
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }
}

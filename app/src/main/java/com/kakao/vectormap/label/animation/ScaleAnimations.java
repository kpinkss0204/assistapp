package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleAnimations implements Animation {
    private List<ScaleAnimation> animations;
    private String id;
    private PointF initScale = new PointF(1.0f, 1.0f);
    private boolean hideLabelAtStop = false;
    private boolean removeLabelAtStop = false;
    private boolean resetToInitialState = true;

    ScaleAnimations(String str, ScaleAnimation... scaleAnimationArr) {
        this.id = MapUtils.getUniqueId(str);
        ArrayList arrayList = new ArrayList();
        this.animations = arrayList;
        if (scaleAnimationArr != null) {
            arrayList.addAll(Arrays.asList(scaleAnimationArr));
        }
    }

    public static ScaleAnimations from(String str) {
        return new ScaleAnimations(str, null);
    }

    public static ScaleAnimations from(ScaleAnimation... scaleAnimationArr) {
        return new ScaleAnimations("", scaleAnimationArr);
    }

    public static ScaleAnimations from(String str, ScaleAnimation... scaleAnimationArr) {
        return new ScaleAnimations(str, scaleAnimationArr);
    }

    public ScaleAnimations setInitScale(PointF pointF) {
        this.initScale = pointF;
        return this;
    }

    public ScaleAnimations setInitScale(float f, float f2) {
        this.initScale = new PointF(f, f2);
        return this;
    }

    public PointF getInitScale() {
        return this.initScale;
    }

    public ScaleAnimations setHideLabelAtStop(boolean z) {
        this.hideLabelAtStop = z;
        return this;
    }

    public boolean isHideLabelAtStop() {
        return this.hideLabelAtStop;
    }

    public ScaleAnimations setResetToInitialState(boolean z) {
        this.resetToInitialState = z;
        return this;
    }

    public ScaleAnimations setRemoveLabelAtStop(boolean z) {
        this.removeLabelAtStop = z;
        return this;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public boolean isResetToInitialState() {
        return this.resetToInitialState;
    }

    public ScaleAnimations addScaleAnimation(ScaleAnimation... scaleAnimationArr) {
        if (scaleAnimationArr != null) {
            this.animations.addAll(Arrays.asList(scaleAnimationArr));
        }
        return this;
    }

    public List<ScaleAnimation> getScaleAnimations() {
        return this.animations;
    }

    public int getScaleAnimationCount() {
        return this.animations.size();
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }
}

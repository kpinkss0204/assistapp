package com.kakao.vectormap.label.animation;

import android.graphics.PointF;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TransformAnimations implements Animation {
    private List<TransformAnimation> animations;
    private String id;
    private PointF initScale = new PointF(1.0f, 1.0f);
    private PointF initPixelTranslation = new PointF(0.0f, 0.0f);
    private float initRotation = 0.0f;
    private float initAlpha = 1.0f;
    private boolean hideLabelAtStop = false;
    private boolean removeLabelAtStop = false;
    private boolean resetToInitialState = true;

    TransformAnimations(String str, TransformAnimation... transformAnimationArr) {
        this.id = MapUtils.getUniqueId(str);
        ArrayList arrayList = new ArrayList();
        this.animations = arrayList;
        if (transformAnimationArr != null) {
            arrayList.addAll(Arrays.asList(transformAnimationArr));
        }
    }

    public static TransformAnimations from(String str) {
        return new TransformAnimations(str, null);
    }

    public static TransformAnimations from(TransformAnimation... transformAnimationArr) {
        return new TransformAnimations("", transformAnimationArr);
    }

    public static TransformAnimations from(String str, TransformAnimation... transformAnimationArr) {
        return new TransformAnimations(str, transformAnimationArr);
    }

    public TransformAnimations setResetToInitialState(boolean z) {
        this.resetToInitialState = z;
        return this;
    }

    public boolean isResetToInitialState() {
        return this.resetToInitialState;
    }

    public TransformAnimations setHideLabelAtStop(boolean z) {
        this.hideLabelAtStop = z;
        return this;
    }

    public boolean isHideLabelAtStop() {
        return this.hideLabelAtStop;
    }

    public TransformAnimations setRemoveLabelAtStop(boolean z) {
        this.removeLabelAtStop = z;
        return this;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public TransformAnimations setInitScale(PointF pointF) {
        this.initScale = pointF;
        return this;
    }

    public PointF getInitScale() {
        return this.initScale;
    }

    public TransformAnimations setInitAlpha(float f) {
        this.initAlpha = f;
        return this;
    }

    public float getInitAlpha() {
        return this.initAlpha;
    }

    public TransformAnimations setInitPixelTranslation(PointF pointF) {
        this.initPixelTranslation = pointF;
        return this;
    }

    public PointF getInitPixelTranslation() {
        return this.initPixelTranslation;
    }

    public TransformAnimations setInitRotation(float f) {
        this.initRotation = f;
        return this;
    }

    public float getInitRotation() {
        return this.initRotation;
    }

    public TransformAnimations addTransformAnimation(TransformAnimation... transformAnimationArr) {
        if (transformAnimationArr != null) {
            this.animations.addAll(Arrays.asList(transformAnimationArr));
        }
        return this;
    }

    public List<TransformAnimation> getTransformAnimations() {
        return this.animations;
    }

    public int getTransformAnimationCount() {
        return this.animations.size();
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }
}

package com.kakao.vectormap.label.animation;

import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AlphaAnimations implements Animation {
    private List<AlphaAnimation> animations;
    private String id;
    private float initAlpha = 1.0f;
    private boolean removeLabelAtStop = false;
    private boolean hideLabelAtStop = false;
    private boolean resetToInitialState = true;

    AlphaAnimations(String str, AlphaAnimation... alphaAnimationArr) {
        this.id = MapUtils.getUniqueId(str);
        ArrayList arrayList = new ArrayList();
        this.animations = arrayList;
        if (alphaAnimationArr != null) {
            arrayList.addAll(Arrays.asList(alphaAnimationArr));
        }
    }

    public static AlphaAnimations from(String str) {
        return new AlphaAnimations(str, null);
    }

    public static AlphaAnimations from(AlphaAnimation... alphaAnimationArr) {
        return new AlphaAnimations("", alphaAnimationArr);
    }

    public static AlphaAnimations from(String str, AlphaAnimation... alphaAnimationArr) {
        return new AlphaAnimations(str, alphaAnimationArr);
    }

    public AlphaAnimations setInitAlpha(float f) {
        this.initAlpha = f;
        return this;
    }

    public float getInitAlpha() {
        return this.initAlpha;
    }

    public AlphaAnimations setHideLabelAtStop(boolean z) {
        this.hideLabelAtStop = z;
        return this;
    }

    public boolean isHideLabelAtStop() {
        return this.hideLabelAtStop;
    }

    public AlphaAnimations setRemoveLabelAtStop(boolean z) {
        this.removeLabelAtStop = z;
        return this;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public AlphaAnimations setResetToInitialState(boolean z) {
        this.resetToInitialState = z;
        return this;
    }

    public boolean isResetToInitialState() {
        return this.resetToInitialState;
    }

    public AlphaAnimations addAlphaAnimation(AlphaAnimation... alphaAnimationArr) {
        if (alphaAnimationArr != null) {
            this.animations.addAll(Arrays.asList(alphaAnimationArr));
        }
        return this;
    }

    public List<AlphaAnimation> getAlphaAnimations() {
        return this.animations;
    }

    public int getAlphaAnimationCount() {
        return this.animations.size();
    }

    @Override // com.kakao.vectormap.animation.Animation
    public String getId() {
        return this.id;
    }
}

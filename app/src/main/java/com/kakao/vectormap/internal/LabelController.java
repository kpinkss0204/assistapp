package com.kakao.vectormap.internal;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelStyles;

/* JADX INFO: loaded from: classes4.dex */
class LabelController extends LabelCommonController {
    static native void addTransformShare(long j, String str, String str2, String str3, String str4, String str5, boolean z);

    static native void changePixelOffset(long j, String str, String str2, String str3, float f, float f2, boolean z);

    static native void changeStylesAndText(long j, String str, boolean z, String str2, String str3, String str4, LabelStyles labelStyles, boolean z2, boolean z3, String[] strArr, int[] iArr);

    static native float getOrientation(long j, String str, String str2, String str3);

    static native LatLng getPosition(long j, String str, String str2, String str3);

    static native void moveOnPath(long j, String str, String str2, String str3, double[] dArr, double[] dArr2, int i, float f, float f2, float f3, float f4, boolean z);

    static native void moveTo(long j, String str, String str2, String str3, double d, double d2, int i);

    static native void removeTransformShare(long j, String str, String str2, String str3, String str4, String str5, boolean z);

    static native void rotateTo(long j, String str, String str2, String str3, float f, int i);

    static native void scaleTo(long j, String str, String str2, String str3, float f, float f2, int i);

    static native void setPosition(long j, String str, String str2, String str3, double d, double d2);

    static native void setRotation(long j, String str, String str2, String str3, float f);

    static native void updateLabels(long j, String str, int i, Label[] labelArr);

    LabelController() {
    }
}

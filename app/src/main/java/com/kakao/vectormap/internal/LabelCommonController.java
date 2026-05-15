package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
class LabelCommonController {
    public static native void addBadges(long j, String str, String str2, String str3, String[] strArr, String[] strArr2, float[] fArr, float[] fArr2, int[] iArr, boolean z);

    public static native long getRank(long j, String str, String str2, String str3, boolean z);

    public static native boolean isClickable(long j, String str, String str2, String str3, boolean z);

    public static native boolean isShow(long j, String str, String str2, String str3, boolean z);

    public static native void removeAllBadge(long j, String str, String str2, String str3, boolean z);

    public static native void removeAllLabel(long j, String str, String str2, boolean z);

    public static native void removeBadge(long j, String str, String str2, String str3, String str4, boolean z);

    public static native void removeLabel(long j, String str, String str2, String str3, boolean z, boolean z2);

    public static native void removeLabels(long j, String str, String str2, String[] strArr, boolean z, boolean z2);

    public static native void setAllBadgeVisible(long j, String str, String str2, String str3, boolean z, boolean z2);

    public static native void setAllVisible(long j, String str, String str2, boolean z, boolean z2, boolean z3);

    public static native void setBadgeOffset(long j, String str, String str2, String str3, String str4, float f, float f2, boolean z);

    public static native void setBadgeVisible(long j, String str, String str2, String str3, String str4, boolean z, boolean z2);

    public static native void setClickable(long j, String str, String str2, String str3, boolean z, boolean z2);

    public static native void setLayerClickable(long j, String str, String str2, boolean z, boolean z2);

    public static native void setRank(long j, String str, String str2, String str3, long j2, boolean z);

    public static native void setScale(long j, String str, String str2, String str3, boolean z, float f, float f2);

    public static native void setVisible(long j, String str, String str2, String str3, boolean z, boolean z2, boolean z3, int i);

    public static native void setVisibleAllStyleBadge(long j, String str, String str2, String str3, boolean z, boolean z2);

    public static native void setVisibleStyleBadge(long j, String str, String str2, String str3, boolean z, String str4, boolean z2);

    public static native void setZOrder(long j, String str, String str2, int i, boolean z);

    LabelCommonController() {
    }
}

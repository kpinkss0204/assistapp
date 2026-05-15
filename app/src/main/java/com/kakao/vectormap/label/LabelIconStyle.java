package com.kakao.vectormap.label;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public class LabelIconStyle {
    public float anchorX;
    public float anchorY;
    public String assetId = "";
    public Bitmap bitmap;
    public int resId;

    LabelIconStyle(int i, Bitmap bitmap, float f, float f2) {
        this.resId = i;
        this.bitmap = bitmap;
        this.anchorX = f;
        this.anchorY = f2;
    }
}

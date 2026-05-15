package com.kakao.vectormap.mapwidget.component;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public class GuiImage extends GuiView {
    public String assetId;
    public Bitmap bitmap;
    public int bottom;
    public GuiView child;
    public boolean isNinepatch;
    public int left;
    public int resourceId;
    public int right;
    public int top;

    public GuiImage(int i, boolean z) {
        this.resourceId = 0;
        this.isNinepatch = false;
        this.assetId = "";
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.type = z ? 3 : 2;
        this.resourceId = i;
        this.isNinepatch = z;
    }

    public GuiImage(Bitmap bitmap) {
        this.resourceId = 0;
        this.isNinepatch = false;
        this.assetId = "";
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.type = 2;
        this.bitmap = bitmap;
    }

    public void setFixedArea(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public void addChild(GuiView guiView) {
        this.child = guiView;
    }

    public GuiView getChild() {
        return this.child;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public boolean isNinepatch() {
        return this.isNinepatch;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
}

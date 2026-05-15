package com.kakao.vectormap.mapwidget.component;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class GuiLayout extends GuiView {
    public GuiImage background;
    public GuiView[] childArray;
    public int orientation;

    public GuiLayout(Orientation orientation) {
        this.orientation = Orientation.Horizontal.getValue();
        this.orientation = orientation.getValue();
        this.type = orientation == Orientation.Horizontal ? 0 : 1;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation.getValue();
    }

    public Orientation getOrientation() {
        return Orientation.getEnum(this.orientation);
    }

    public void setBackground(int i, boolean z) {
        this.background = new GuiImage(i, z);
    }

    public void setBackground(Bitmap bitmap) {
        this.background = new GuiImage(bitmap);
    }

    public void setBackground(GuiImage guiImage) {
        this.background = guiImage;
    }

    public GuiImage getBackground() {
        return this.background;
    }

    public void addView(GuiView guiView) {
        GuiView[] guiViewArr = this.childArray;
        if (guiViewArr != null && guiViewArr.length > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(this.childArray));
            arrayList.add(guiView);
            this.childArray = (GuiView[]) arrayList.toArray(new GuiView[arrayList.size()]);
            return;
        }
        this.childArray = new GuiView[]{guiView};
    }

    public boolean hasChild() {
        GuiView[] guiViewArr = this.childArray;
        return guiViewArr != null && guiViewArr.length > 0;
    }

    public GuiView[] getChildArray() {
        return this.childArray;
    }

    public GuiView getChildAt(int i) {
        GuiView[] guiViewArr = this.childArray;
        if (guiViewArr == null) {
            return null;
        }
        return guiViewArr[i];
    }

    public GuiView getChild(String str) {
        GuiView[] guiViewArr = this.childArray;
        if (guiViewArr == null) {
            return null;
        }
        for (GuiView guiView : guiViewArr) {
            if (guiView.getId() == str) {
                return guiView;
            }
        }
        return null;
    }
}

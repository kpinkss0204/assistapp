package com.kakao.vectormap.mapwidget.component;

/* JADX INFO: loaded from: classes4.dex */
public abstract class GuiView {
    public Object tag;
    public int type;
    public String id = "";
    public boolean clickable = true;
    public int paddingLeft = 0;
    public int paddingTop = 0;
    public int paddingRight = 0;
    public int paddingBottom = 0;
    public int verticalOrigin = Vertical.Bottom.getValue();
    public int horizontalOrigin = Horizontal.Center.getValue();
    public int verticalAlign = Vertical.Center.getValue();
    public int horizontalAlign = Horizontal.Center.getValue();

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setClickable(boolean z) {
        this.clickable = z;
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.paddingLeft = i;
        this.paddingTop = i2;
        this.paddingRight = i3;
        this.paddingBottom = i4;
    }

    public void setOrigin(Vertical vertical, Horizontal horizontal) {
        this.verticalOrigin = vertical.getValue();
        this.horizontalOrigin = horizontal.getValue();
    }

    public void setAlign(Vertical vertical, Horizontal horizontal) {
        this.verticalAlign = vertical.getValue();
        this.horizontalAlign = horizontal.getValue();
    }

    public void setVerticalOrigin(Vertical vertical) {
        this.verticalOrigin = vertical.getValue();
    }

    public void setHorizontalOrigin(Horizontal horizontal) {
        this.horizontalOrigin = horizontal.getValue();
    }

    public Vertical getVerticalOrigin() {
        return Vertical.getEnum(this.verticalOrigin);
    }

    public Horizontal getHorizontalOrigin() {
        return Horizontal.getEnum(this.horizontalOrigin);
    }

    public Vertical getVerticalAlign() {
        return Vertical.getEnum(this.verticalAlign);
    }

    public Horizontal getHorizontalAlign() {
        return Horizontal.getEnum(this.horizontalAlign);
    }

    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public int getPaddingTop() {
        return this.paddingTop;
    }

    public int getPaddingRight() {
        return this.paddingRight;
    }

    public int getPaddingBottom() {
        return this.paddingBottom;
    }

    public Object getTag() {
        return this.tag;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }
}

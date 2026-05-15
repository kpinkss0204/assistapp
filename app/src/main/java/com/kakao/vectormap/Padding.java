package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class Padding {
    public int bottom;
    public int left;
    public int right;
    public int top;

    public Padding() {
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
    }

    public Padding(Padding padding) {
        this.left = padding.left;
        this.top = padding.top;
        this.right = padding.right;
        this.bottom = padding.bottom;
    }

    public Padding(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public int getLeft() {
        return this.left;
    }

    public int getTop() {
        return this.top;
    }

    public int getRight() {
        return this.right;
    }

    public int getBottom() {
        return this.bottom;
    }

    public boolean equals(Padding padding) {
        return padding != null && this.left == padding.left && this.top == padding.top && this.right == padding.right && this.bottom == padding.bottom;
    }

    public String toString() {
        return "Padding{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
    }
}

package com.kakao.vectormap.shape;

/* JADX INFO: loaded from: classes4.dex */
public enum ShapeLayerPass {
    Default(0),
    Overlay(1),
    Route(2);

    private final int value;

    ShapeLayerPass(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

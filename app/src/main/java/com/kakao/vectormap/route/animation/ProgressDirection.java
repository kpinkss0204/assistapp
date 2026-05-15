package com.kakao.vectormap.route.animation;

/* JADX INFO: loaded from: classes4.dex */
public enum ProgressDirection {
    StartFirst(0),
    EndFirst(1);

    private final int value;

    ProgressDirection(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static ProgressDirection fromValue(int i) {
        for (ProgressDirection progressDirection : values()) {
            if (progressDirection.value == i) {
                return progressDirection;
            }
        }
        return null;
    }
}

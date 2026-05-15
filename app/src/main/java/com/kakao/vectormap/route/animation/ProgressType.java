package com.kakao.vectormap.route.animation;

/* JADX INFO: loaded from: classes4.dex */
public enum ProgressType {
    ToHide(0),
    ToShow(1);

    private final int value;

    ProgressType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static ProgressType fromValue(int i) {
        for (ProgressType progressType : values()) {
            if (progressType.value == i) {
                return progressType;
            }
        }
        return null;
    }
}

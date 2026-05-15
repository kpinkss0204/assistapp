package com.kakao.vectormap.label;

/* JADX INFO: loaded from: classes4.dex */
public enum Transition {
    None(0),
    Alpha(1),
    Scale(2);

    private final int value;

    Transition(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static Transition getEnum(int i) {
        Transition transition = None;
        if (i == transition.getValue()) {
            return transition;
        }
        Transition transition2 = Alpha;
        if (i != transition2.getValue()) {
            Transition transition3 = Scale;
            if (i == transition3.getValue()) {
                return transition3;
            }
        }
        return transition2;
    }
}

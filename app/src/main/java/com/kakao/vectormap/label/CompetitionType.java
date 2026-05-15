package com.kakao.vectormap.label;

/* JADX INFO: loaded from: classes4.dex */
public enum CompetitionType {
    None(0),
    All(1),
    Upper(2),
    UpperLower(3),
    UpperSame(4),
    Same(5),
    SameLower(6),
    Lower(7);

    private final int value;

    CompetitionType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}

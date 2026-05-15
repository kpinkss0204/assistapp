package com.kakao.vectormap.internal;

import com.kakao.vectormap.label.LabelTextBuilder;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ILabelOptions {
    public LabelTextBuilder labelTextBuilder;
    public int[] textStyleIndexes;
    public String[] texts;
    public String labelId = "";
    public long rank = 0;
    public boolean visible = true;
}

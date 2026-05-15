package com.kakao.vectormap.label;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LabelTextBuilder {
    private final List<Pair<String, Integer>> textList = new ArrayList();

    public LabelTextBuilder setTexts(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            this.textList.clear();
            if (strArr.length == 1) {
                strArr = strArr[0].split(System.lineSeparator());
            }
            for (int i = 0; i < strArr.length; i++) {
                this.textList.add(new Pair<>(strArr[i], Integer.valueOf(i)));
            }
        }
        return this;
    }

    public LabelTextBuilder addTextLine(String str, int i) {
        if (str == null) {
            return this;
        }
        this.textList.add(new Pair<>(str, Integer.valueOf(i)));
        return this;
    }

    public int getTextLineCount() {
        return this.textList.size();
    }

    public String[] getTexts() {
        int size = this.textList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) this.textList.get(i).first;
        }
        return strArr;
    }

    public int[] getTextIndexes() {
        int size = this.textList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) this.textList.get(i).second).intValue();
        }
        return iArr;
    }
}

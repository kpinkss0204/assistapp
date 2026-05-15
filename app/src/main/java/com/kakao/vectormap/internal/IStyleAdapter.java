package com.kakao.vectormap.internal;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IStyleAdapter {
    protected String content;
    protected String styleId;
    protected Map<Integer, Integer> typeIndexMap = new HashMap();

    void setStyleId(String str) {
        this.styleId = str;
    }

    void setContent(String str) {
        this.content = str;
    }

    protected int getIndex(int i) {
        if (this.typeIndexMap.containsKey(Integer.valueOf(i))) {
            return this.typeIndexMap.get(Integer.valueOf(i)).intValue();
        }
        return 0;
    }
}

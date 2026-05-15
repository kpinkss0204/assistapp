package org.simpleframework.xml.core;

/* JADX INFO: loaded from: classes3.dex */
enum MethodType {
    GET(3),
    IS(2),
    SET(3),
    NONE(0);

    private int prefix;

    MethodType(int i) {
        this.prefix = i;
    }

    public int getPrefix() {
        return this.prefix;
    }
}

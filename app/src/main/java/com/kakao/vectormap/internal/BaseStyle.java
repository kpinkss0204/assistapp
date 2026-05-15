package com.kakao.vectormap.internal;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseStyle {
    public abstract int toJson(IStyleBuilder iStyleBuilder) throws IOException, RuntimeException;
}

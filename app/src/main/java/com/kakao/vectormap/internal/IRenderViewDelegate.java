package com.kakao.vectormap.internal;

import com.kakao.vectormap.Logo;

/* JADX INFO: loaded from: classes4.dex */
public interface IRenderViewDelegate {
    Logo getLogo() throws RuntimeException;

    void setLogoPosition(int i, float f, float f2) throws RuntimeException;
}

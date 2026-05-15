package com.kakao.vectormap.graphics.gl;

/* JADX INFO: loaded from: classes4.dex */
public interface IEglLifeCycleCallback {
    void eglContextDestroyed();

    void eglSurfaceCreated();

    void eglSurfaceDestroyed();
}

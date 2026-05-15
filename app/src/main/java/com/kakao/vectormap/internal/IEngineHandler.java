package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
public interface IEngineHandler {
    String getEngineState();

    boolean isPaused();

    boolean isResumed();

    void pause();

    void removeVsyncCallbackOnUiThread();

    boolean render();

    void resize(int i, int i2);

    void resume();

    long start(int i, String str);

    void stop();

    void updateSurface();
}

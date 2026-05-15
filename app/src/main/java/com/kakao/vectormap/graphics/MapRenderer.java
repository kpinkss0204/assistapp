package com.kakao.vectormap.graphics;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MapRenderer {
    public abstract String getEngineState();

    public abstract boolean isPaused();

    public abstract boolean isResumed();

    public abstract void pause();

    public abstract void resume();

    public abstract void setEngineHandler(Object obj);
}

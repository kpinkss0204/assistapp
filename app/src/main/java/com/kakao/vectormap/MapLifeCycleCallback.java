package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MapLifeCycleCallback {
    public abstract void onMapDestroy();

    public abstract void onMapError(Exception exc);

    public void onMapPaused() {
    }

    public void onMapResumed() {
    }
}

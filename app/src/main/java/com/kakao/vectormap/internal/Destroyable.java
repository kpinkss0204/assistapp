package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
abstract class Destroyable {
    private boolean isRunning;

    abstract void onDestroy();

    Destroyable() {
    }

    public synchronized void setRunning(boolean z) {
        this.isRunning = z;
    }

    public synchronized boolean isRunning() {
        return this.isRunning;
    }
}

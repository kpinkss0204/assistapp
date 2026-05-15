package com.kakao.vectormap.camera;

/* JADX INFO: loaded from: classes4.dex */
public class CameraAnimation {
    public boolean autoElevation;
    public int duration;
    public boolean isConsecutive;

    CameraAnimation(int i, boolean z, boolean z2) {
        this.duration = i;
        this.autoElevation = z;
        this.isConsecutive = z2;
    }

    public static CameraAnimation from(int i) {
        return new CameraAnimation(i, false, false);
    }

    public static CameraAnimation from(int i, boolean z, boolean z2) {
        return new CameraAnimation(i, z, z2);
    }

    public synchronized int getDuration() {
        return this.duration;
    }

    public synchronized void setDuration(int i) {
        this.duration = i;
    }

    public synchronized boolean isAutoElevation() {
        return this.autoElevation;
    }

    public synchronized void setAutoElevation(boolean z) {
        this.autoElevation = z;
    }

    public synchronized boolean isConsecutive() {
        return this.isConsecutive;
    }

    public synchronized void setConsecutive(boolean z) {
        this.isConsecutive = z;
    }
}

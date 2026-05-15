package com.kakao.vectormap.internal;

/* JADX INFO: loaded from: classes4.dex */
public class MouseMotionHandler {
    private long appEngineHandle;
    private float startScrollValue = 0.0f;
    private long startEventTime = 0;
    private String viewName = "test";

    private boolean isSameDirection(float f, float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            return f < 0.0f && f2 < 0.0f;
        }
        return true;
    }

    public void setAppEngineHandle(long j) {
        this.appEngineHandle = j;
    }

    public void handleEvent(long j, float f) {
        if (j - this.startEventTime >= 70 || !isSameDirection(this.startScrollValue, f)) {
            this.startScrollValue = f;
            this.startEventTime = j;
            if (f < 0.0f) {
                MapCameraController.zoomIn(this.appEngineHandle, this.viewName, true, true, 70);
            } else {
                MapCameraController.zoomOut(this.appEngineHandle, this.viewName, true, true, 70);
            }
            this.startEventTime = j;
        }
    }
}

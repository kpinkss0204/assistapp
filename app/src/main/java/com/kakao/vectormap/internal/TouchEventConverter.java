package com.kakao.vectormap.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class TouchEventConverter implements View.OnTouchListener, EngineCreateCallback {
    private float downX;
    private float downY;
    private int touchSlop;
    private boolean touchSlopExceeded;
    private float lastScrollValue = 0.0f;
    private long lastEventTime = 0;
    private long appEngineHandle = 0;
    private MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();

    static native void eventCancelled(long j, int i, int[] iArr, float f, float f2, double d);

    static native void eventMoved(long j, int i, int[] iArr, float[] fArr, float[] fArr2, double d);

    static native void eventOccurred(long j, int i, int i2, float f, float f2, double d);

    static native void mouseWheelMoved(long j, float f, float f2, float f3, int i);

    public static native void reverseMouseWheelScroll(boolean z);

    public TouchEventConverter(int i) {
        this.touchSlop = i;
        reverseMouseWheelScroll(false);
    }

    @Override // com.kakao.vectormap.internal.EngineCreateCallback
    public void onEngineCreated(long j) {
        this.appEngineHandle = j;
        this.mouseMotionHandler.setAppEngineHandle(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003c  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.internal.TouchEventConverter.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean onGenericMotion(MotionEvent motionEvent) {
        if (this.appEngineHandle == 0) {
            return true;
        }
        if (!motionEvent.isFromSource(2) || motionEvent.getAction() != 8) {
            return false;
        }
        long eventTime = motionEvent.getEventTime();
        float axisValue = motionEvent.getAxisValue(9);
        long j = this.lastEventTime;
        if (j == 0 || this.lastScrollValue == 0.0f) {
            mouseWheelMoved(this.appEngineHandle, motionEvent.getX(), motionEvent.getY(), axisValue, 30);
            this.lastEventTime = eventTime;
            this.lastScrollValue = axisValue;
            return true;
        }
        if (Math.abs(eventTime - j) < 50) {
            if (isDirectionChanged(axisValue)) {
                mouseWheelMoved(this.appEngineHandle, motionEvent.getX(), motionEvent.getY(), axisValue, 30);
                this.lastEventTime = eventTime;
                this.lastScrollValue = axisValue;
            }
            return true;
        }
        mouseWheelMoved(this.appEngineHandle, motionEvent.getX(), motionEvent.getY(), axisValue, 30);
        this.lastEventTime = eventTime;
        this.lastScrollValue = axisValue;
        return true;
    }

    private boolean isDirectionChanged(float f) {
        float f2 = this.lastScrollValue;
        if (f2 <= 0.0f || f >= 0.0f) {
            return f2 < 0.0f && f > 0.0f;
        }
        return true;
    }
}

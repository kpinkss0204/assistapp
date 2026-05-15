package com.kakao.vectormap.graphics.vk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes4.dex */
public class VKSurfaceView extends SurfaceView implements SurfaceHolder.Callback2 {
    private boolean mDetached;
    private Renderer mRenderer;
    private VKRenderThread mThread;

    public interface Renderer {
        boolean onDrawFrame();

        void onPause();

        void onRenderThreadStart();

        void onRenderThreadStop();

        void onResume();

        void onSurfaceChanged(int i, int i2);

        void onSurfaceCreated();

        void onSurfaceDestroyed();
    }

    public void initEngine(boolean z) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    public VKSurfaceView(Context context) {
        super(context);
        init();
    }

    public VKSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public VKSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    protected void finalize() throws Throwable {
        try {
            VKRenderThread vKRenderThread = this.mThread;
            if (vKRenderThread != null) {
                vKRenderThread.requestExitAndWait();
            }
        } finally {
            super.finalize();
        }
    }

    private void init() {
        getHolder().addCallback(this);
    }

    private void checkRenderThreadState() {
        if (this.mThread != null) {
            throw new IllegalStateException("mThread should be null.");
        }
    }

    public void setRenderer(Renderer renderer) {
        checkRenderThreadState();
        this.mRenderer = renderer;
        VKRenderThread vKRenderThread = new VKRenderThread(renderer);
        this.mThread = vKRenderThread;
        vKRenderThread.start();
    }

    public void setRenderMode(int i) {
        this.mThread.setRenderMode(i);
    }

    public int getRenderMode() {
        return this.mThread.getRenderMode();
    }

    public void requestRender() {
        this.mThread.requestRender();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mThread.surfaceCreated();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mThread.onWindowResize(i2, i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mThread.surfaceDestroyed();
    }

    public void onPause() {
        this.mThread.onPause();
    }

    public void onResume() {
        this.mThread.onResume();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mDetached && this.mRenderer != null) {
            VKRenderThread vKRenderThread = new VKRenderThread(this.mRenderer);
            this.mThread = vKRenderThread;
            vKRenderThread.start();
        }
        this.mDetached = false;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        VKRenderThread vKRenderThread = this.mThread;
        if (vKRenderThread != null) {
            vKRenderThread.requestExitAndWait();
        }
        this.mDetached = true;
        super.onDetachedFromWindow();
    }
}

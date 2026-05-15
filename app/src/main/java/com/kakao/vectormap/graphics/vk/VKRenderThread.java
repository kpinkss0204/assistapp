package com.kakao.vectormap.graphics.vk;

import com.kakao.vectormap.graphics.vk.VKSurfaceView;

/* JADX INFO: loaded from: classes4.dex */
public class VKRenderThread extends Thread {
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private boolean mConfirmedSurfaceCreation;
    private boolean mConfirmedSurfaceDestruction;
    private boolean mExited;
    private boolean mPaused;
    private boolean mRenderComplete;
    private VKSurfaceView.Renderer mRenderer;
    private boolean mRequestPaused;
    private boolean mShouldExit;
    private boolean mSizeChanged;
    private boolean mSurfaceCreated;
    private int _testCallOrder = 0;
    private final Object mSyncObj = new Object();
    private int mWidth = 0;
    private int mHeight = 0;
    private boolean mRequestRender = true;
    private int mRenderMode = 1;

    VKRenderThread(VKSurfaceView.Renderer renderer) {
        this.mRenderer = renderer;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        setName("VK Render Thread " + getId());
        try {
            guardedRun();
        } catch (InterruptedException unused) {
        }
    }

    private void guardedRun() throws InterruptedException {
        VKSurfaceView.Renderer renderer;
        VKSurfaceView.Renderer renderer2 = this.mRenderer;
        if (renderer2 != null) {
            renderer2.onRenderThreadStart();
        }
        boolean z = true;
        while (true) {
            synchronized (this.mSyncObj) {
                if (z) {
                    z = false;
                }
                this.mSyncObj.notifyAll();
                this._testCallOrder++;
                this.mSyncObj.wait();
                if (this.mShouldExit) {
                    this.mShouldExit = false;
                    this.mExited = true;
                    this.mSyncObj.notifyAll();
                    return;
                }
                boolean z2 = this.mPaused;
                boolean z3 = this.mRequestPaused;
                if (z2 != z3) {
                    if (z3) {
                        this.mRenderer.onPause();
                        this.mSyncObj.notifyAll();
                    } else {
                        this.mRenderer.onResume();
                        this.mPaused = this.mRequestPaused;
                    }
                }
                if (this.mSurfaceCreated && !this.mConfirmedSurfaceCreation) {
                    VKSurfaceView.Renderer renderer3 = this.mRenderer;
                    if (renderer3 != null) {
                        renderer3.onSurfaceCreated();
                    }
                    this.mConfirmedSurfaceCreation = true;
                }
                if (!this.mSurfaceCreated && !this.mConfirmedSurfaceDestruction) {
                    VKSurfaceView.Renderer renderer4 = this.mRenderer;
                    if (renderer4 != null) {
                        renderer4.onSurfaceDestroyed();
                    }
                    this.mConfirmedSurfaceDestruction = true;
                }
                if (this.mSizeChanged) {
                    VKSurfaceView.Renderer renderer5 = this.mRenderer;
                    if (renderer5 != null) {
                        renderer5.onSurfaceChanged(this.mWidth, this.mHeight);
                    }
                    this.mSizeChanged = false;
                }
                if (readyToDraw() && true == this.mRequestRender && (renderer = this.mRenderer) != null) {
                    renderer.onDrawFrame();
                    this.mRequestRender = false;
                }
                this.mRenderComplete = true;
                this.mRenderer.onRenderThreadStop();
            }
        }
    }

    public boolean ableToDraw() {
        return readyToDraw();
    }

    private boolean readyToDraw() {
        if (this.mPaused || !this.mSurfaceCreated || this.mWidth <= 0 || this.mHeight <= 0) {
            return false;
        }
        return this.mRequestRender || this.mRenderMode == 1;
    }

    public void setRenderMode(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("renderMode");
        }
        synchronized (this.mSyncObj) {
            this.mRenderMode = i;
            this.mSyncObj.notifyAll();
        }
    }

    public int getRenderMode() {
        int i;
        synchronized (this.mSyncObj) {
            i = this.mRenderMode;
        }
        return i;
    }

    public void requestRender() {
        synchronized (this.mSyncObj) {
            this.mRequestRender = true;
            this.mSyncObj.notifyAll();
        }
    }

    public void surfaceCreated() {
        synchronized (this.mSyncObj) {
            this.mSurfaceCreated = true;
            this.mConfirmedSurfaceCreation = false;
            while (!this.mConfirmedSurfaceCreation && !this.mExited) {
                try {
                    this.mSyncObj.notifyAll();
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void surfaceDestroyed() {
        synchronized (this.mSyncObj) {
            this.mSurfaceCreated = false;
            this.mConfirmedSurfaceDestruction = false;
            while (!this.mConfirmedSurfaceDestruction && !this.mExited) {
                try {
                    this.mSyncObj.notifyAll();
                    this._testCallOrder++;
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void onPause() {
        synchronized (this.mSyncObj) {
            this.mRequestPaused = true;
            while (!this.mExited && !this.mPaused) {
                try {
                    this.mSyncObj.notifyAll();
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void onResume() {
        synchronized (this.mSyncObj) {
            this.mRequestPaused = false;
            this.mRequestRender = true;
            this.mRenderComplete = false;
            while (!this.mExited && this.mPaused && !this.mRenderComplete) {
                try {
                    this.mSyncObj.notifyAll();
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void onWindowResize(int i, int i2) {
        synchronized (this.mSyncObj) {
            this.mWidth = i;
            this.mHeight = i2;
            this.mSizeChanged = true;
            this.mRequestRender = true;
            this.mRenderComplete = false;
            if (Thread.currentThread() == this) {
                return;
            }
            while (!this.mExited && !this.mPaused && !this.mRenderComplete && ableToDraw()) {
                try {
                    this.mSyncObj.notifyAll();
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void requestExitAndWait() {
        synchronized (this.mSyncObj) {
            this.mShouldExit = true;
            while (!this.mExited) {
                try {
                    this.mSyncObj.notifyAll();
                    this.mSyncObj.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

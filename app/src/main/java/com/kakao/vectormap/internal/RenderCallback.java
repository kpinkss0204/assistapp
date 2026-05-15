package com.kakao.vectormap.internal;

import android.os.Build;
import android.view.Choreographer;
import android.view.SurfaceView;
import com.kakao.vectormap.graphics.IMapSurfaceView;

/* JADX INFO: loaded from: classes4.dex */
public class RenderCallback {
    private OnFrame onFrameCallback;
    private OnVsync onVsyncCallback;

    public static class OnFrame implements Choreographer.FrameCallback {
        private IMapSurfaceView surfaceView;

        public OnFrame(IMapSurfaceView iMapSurfaceView) {
            this.surfaceView = iMapSurfaceView;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            Choreographer.getInstance().postFrameCallback(this);
            IMapSurfaceView iMapSurfaceView = this.surfaceView;
            if (iMapSurfaceView != null) {
                iMapSurfaceView.requestRender();
            }
        }

        SurfaceView getSurfaceView() {
            return this.surfaceView.getView();
        }
    }

    public static class OnVsync implements Choreographer.VsyncCallback {
        private IMapSurfaceView surfaceView;

        public OnVsync(IMapSurfaceView iMapSurfaceView) {
            this.surfaceView = iMapSurfaceView;
        }

        @Override // android.view.Choreographer.VsyncCallback
        public void onVsync(Choreographer.FrameData frameData) {
            Choreographer.getInstance().postVsyncCallback(this);
            IMapSurfaceView iMapSurfaceView = this.surfaceView;
            if (iMapSurfaceView != null) {
                iMapSurfaceView.requestRender();
            }
        }

        SurfaceView getSurfaceView() {
            return this.surfaceView.getView();
        }
    }

    public RenderCallback(IMapSurfaceView iMapSurfaceView) {
        if (Build.VERSION.SDK_INT >= 33) {
            this.onVsyncCallback = new OnVsync(iMapSurfaceView);
        } else {
            this.onFrameCallback = new OnFrame(iMapSurfaceView);
        }
    }

    public void removeCallback() {
        if (Build.VERSION.SDK_INT >= 33) {
            Choreographer.getInstance().removeVsyncCallback(this.onVsyncCallback);
        } else {
            Choreographer.getInstance().removeFrameCallback(this.onFrameCallback);
        }
    }

    public void postCallback() {
        if (Build.VERSION.SDK_INT >= 33) {
            Choreographer.getInstance().postVsyncCallback(this.onVsyncCallback);
        } else {
            Choreographer.getInstance().postFrameCallback(this.onFrameCallback);
        }
    }

    SurfaceView getSurfaceView() {
        if (Build.VERSION.SDK_INT >= 33) {
            return this.onVsyncCallback.getSurfaceView();
        }
        return this.onFrameCallback.getSurfaceView();
    }
}

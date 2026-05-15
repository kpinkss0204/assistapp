package com.kakao.vectormap.internal;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.graphics.vk.VKRenderer;

/* JADX INFO: loaded from: classes4.dex */
public class VKMapRenderer extends VKRenderer {
    private IEngineHandler engineHandler;
    private boolean readyToStartEngine;

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public String getEngineState() {
        return null;
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onPause() {
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onResume() {
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public void setEngineHandler(Object obj) {
        this.engineHandler = (EngineHandler) obj;
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public void resume() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler == null) {
            MapLogger.e("resume failure. EngineHandler is null.");
            return;
        }
        try {
            iEngineHandler.resume();
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public boolean isResumed() throws RuntimeException {
        try {
            return this.engineHandler.isResumed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public void pause() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler == null) {
            MapLogger.e("pause failure. EngineHandler is null.");
            return;
        }
        try {
            iEngineHandler.pause();
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public boolean isPaused() throws RuntimeException {
        try {
            return this.engineHandler.isPaused();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onRenderThreadStart() {
        this.readyToStartEngine = true;
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onRenderThreadStop() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.stop();
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onSurfaceCreated() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler == null) {
            MapLogger.e("onSurfaceCreated return. engineHandler is null");
            return;
        }
        if (!this.readyToStartEngine) {
            iEngineHandler.updateSurface();
            this.engineHandler.resume();
            return;
        }
        try {
            iEngineHandler.start(24, "vulkan");
            this.readyToStartEngine = false;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onSurfaceChanged(int i, int i2) {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.resize(i, i2);
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public void onSurfaceDestroyed() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.pause();
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView.Renderer
    public boolean onDrawFrame() {
        return this.engineHandler.render();
    }
}

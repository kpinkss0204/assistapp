package com.kakao.vectormap.internal;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.graphics.gl.GLRenderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes4.dex */
public class GLMapRenderer extends GLRenderer {
    private IEngineHandler engineHandler;

    @Override // com.kakao.vectormap.graphics.gl.IEglLifeCycleCallback
    public void eglContextDestroyed() {
    }

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public void setEngineHandler(Object obj) {
        this.engineHandler = (IEngineHandler) obj;
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

    @Override // com.kakao.vectormap.graphics.MapRenderer
    public String getEngineState() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler == null) {
            return "EngineHandler is null.";
        }
        return iEngineHandler.getEngineState();
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        if (this.engineHandler == null) {
            MapLogger.e("onSurfaceCreated return. engineHandler is null");
            return;
        }
        gl10.glClearColor(0.949f, 1.0f, 0.968f, 1.0f);
        try {
            this.engineHandler.start(24, gl10.glGetString(7937));
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.resize(i, i2);
        }
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.Renderer
    public boolean onDrawFrame(GL10 gl10) {
        return this.engineHandler.render();
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.Renderer
    public void removeVsyncCallback() {
        this.engineHandler.removeVsyncCallbackOnUiThread();
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.Renderer
    public void onEngineStop() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.stop();
        }
    }

    @Override // com.kakao.vectormap.graphics.gl.IEglLifeCycleCallback
    public synchronized void eglSurfaceCreated() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.resume();
        }
    }

    @Override // com.kakao.vectormap.graphics.gl.IEglLifeCycleCallback
    public synchronized void eglSurfaceDestroyed() {
        IEngineHandler iEngineHandler = this.engineHandler;
        if (iEngineHandler != null) {
            iEngineHandler.pause();
        }
    }
}

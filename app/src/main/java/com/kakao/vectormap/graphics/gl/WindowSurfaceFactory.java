package com.kakao.vectormap.graphics.gl;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.graphics.gl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* JADX INFO: loaded from: classes4.dex */
class WindowSurfaceFactory implements GLSurfaceView.EGLWindowSurfaceFactory {
    private IEglLifeCycleCallback callback;

    public WindowSurfaceFactory(IEglLifeCycleCallback iEglLifeCycleCallback) {
        this.callback = iEglLifeCycleCallback;
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLWindowSurfaceFactory
    public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
        EGLSurface eGLSurfaceEglCreateWindowSurface = null;
        try {
            eGLSurfaceEglCreateWindowSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            IEglLifeCycleCallback iEglLifeCycleCallback = this.callback;
            if (iEglLifeCycleCallback != null) {
                iEglLifeCycleCallback.eglSurfaceCreated();
            }
            return eGLSurfaceEglCreateWindowSurface;
        } catch (IllegalArgumentException e) {
            MapLogger.e("eglCreateWindowSurface - " + e);
            return eGLSurfaceEglCreateWindowSurface;
        }
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLWindowSurfaceFactory
    public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        IEglLifeCycleCallback iEglLifeCycleCallback = this.callback;
        if (iEglLifeCycleCallback != null) {
            iEglLifeCycleCallback.eglSurfaceDestroyed();
        }
    }
}

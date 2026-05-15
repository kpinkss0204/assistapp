package com.kakao.vectormap.graphics.gl;

import android.util.Log;
import com.kakao.vectormap.graphics.gl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes4.dex */
public class EglContextFactory implements GLSurfaceView.EGLContextFactory {
    private int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private IEglLifeCycleCallback eglContextCallback;
    private int eglContextClientVersion;

    public EglContextFactory(int i, IEglLifeCycleCallback iEglLifeCycleCallback) {
        this.eglContextClientVersion = i;
        this.eglContextCallback = iEglLifeCycleCallback;
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLContextFactory
    public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        int[] iArr = {this.EGL_CONTEXT_CLIENT_VERSION, this.eglContextClientVersion, EGL14.EGL_NONE};
        EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
        if (this.eglContextClientVersion == 0) {
            iArr = null;
        }
        return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLContextFactory
    public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
        if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
            return;
        }
        Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
    }
}

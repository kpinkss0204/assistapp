package com.kakao.vectormap.graphics.gl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLDebugHelper;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.kakao.vectormap.MapLogger;
import java.io.Writer;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes4.dex */
public abstract class GLSurfaceView extends SurfaceView implements SurfaceHolder.Callback2 {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final String GLTHREAD_NAME = "GLThread";
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "GLSurfaceView";
    private static int mEGLContextClientVersion;
    private static GLThreadManager sGLThreadManager = new GLThreadManager();
    private int mDebugFlags;
    private boolean mDetached;
    private EGLConfigChooser mEGLConfigChooser;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private GLThread mGLThread;
    private GLWrapper mGLWrapper;
    private boolean mPreserveEGLContextOnPause;
    private Renderer mRenderer;
    private final WeakReference<GLSurfaceView> mThisWeakRef;

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    public interface Renderer {
        boolean onDrawFrame(GL10 gl10);

        void onEngineStop();

        void onSurfaceChanged(GL10 gl10, int i, int i2);

        void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig);

        void removeVsyncCallback();
    }

    public abstract void initEngine(boolean z);

    public abstract boolean isFinishManually();

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    public GLSurfaceView(Context context) {
        super(context);
        this.mThisWeakRef = new WeakReference<>(this);
        init();
    }

    public GLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mThisWeakRef = new WeakReference<>(this);
        init();
    }

    protected void finalize() throws Throwable {
        try {
            GLThread gLThread = this.mGLThread;
            if (gLThread != null) {
                gLThread.requestExitAndWait();
            }
        } catch (Exception e) {
            MapLogger.e(e);
        } finally {
            super.finalize();
        }
    }

    private void init() {
        getHolder().addCallback(this);
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setDebugFlags(int i) {
        this.mDebugFlags = i;
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.mPreserveEGLContextOnPause = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.mPreserveEGLContextOnPause;
    }

    public void setRenderer(Renderer renderer, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new SimpleEGLConfigChooser(true);
        }
        if (this.mEGLContextFactory == null) {
            this.mEGLContextFactory = new DefaultContextFactory();
        }
        if (this.mEGLWindowSurfaceFactory == null) {
            this.mEGLWindowSurfaceFactory = new DefaultWindowSurfaceFactory();
        }
        this.mRenderer = renderer;
        GLThread gLThread = new GLThread(this.mThisWeakRef);
        this.mGLThread = gLThread;
        gLThread.setName(GLTHREAD_NAME);
        this.mGLThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        this.mGLThread.start();
    }

    public void setEGLContextFactory(EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.mEGLContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.mEGLWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.mEGLConfigChooser = eGLConfigChooser;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new SimpleEGLConfigChooser(z));
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new ComponentSizeChooser(i, i2, i3, i4, i5, i6));
    }

    public void setEGLContextClientVersion(int i) {
        checkRenderThreadState();
        mEGLContextClientVersion = i;
    }

    public void setRenderMode(int i) {
        this.mGLThread.setRenderMode(i);
    }

    public int getRenderMode() {
        return this.mGLThread.getRenderMode();
    }

    public void requestRender() {
        this.mGLThread.requestRender();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceCreated();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mGLThread.surfaceDestroyed();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mGLThread.onWindowResize(i2, i3);
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.requestRenderAndNotify(runnable);
        }
    }

    public void onPause() {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.onPause();
        }
    }

    public void onResume() {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.onResume();
        }
    }

    public void queueEvent(Runnable runnable) {
        GLThread gLThread = this.mGLThread;
        if (gLThread != null) {
            gLThread.queueEvent(runnable);
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        GLThread gLThread;
        super.onAttachedToWindow();
        if (this.mDetached && ((gLThread = this.mGLThread) == null || gLThread.getState() == Thread.State.TERMINATED)) {
            initEngine(this.mGLThread == null);
        }
        this.mDetached = false;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        if (isFinishManually()) {
            this.mDetached = true;
            super.onDetachedFromWindow();
            MapLogger.d("onDetachedFromWindow(isFinishManually=true)");
            return;
        }
        boolean zIsFinishing = false;
        try {
            Context context = getContext();
            if (context instanceof Activity) {
                zIsFinishing = ((Activity) context).isFinishing();
                if (zIsFinishing) {
                    requestExit();
                }
            } else {
                MapLogger.w("Don't forget to call MapView.finish().");
            }
        } catch (Exception e) {
            MapLogger.e(e);
        }
        this.mDetached = true;
        super.onDetachedFromWindow();
        MapLogger.d("onDetachedFromWindow(isFinishing=" + zIsFinishing + ")");
    }

    public void requestExit() {
        GLThread gLThread;
        if (this.mRenderer == null || (gLThread = this.mGLThread) == null) {
            return;
        }
        gLThread.queueEvent(new Runnable() { // from class: com.kakao.vectormap.graphics.gl.GLSurfaceView.1
            @Override // java.lang.Runnable
            public void run() {
                GLSurfaceView.this.mRenderer.onEngineStop();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.graphics.gl.GLSurfaceView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GLSurfaceView.this.mGLThread.requestExitAndWait();
                        GLSurfaceView.this.mRenderer.removeVsyncCallback();
                    }
                });
            }
        });
    }

    private class DefaultContextFactory implements EGLContextFactory {
        private int EGL_CONTEXT_CLIENT_VERSION;

        private DefaultContextFactory() {
            this.EGL_CONTEXT_CLIENT_VERSION = 12440;
        }

        @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLContextFactory
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.EGL_CONTEXT_CLIENT_VERSION, GLSurfaceView.mEGLContextClientVersion, EGL14.EGL_NONE};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (GLSurfaceView.mEGLContextClientVersion == 0) {
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
            EglHelper.throwEglException("eglDestroyContex", egl10.eglGetError());
        }
    }

    private static class DefaultWindowSurfaceFactory implements EGLWindowSurfaceFactory {
        private DefaultWindowSurfaceFactory() {
        }

        @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLWindowSurfaceFactory
        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e(GLSurfaceView.TAG, "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLWindowSurfaceFactory
        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    public static abstract class BaseConfigChooser implements EGLConfigChooser {
        protected int[] mConfigSpec;

        protected abstract EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public BaseConfigChooser(int[] iArr) {
            this.mConfigSpec = filterConfigSpec(iArr);
        }

        @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, eGLConfigArr, i, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            EGLConfig eGLConfigChooseConfig = chooseConfig(egl10, eGLDisplay, eGLConfigArr);
            if (eGLConfigChooseConfig != null) {
                return eGLConfigChooseConfig;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private int[] filterConfigSpec(int[] iArr) {
            if (GLSurfaceView.mEGLContextClientVersion != 2 && GLSurfaceView.mEGLContextClientVersion != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            if (GLSurfaceView.mEGLContextClientVersion == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    private class ComponentSizeChooser extends BaseConfigChooser {
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue;

        public ComponentSizeChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{EGL14.EGL_RED_SIZE, i, EGL14.EGL_GREEN_SIZE, i2, EGL14.EGL_BLUE_SIZE, i3, EGL14.EGL_ALPHA_SIZE, i4, EGL14.EGL_DEPTH_SIZE, i5, EGL14.EGL_STENCIL_SIZE, i6, EGL14.EGL_NONE});
            this.mValue = new int[1];
            this.mRedSize = i;
            this.mGreenSize = i2;
            this.mBlueSize = i3;
            this.mAlphaSize = i4;
            this.mDepthSize = i5;
            this.mStencilSize = i6;
        }

        @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.BaseConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            int length = eGLConfigArr.length;
            int i = 0;
            while (i < length) {
                EGLConfig eGLConfig = eGLConfigArr[i];
                EGL10 egl102 = egl10;
                EGLDisplay eGLDisplay2 = eGLDisplay;
                int iFindConfigAttrib = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_DEPTH_SIZE, 0);
                int iFindConfigAttrib2 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_STENCIL_SIZE, 0);
                if (iFindConfigAttrib >= this.mDepthSize && iFindConfigAttrib2 >= this.mStencilSize) {
                    int iFindConfigAttrib3 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_RED_SIZE, 0);
                    int iFindConfigAttrib4 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_GREEN_SIZE, 0);
                    int iFindConfigAttrib5 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_BLUE_SIZE, 0);
                    int iFindConfigAttrib6 = findConfigAttrib(egl102, eGLDisplay2, eGLConfig, EGL14.EGL_ALPHA_SIZE, 0);
                    if (iFindConfigAttrib3 == this.mRedSize && iFindConfigAttrib4 == this.mGreenSize && iFindConfigAttrib5 == this.mBlueSize && iFindConfigAttrib6 == this.mAlphaSize) {
                        return eGLConfig;
                    }
                }
                i++;
                egl10 = egl102;
                eGLDisplay = eGLDisplay2;
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.mValue) ? this.mValue[0] : i2;
        }
    }

    private class SimpleEGLConfigChooser extends ComponentSizeChooser {
        public SimpleEGLConfigChooser(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    private static class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;
        private WeakReference<GLSurfaceView> mGLSurfaceViewWeakRef;

        public EglHelper(WeakReference<GLSurfaceView> weakReference) {
            this.mGLSurfaceViewWeakRef = weakReference;
        }

        public void start() {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.mEgl = egl10;
            EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEglDisplay = eGLDisplayEglGetDisplay;
            if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.mEgl.eglInitialize(this.mEglDisplay, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            GLSurfaceView gLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (gLSurfaceView != null) {
                this.mEglConfig = gLSurfaceView.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
                this.mEglContext = gLSurfaceView.mEGLContextFactory.createContext(this.mEgl, this.mEglDisplay, this.mEglConfig);
            } else {
                this.mEglConfig = null;
                this.mEglContext = null;
            }
            EGLContext eGLContext = this.mEglContext;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.mEglContext = null;
                throwEglException("createContext");
            }
            this.mEglSurface = null;
        }

        public boolean createSurface() {
            if (this.mEgl == null) {
                throw new RuntimeException("egl not initialized");
            }
            if (this.mEglDisplay == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.mEglConfig == null) {
                throw new RuntimeException("mEglConfig not initialized");
            }
            destroySurfaceImp();
            GLSurfaceView gLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (gLSurfaceView != null) {
                this.mEglSurface = gLSurfaceView.mEGLWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, gLSurfaceView.getHolder());
            } else {
                this.mEglSurface = null;
            }
            EGLSurface eGLSurface = this.mEglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.mEgl.eglGetError() == 12299) {
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            EGL10 egl10 = this.mEgl;
            EGLDisplay eGLDisplay = this.mEglDisplay;
            EGLSurface eGLSurface2 = this.mEglSurface;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.mEglContext)) {
                return true;
            }
            logEglErrorAsWarning("EGLHelper", "eglMakeCurrent", this.mEgl.eglGetError());
            return false;
        }

        GL createGL() {
            GL gl = this.mEglContext.getGL();
            GLSurfaceView gLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (gLSurfaceView == null) {
                return gl;
            }
            if (gLSurfaceView.mGLWrapper != null) {
                gl = gLSurfaceView.mGLWrapper.wrap(gl);
            }
            if ((gLSurfaceView.mDebugFlags & 3) != 0) {
                return GLDebugHelper.wrap(gl, (gLSurfaceView.mDebugFlags & 1) == 0 ? 0 : 1, (gLSurfaceView.mDebugFlags & 2) != 0 ? new LogWriter() : null);
            }
            return gl;
        }

        public int swap() {
            return !this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface) ? this.mEgl.eglGetError() : EGL14.EGL_SUCCESS;
        }

        public void destroySurface() {
            destroySurfaceImp();
        }

        private void destroySurfaceImp() {
            EGLSurface eGLSurface = this.mEglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            GLSurfaceView gLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (gLSurfaceView != null) {
                gLSurfaceView.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
            }
            this.mEglSurface = null;
        }

        public void finish() {
            if (this.mEglContext != null) {
                GLSurfaceView gLSurfaceView = this.mGLSurfaceViewWeakRef.get();
                if (gLSurfaceView != null) {
                    gLSurfaceView.mEGLContextFactory.destroyContext(this.mEgl, this.mEglDisplay, this.mEglContext);
                }
                this.mEglContext = null;
            }
            EGLDisplay eGLDisplay = this.mEglDisplay;
            if (eGLDisplay != null) {
                this.mEgl.eglTerminate(eGLDisplay);
                this.mEglDisplay = null;
            }
        }

        private void throwEglException(String str) {
            throwEglException(str, this.mEgl.eglGetError());
        }

        public static void throwEglException(String str, int i) {
            throw new RuntimeException(formatEglError(str, i));
        }

        public static void logEglErrorAsWarning(String str, String str2, int i) {
            Log.w(str, formatEglError(str2, i));
        }

        public static String formatEglError(String str, int i) {
            return str + " failed: " + EGLLogWrapper.getErrorString(i);
        }
    }

    static class GLThread extends Thread {
        private EglHelper mEglHelper;
        private boolean mExited;
        private boolean mFinishedCreatingEglSurface;
        private WeakReference<GLSurfaceView> mGLSurfaceViewWeakRef;
        private boolean mHasSurface;
        private boolean mHaveEglContext;
        private boolean mHaveEglSurface;
        private boolean mPaused;
        private boolean mRenderComplete;
        private boolean mRequestPaused;
        private boolean mShouldExit;
        private boolean mShouldReleaseEglContext;
        private boolean mSurfaceIsBad;
        private boolean mWaitingForSurface;
        private ArrayList<Runnable> mEventQueue = new ArrayList<>();
        private boolean mSizeChanged = true;
        private Runnable mFinishDrawingRunnable = null;
        private int mWidth = 0;
        private int mHeight = 0;
        private boolean mRequestRender = true;
        private int mRenderMode = 1;
        private boolean mWantRenderNotification = false;

        GLThread(WeakReference<GLSurfaceView> weakReference) {
            this.mGLSurfaceViewWeakRef = weakReference;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                guardedRun();
            } catch (Exception unused) {
            } catch (Throwable th) {
                GLSurfaceView.sGLThreadManager.threadExiting(this);
                throw th;
            }
            GLSurfaceView.sGLThreadManager.threadExiting(this);
        }

        private void stopEglSurfaceLocked() {
            if (this.mHaveEglSurface) {
                this.mHaveEglSurface = false;
                this.mEglHelper.destroySurface();
            }
        }

        private void stopEglContextLocked() {
            if (this.mHaveEglContext) {
                this.mEglHelper.finish();
                this.mHaveEglContext = false;
                GLSurfaceView.sGLThreadManager.releaseEglContextLocked(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:111:0x017c A[Catch: all -> 0x022f, TryCatch #7 {all -> 0x022f, blocks: (B:3:0x001f, B:4:0x0023, B:87:0x013e, B:90:0x0147, B:92:0x014f, B:93:0x0153, B:100:0x0163, B:101:0x0164, B:102:0x0168, B:109:0x0179, B:111:0x017c, B:113:0x0188, B:115:0x0192, B:118:0x01a0, B:120:0x01aa, B:122:0x01b2, B:124:0x01bc, B:126:0x01c6, B:130:0x01d4, B:131:0x01e1, B:146:0x0207, B:138:0x01f0, B:155:0x022e, B:95:0x0155, B:96:0x015e, B:104:0x016a, B:105:0x0175, B:5:0x0024, B:7:0x0028, B:16:0x0039, B:18:0x0041, B:85:0x013b, B:19:0x004e, B:21:0x0054, B:23:0x005f, B:25:0x0063, B:27:0x006f, B:29:0x0078, B:31:0x007c, B:33:0x0081, B:35:0x0085, B:40:0x0097, B:38:0x0091, B:41:0x009a, B:43:0x009e, B:45:0x00a2, B:47:0x00a6, B:48:0x00a9, B:49:0x00b6, B:51:0x00ba, B:53:0x00be, B:55:0x00ca, B:56:0x00d8, B:58:0x00dc, B:60:0x00e2, B:62:0x00e8, B:66:0x00f0, B:68:0x00f6, B:70:0x0102, B:71:0x0109, B:72:0x010a, B:74:0x010e, B:76:0x0112, B:77:0x0118, B:79:0x011c, B:81:0x0120, B:82:0x012c, B:152:0x0222, B:151:0x0217, B:133:0x01e3, B:134:0x01ec), top: B:178:0x001f, inners: #0, #3, #4, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:113:0x0188 A[Catch: all -> 0x022f, TryCatch #7 {all -> 0x022f, blocks: (B:3:0x001f, B:4:0x0023, B:87:0x013e, B:90:0x0147, B:92:0x014f, B:93:0x0153, B:100:0x0163, B:101:0x0164, B:102:0x0168, B:109:0x0179, B:111:0x017c, B:113:0x0188, B:115:0x0192, B:118:0x01a0, B:120:0x01aa, B:122:0x01b2, B:124:0x01bc, B:126:0x01c6, B:130:0x01d4, B:131:0x01e1, B:146:0x0207, B:138:0x01f0, B:155:0x022e, B:95:0x0155, B:96:0x015e, B:104:0x016a, B:105:0x0175, B:5:0x0024, B:7:0x0028, B:16:0x0039, B:18:0x0041, B:85:0x013b, B:19:0x004e, B:21:0x0054, B:23:0x005f, B:25:0x0063, B:27:0x006f, B:29:0x0078, B:31:0x007c, B:33:0x0081, B:35:0x0085, B:40:0x0097, B:38:0x0091, B:41:0x009a, B:43:0x009e, B:45:0x00a2, B:47:0x00a6, B:48:0x00a9, B:49:0x00b6, B:51:0x00ba, B:53:0x00be, B:55:0x00ca, B:56:0x00d8, B:58:0x00dc, B:60:0x00e2, B:62:0x00e8, B:66:0x00f0, B:68:0x00f6, B:70:0x0102, B:71:0x0109, B:72:0x010a, B:74:0x010e, B:76:0x0112, B:77:0x0118, B:79:0x011c, B:81:0x0120, B:82:0x012c, B:152:0x0222, B:151:0x0217, B:133:0x01e3, B:134:0x01ec), top: B:178:0x001f, inners: #0, #3, #4, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:118:0x01a0 A[Catch: all -> 0x022f, TryCatch #7 {all -> 0x022f, blocks: (B:3:0x001f, B:4:0x0023, B:87:0x013e, B:90:0x0147, B:92:0x014f, B:93:0x0153, B:100:0x0163, B:101:0x0164, B:102:0x0168, B:109:0x0179, B:111:0x017c, B:113:0x0188, B:115:0x0192, B:118:0x01a0, B:120:0x01aa, B:122:0x01b2, B:124:0x01bc, B:126:0x01c6, B:130:0x01d4, B:131:0x01e1, B:146:0x0207, B:138:0x01f0, B:155:0x022e, B:95:0x0155, B:96:0x015e, B:104:0x016a, B:105:0x0175, B:5:0x0024, B:7:0x0028, B:16:0x0039, B:18:0x0041, B:85:0x013b, B:19:0x004e, B:21:0x0054, B:23:0x005f, B:25:0x0063, B:27:0x006f, B:29:0x0078, B:31:0x007c, B:33:0x0081, B:35:0x0085, B:40:0x0097, B:38:0x0091, B:41:0x009a, B:43:0x009e, B:45:0x00a2, B:47:0x00a6, B:48:0x00a9, B:49:0x00b6, B:51:0x00ba, B:53:0x00be, B:55:0x00ca, B:56:0x00d8, B:58:0x00dc, B:60:0x00e2, B:62:0x00e8, B:66:0x00f0, B:68:0x00f6, B:70:0x0102, B:71:0x0109, B:72:0x010a, B:74:0x010e, B:76:0x0112, B:77:0x0118, B:79:0x011c, B:81:0x0120, B:82:0x012c, B:152:0x0222, B:151:0x0217, B:133:0x01e3, B:134:0x01ec), top: B:178:0x001f, inners: #0, #3, #4, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:124:0x01bc A[Catch: all -> 0x022f, TryCatch #7 {all -> 0x022f, blocks: (B:3:0x001f, B:4:0x0023, B:87:0x013e, B:90:0x0147, B:92:0x014f, B:93:0x0153, B:100:0x0163, B:101:0x0164, B:102:0x0168, B:109:0x0179, B:111:0x017c, B:113:0x0188, B:115:0x0192, B:118:0x01a0, B:120:0x01aa, B:122:0x01b2, B:124:0x01bc, B:126:0x01c6, B:130:0x01d4, B:131:0x01e1, B:146:0x0207, B:138:0x01f0, B:155:0x022e, B:95:0x0155, B:96:0x015e, B:104:0x016a, B:105:0x0175, B:5:0x0024, B:7:0x0028, B:16:0x0039, B:18:0x0041, B:85:0x013b, B:19:0x004e, B:21:0x0054, B:23:0x005f, B:25:0x0063, B:27:0x006f, B:29:0x0078, B:31:0x007c, B:33:0x0081, B:35:0x0085, B:40:0x0097, B:38:0x0091, B:41:0x009a, B:43:0x009e, B:45:0x00a2, B:47:0x00a6, B:48:0x00a9, B:49:0x00b6, B:51:0x00ba, B:53:0x00be, B:55:0x00ca, B:56:0x00d8, B:58:0x00dc, B:60:0x00e2, B:62:0x00e8, B:66:0x00f0, B:68:0x00f6, B:70:0x0102, B:71:0x0109, B:72:0x010a, B:74:0x010e, B:76:0x0112, B:77:0x0118, B:79:0x011c, B:81:0x0120, B:82:0x012c, B:152:0x0222, B:151:0x0217, B:133:0x01e3, B:134:0x01ec), top: B:178:0x001f, inners: #0, #3, #4, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:149:0x0211  */
        /* JADX WARN: Removed duplicated region for block: B:174:0x0235 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void guardedRun() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instruction units count: 576
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.graphics.gl.GLSurfaceView.GLThread.guardedRun():void");
        }

        public boolean ableToDraw() {
            return this.mHaveEglContext && this.mHaveEglSurface && readyToDraw();
        }

        private boolean readyToDraw() {
            if (this.mPaused || !this.mHasSurface || this.mSurfaceIsBad || this.mWidth <= 0 || this.mHeight <= 0) {
                return false;
            }
            return this.mRequestRender || this.mRenderMode == 1;
        }

        public void setRenderMode(int i) {
            if (i >= 0 && i <= 1) {
                synchronized (GLSurfaceView.sGLThreadManager) {
                    this.mRenderMode = i;
                    GLSurfaceView.sGLThreadManager.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("renderMode");
        }

        public int getRenderMode() {
            int i;
            synchronized (GLSurfaceView.sGLThreadManager) {
                i = this.mRenderMode;
            }
            return i;
        }

        public void requestRender() {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mRequestRender = true;
                GLSurfaceView.sGLThreadManager.notifyAll();
            }
        }

        public void requestRenderAndNotify(Runnable runnable) {
            synchronized (GLSurfaceView.sGLThreadManager) {
                if (Thread.currentThread() == this) {
                    return;
                }
                this.mWantRenderNotification = true;
                this.mRequestRender = true;
                this.mRenderComplete = false;
                this.mFinishDrawingRunnable = runnable;
                GLSurfaceView.sGLThreadManager.notifyAll();
            }
        }

        public void surfaceCreated() {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mHasSurface = true;
                this.mFinishedCreatingEglSurface = false;
                GLSurfaceView.sGLThreadManager.notifyAll();
                while (this.mWaitingForSurface && !this.mFinishedCreatingEglSurface && !this.mExited) {
                    try {
                        GLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void surfaceDestroyed() {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mHasSurface = false;
                GLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mWaitingForSurface && !this.mExited) {
                    try {
                        GLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        MapLogger.e(e);
                    }
                }
            }
        }

        public void onPause() {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mRequestPaused = true;
                GLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited && !this.mPaused) {
                    try {
                        GLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onResume() {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mRequestPaused = false;
                this.mRequestRender = true;
                this.mRenderComplete = false;
                GLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited && this.mPaused && !this.mRenderComplete) {
                    try {
                        GLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void onWindowResize(int i, int i2) {
            synchronized (GLSurfaceView.sGLThreadManager) {
                this.mWidth = i;
                this.mHeight = i2;
                this.mSizeChanged = true;
                this.mRequestRender = true;
                this.mRenderComplete = false;
                if (Thread.currentThread() == this) {
                    return;
                }
                GLSurfaceView.sGLThreadManager.notifyAll();
                while (!this.mExited && !this.mPaused && !this.mRenderComplete && ableToDraw()) {
                    try {
                        GLSurfaceView.sGLThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        MapLogger.e(e);
                    }
                }
            }
        }

        public void requestExitAndWait() {
            if (Thread.currentThread().getName() != GLSurfaceView.GLTHREAD_NAME) {
                synchronized (GLSurfaceView.sGLThreadManager) {
                    this.mShouldExit = true;
                    GLSurfaceView.sGLThreadManager.notifyAll();
                    System.currentTimeMillis();
                    while (!this.mExited) {
                        try {
                            GLSurfaceView.sGLThreadManager.wait();
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        } catch (Exception e) {
                            MapLogger.e(e);
                        }
                    }
                }
                return;
            }
            MapLogger.e("requestExitAndWait() called from wrong thread");
        }

        public void requestReleaseEglContextLocked() {
            this.mShouldReleaseEglContext = true;
            GLSurfaceView.sGLThreadManager.notifyAll();
        }

        public void queueEvent(Runnable runnable) {
            if (runnable != null) {
                synchronized (GLSurfaceView.sGLThreadManager) {
                    this.mEventQueue.add(runnable);
                    GLSurfaceView.sGLThreadManager.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }
    }

    static class LogWriter extends Writer {
        private StringBuilder mBuilder = new StringBuilder();

        LogWriter() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            flushBuilder();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            flushBuilder();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    flushBuilder();
                } else {
                    this.mBuilder.append(c);
                }
            }
        }

        private void flushBuilder() {
            if (this.mBuilder.length() > 0) {
                Log.v(GLSurfaceView.TAG, this.mBuilder.toString());
                StringBuilder sb = this.mBuilder;
                sb.delete(0, sb.length());
            }
        }
    }

    private void checkRenderThreadState() {
        if (this.mGLThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    private static class GLThreadManager {
        private static String TAG = "GLThreadManager";

        private GLThreadManager() {
        }

        public synchronized void threadExiting(GLThread gLThread) {
            gLThread.mExited = true;
            notifyAll();
        }

        public void releaseEglContextLocked(GLThread gLThread) {
            notifyAll();
        }
    }
}

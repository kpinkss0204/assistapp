package com.kakao.vectormap.graphics.gl;

import android.util.Log;
import com.kakao.vectormap.graphics.gl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes4.dex */
public class KConfigChooser extends GLSurfaceView.BaseConfigChooser {
    private int mDepthSize;
    private int[] mValue;

    public KConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        super(new int[]{EGL14.EGL_RED_SIZE, i, EGL14.EGL_GREEN_SIZE, i2, EGL14.EGL_BLUE_SIZE, i3, EGL14.EGL_ALPHA_SIZE, i4, EGL14.EGL_DEPTH_SIZE, i5, EGL14.EGL_STENCIL_SIZE, i6, EGL14.EGL_NONE});
        this.mValue = new int[1];
        this.mDepthSize = i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0053 A[EDGE_INSN: B:24:0x0053->B:20:0x0053 BREAK  A[LOOP:0: B:3:0x0014->B:25:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[LOOP:0: B:3:0x0014->B:25:?, LOOP_END, SYNTHETIC] */
    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView.BaseConfigChooser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected javax.microedition.khronos.egl.EGLConfig chooseConfig(javax.microedition.khronos.egl.EGL10 r10, javax.microedition.khronos.egl.EGLDisplay r11, javax.microedition.khronos.egl.EGLConfig[] r12) {
        /*
            r9 = this;
            r7 = 24
            r9.mDepthSize = r7
            r5 = 8
            r8 = 8
            r3 = 8
            r4 = 8
            r6 = 0
            r0 = r9
            r1 = r10
            r2 = r11
            javax.microedition.khronos.egl.EGLConfig r3 = r0.chooseConfig(r1, r2, r3, r4, r5, r6, r7, r8)
        L14:
            if (r3 != 0) goto L53
            r1 = 24
            r2 = 8
            if (r6 != 0) goto L22
            int r4 = r9.mDepthSize
            if (r4 != r1) goto L22
        L20:
            r6 = r2
            goto L35
        L22:
            r4 = 16
            if (r6 != r2) goto L2e
            int r5 = r9.mDepthSize
            if (r5 != r1) goto L2e
            r9.mDepthSize = r4
            r2 = 0
            goto L20
        L2e:
            if (r6 != 0) goto L53
            int r1 = r9.mDepthSize
            if (r1 != r4) goto L53
            goto L20
        L35:
            int[] r1 = r9.mConfigSpec
            r2 = 7
            r1[r2] = r6
            int[] r1 = r9.mConfigSpec
            int r7 = r9.mDepthSize
            r2 = 9
            r1[r2] = r7
            r5 = 8
            r8 = 8
            r3 = 8
            r4 = 8
            r0 = r9
            r1 = r10
            r2 = r11
            javax.microedition.khronos.egl.EGLConfig r3 = r0.chooseConfig(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r3 == 0) goto L14
        L53:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kakao.vectormap.graphics.gl.KConfigChooser.chooseConfig(javax.microedition.khronos.egl.EGL10, javax.microedition.khronos.egl.EGLDisplay, javax.microedition.khronos.egl.EGLConfig[]):javax.microedition.khronos.egl.EGLConfig");
    }

    private EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = new int[1];
        if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, null, 0, iArr)) {
            Log.w("###", "eglChooseConfig failed (alphaSize=" + i4 + ", depthSize=" + i5 + ")");
            return null;
        }
        int i7 = iArr[0];
        if (i7 <= 0) {
            Log.w("###", "No configs match configSpec (alphaSize=" + i4 + ", depthSize=" + i5 + ")");
            return null;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[i7];
        if (!egl10.eglChooseConfig(eGLDisplay, this.mConfigSpec, eGLConfigArr, i7, iArr)) {
            Log.w("###", "eglChooseConfig#2 failed (alphaSize=" + i4 + ", depthSize=" + i5 + ")");
            return null;
        }
        EGLConfig eGLConfigChooseConfig = chooseConfig(egl10, eGLDisplay, eGLConfigArr, i, i2, i3, i4, i5, i6);
        if (eGLConfigChooseConfig != null) {
            return eGLConfigChooseConfig;
        }
        Log.w("###", "No config chosen (alphaSize=" + i4 + ", depthSize=" + i5 + ")");
        return null;
    }

    private EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr, int i, int i2, int i3, int i4, int i5, int i6) {
        for (EGLConfig eGLConfig : eGLConfigArr) {
            int iFindConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_DEPTH_SIZE, 0);
            int iFindConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_STENCIL_SIZE, 0);
            if (iFindConfigAttrib >= i5) {
                if (iFindConfigAttrib2 >= i6) {
                    int iFindConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_RED_SIZE, 0);
                    int iFindConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_GREEN_SIZE, 0);
                    int iFindConfigAttrib5 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_BLUE_SIZE, 0);
                    int iFindConfigAttrib6 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, EGL14.EGL_ALPHA_SIZE, 0);
                    if (iFindConfigAttrib3 == i) {
                        if (iFindConfigAttrib4 == i2) {
                            if (iFindConfigAttrib5 == i3 && iFindConfigAttrib6 == i4) {
                                return eGLConfig;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public int getDepthSize() {
        return this.mDepthSize;
    }

    private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.mValue) ? this.mValue[0] : i2;
    }
}

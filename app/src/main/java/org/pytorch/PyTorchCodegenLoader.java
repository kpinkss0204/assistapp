package org.pytorch;

import com.facebook.soloader.nativeloader.NativeLoader;

/* JADX INFO: loaded from: classes4.dex */
public class PyTorchCodegenLoader {
    public static void loadNativeLibs() {
        try {
            NativeLoader.loadLibrary("torch-code-gen");
        } catch (Throwable unused) {
        }
    }

    private PyTorchCodegenLoader() {
    }
}

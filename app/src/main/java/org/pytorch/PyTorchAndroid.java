package org.pytorch;

import android.content.res.AssetManager;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;

/* JADX INFO: loaded from: classes4.dex */
public final class PyTorchAndroid {
    private static native void nativeSetNumThreads(int i);

    static {
        if (!NativeLoader.isInitialized()) {
            NativeLoader.init(new SystemDelegate());
        }
        NativeLoader.loadLibrary("pytorch_jni_lite");
        PyTorchCodegenLoader.loadNativeLibs();
    }

    public static Module loadModuleFromAsset(AssetManager assetManager, String str, Device device) {
        return new Module(new NativePeer(str, assetManager, device));
    }

    public static Module loadModuleFromAsset(AssetManager assetManager, String str) {
        return new Module(new NativePeer(str, assetManager, Device.CPU));
    }

    public static void setNumThreads(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Number of threads cannot be less than 1");
        }
        nativeSetNumThreads(i);
    }
}

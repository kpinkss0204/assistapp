package org.pytorch;

import com.facebook.jni.HybridData;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
class NativePeer implements INativePeer {
    private final HybridData mHybridData;

    private static native HybridData initHybrid(String str, Map<String, String> map, int i);

    private static native HybridData initHybridAndroidAsset(String str, Object obj, int i);

    @Override // org.pytorch.INativePeer
    public native IValue forward(IValue... iValueArr);

    @Override // org.pytorch.INativePeer
    public native IValue runMethod(String str, IValue... iValueArr);

    static {
        NativeLoader.loadLibrary("pytorch_jni");
        PyTorchCodegenLoader.loadNativeLibs();
    }

    NativePeer(String str, Map<String, String> map, Device device) {
        this.mHybridData = initHybrid(str, map, device.jniCode);
    }

    NativePeer(String str, Object obj, Device device) {
        this.mHybridData = initHybridAndroidAsset(str, obj, device.jniCode);
    }

    @Override // org.pytorch.INativePeer
    public void resetNative() {
        this.mHybridData.resetNative();
    }
}

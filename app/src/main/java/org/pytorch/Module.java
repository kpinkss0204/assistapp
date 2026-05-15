package org.pytorch;

import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Module {
    private INativePeer mNativePeer;

    public static Module load(String str, Map<String, String> map, Device device) {
        if (!NativeLoader.isInitialized()) {
            NativeLoader.init(new SystemDelegate());
        }
        return new Module(new NativePeer(str, map, device));
    }

    public static Module load(String str) {
        return load(str, null, Device.CPU);
    }

    Module(INativePeer iNativePeer) {
        this.mNativePeer = iNativePeer;
    }

    public IValue forward(IValue... iValueArr) {
        return this.mNativePeer.forward(iValueArr);
    }

    public IValue runMethod(String str, IValue... iValueArr) {
        return this.mNativePeer.runMethod(str, iValueArr);
    }

    public void destroy() {
        this.mNativePeer.resetNative();
    }
}

package org.pytorch;

import android.content.res.AssetManager;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LiteModuleLoader {
    public static Module load(String str, Map<String, String> map, Device device) {
        return new Module(new LiteNativePeer(str, map, device));
    }

    public static Module load(String str) {
        return new Module(new LiteNativePeer(str, (Map<String, String>) null, Device.CPU));
    }

    public static Module loadModuleFromAsset(AssetManager assetManager, String str, Device device) {
        return new Module(new LiteNativePeer(str, assetManager, device));
    }

    public static Module loadModuleFromAsset(AssetManager assetManager, String str) {
        return new Module(new LiteNativePeer(str, assetManager, Device.CPU));
    }
}

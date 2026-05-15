package com.kakao.vectormap.internal;

import android.content.Context;
import android.graphics.Bitmap;
import com.kakao.vectormap.MapLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
class MapResourceManager implements IMapResourceManager {
    private long appEngineHandle;
    private Context context;
    private String viewName;

    static native void addImage(long j, String str, String str2, byte[] bArr);

    static native boolean hasImage(long j, String str, String str2);

    MapResourceManager(long j, String str, Context context) {
        this.appEngineHandle = j;
        this.viewName = str;
        this.context = context;
    }

    public boolean isDarkMode() {
        return (this.context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    @Override // com.kakao.vectormap.internal.IMapResourceManager
    public String addImage(int i, Bitmap bitmap) {
        if (i != 0) {
            String assetId = getAssetId(this.context, String.valueOf(i));
            addImage(this.appEngineHandle, this.viewName, this.context, assetId, i);
            return assetId;
        }
        if (bitmap != null) {
            String assetId2 = getAssetId(this.context, String.valueOf(bitmap.hashCode()));
            addImage(this.appEngineHandle, this.viewName, assetId2, bitmap);
            return assetId2;
        }
        MapLogger.e("ImageAsset is invalid.");
        return "";
    }

    public String getAssetId(int i, Bitmap bitmap) {
        if (i != 0) {
            return getAssetId(this.context, String.valueOf(i));
        }
        if (bitmap != null) {
            return getAssetId(this.context, String.valueOf(bitmap.hashCode()));
        }
        MapLogger.e("ImageAsset is invalid.");
        return "";
    }

    private String getAssetId(Context context, String str) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32 ? str + "_dark" : str;
    }

    static void addImage(long j, String str, Context context, String str2, int i) {
        if (hasImage(j, str, str2)) {
            return;
        }
        addImage(j, str, str2, getBytes(context, i));
    }

    static void addImage(long j, String str, String str2, Bitmap bitmap) {
        if (hasImage(j, str, str2)) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        addImage(j, str, str2, byteArrayOutputStream.toByteArray());
    }

    public byte[] getResourceBytes(int i, Bitmap bitmap) {
        if (i != 0) {
            return getBytes(this.context, i);
        }
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        return new byte[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    private static byte[] getBytes(Context context, int i) throws Throwable {
        InputStream inputStreamOpenRawResource;
        ?? r0 = 0;
        try {
            if (i <= 0) {
                return null;
            }
            try {
                inputStreamOpenRawResource = context.getResources().openRawResource(i);
                if (inputStreamOpenRawResource == null) {
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                            return null;
                        } catch (IOException e) {
                            MapLogger.e(e);
                        }
                    }
                    return null;
                }
                try {
                    int iAvailable = inputStreamOpenRawResource.available();
                    byte[] bArr = new byte[iAvailable];
                    inputStreamOpenRawResource.read(bArr, 0, iAvailable);
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                            return bArr;
                        } catch (IOException e2) {
                            MapLogger.e(e2);
                        }
                    }
                    return bArr;
                } catch (IOException e3) {
                    e = e3;
                    MapLogger.e(e);
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (IOException e4) {
                            MapLogger.e(e4);
                        }
                    }
                    return null;
                }
            } catch (IOException e5) {
                e = e5;
                inputStreamOpenRawResource = null;
            } catch (Throwable th) {
                th = th;
                if (r0 != 0) {
                    try {
                        r0.close();
                    } catch (IOException e6) {
                        MapLogger.e(e6);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0 = context;
        }
    }
}

package com.kakao.vectormap.internal;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.kakao.vectormap.BuildConfig;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.MapLogger;

/* JADX INFO: loaded from: classes4.dex */
public class EngineHandler implements IEngineHandler {
    protected EngineCreateCallback engineCreateCallback;
    protected final boolean isVulkan;
    private MapViewHolder mapViewHolder;
    private RenderCallback renderCallback;
    protected long appEngineHandle = 0;
    private boolean isResumed = false;
    private boolean isPaused = false;

    static native long create(String str, String str2, String str3, AssetManager assetManager, float f, double d, double d2, int i, int i2, int i3, String str4, int i4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, Surface surface, int i5, String str12, String str13, RenderViewOptions[] renderViewOptionsArr);

    static native String getEngineState(long j);

    static native void nativeInit();

    static native boolean pause(long j);

    static native boolean render(long j);

    static native boolean resume(long j, int i, int i2);

    static native void setAllViewport(long j, int i, int i2);

    static native void setViewport(long j, String str, int i, int i2, int i3, int i4, int i5, int i6);

    static native void stop(long j);

    static native void updateVulkanSurface(long j, int i, int i2, Surface surface);

    static {
        nativeInit();
    }

    public EngineHandler(MapViewHolder mapViewHolder, EngineCreateCallback engineCreateCallback, RenderCallback renderCallback, boolean z) {
        this.mapViewHolder = mapViewHolder;
        this.engineCreateCallback = engineCreateCallback;
        this.renderCallback = renderCallback;
        this.isVulkan = z;
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public long start(int i, String str) throws RuntimeException {
        SurfaceView surfaceView = this.renderCallback.getSurfaceView();
        Surface surface = surfaceView.getHolder().getSurface();
        Context context = surfaceView.getContext();
        int width = this.renderCallback.getSurfaceView().getWidth();
        int height = this.renderCallback.getSurfaceView().getHeight();
        this.mapViewHolder.setEngineCreate();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        String strValueOf = String.valueOf(Build.VERSION.SDK_INT);
        String appVersion = getAppVersion(context);
        double d = displayMetrics.ydpi;
        String str2 = Build.MODEL;
        String packageName = context.getPackageName();
        DeviceType deviceType = DeviceType.Phone;
        if (displayMetrics.xdpi == 0.0f) {
            int i2 = displayMetrics.densityDpi;
        } else {
            float f = displayMetrics.xdpi;
        }
        if (displayMetrics.ydpi == 0.0f) {
            int i3 = displayMetrics.densityDpi;
        } else {
            float f2 = displayMetrics.ydpi;
        }
        double d2 = (((double) ((displayMetrics.densityDpi + displayMetrics.xdpi) + displayMetrics.ydpi)) / 3.0d) / 160.0d;
        if (context.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
            deviceType = DeviceType.Watch;
        }
        RenderViewOptions[] renderViewOptions = this.mapViewHolder.getRenderViewOptions();
        long jCreate = create(context.getApplicationInfo().dataDir, context.getCacheDir().getAbsolutePath(), "assets", context.getAssets(), this.mapViewHolder.getMapDpScale(), d2, d, width, height, i, str2, deviceType.getValue(), appVersion, packageName, BuildConfig.SDK_VERSION, strValueOf, str, this.isVulkan, BuildConfig.SDK_HASH, BuildConfig.ENGINE_HASH, surface, renderViewOptions.length, BuildConfig.FLAVOR, KakaoMapSdk.INSTANCE.getAppKey(), renderViewOptions);
        this.appEngineHandle = jCreate;
        EngineCreateCallback engineCreateCallback = this.engineCreateCallback;
        if (engineCreateCallback != null) {
            engineCreateCallback.onEngineCreated(jCreate);
        }
        this.mapViewHolder.callEngineResumed(this.renderCallback);
        return this.appEngineHandle;
    }

    private String getAppVersion(Context context) {
        if (context == null) {
            return "0.0.0";
        }
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            MapLogger.e(e);
            return "0.0.0";
        }
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void resume() {
        if (this.appEngineHandle == 0) {
            return;
        }
        SurfaceView surfaceView = this.renderCallback.getSurfaceView();
        boolean zResume = resume(this.appEngineHandle, surfaceView.getWidth(), surfaceView.getHeight());
        this.isResumed = zResume;
        if (zResume) {
            this.isPaused = false;
            this.mapViewHolder.callEngineResumed(this.renderCallback);
        }
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public boolean isResumed() {
        return this.isResumed;
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public boolean render() {
        long j = this.appEngineHandle;
        if (j == 0) {
            MapLogger.w("EngineHandler render return. appEngineHandle = 0");
            return false;
        }
        return render(j);
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void pause() {
        long j = this.appEngineHandle;
        if (j == 0) {
            MapLogger.w("EngineHandler pause return. appEngineHandle = 0");
            return;
        }
        boolean zPause = pause(j);
        this.isPaused = zPause;
        if (zPause) {
            this.isResumed = false;
            this.mapViewHolder.callEnginePaused(this.renderCallback);
        }
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public boolean isPaused() {
        return this.isPaused;
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void updateSurface() {
        SurfaceView surfaceView = this.renderCallback.getSurfaceView();
        updateVulkanSurface(this.appEngineHandle, surfaceView.getWidth(), surfaceView.getHeight(), surfaceView.getHolder().getSurface());
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void resize(int i, int i2) {
        long j = this.appEngineHandle;
        if (j == 0) {
            MapLogger.w("EngineHandler resize return. appEngineHandle = 0");
        } else {
            setAllViewport(j, i, i2);
        }
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void stop() {
        if (this.appEngineHandle == 0) {
            MapLogger.w("EngineHandler stop return. appEngineHandle = 0");
            return;
        }
        this.mapViewHolder.callEngineStopped();
        stop(this.appEngineHandle);
        this.appEngineHandle = 0L;
        this.engineCreateCallback = null;
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public String getEngineState() {
        return getEngineState(this.appEngineHandle);
    }

    @Override // com.kakao.vectormap.internal.IEngineHandler
    public void removeVsyncCallbackOnUiThread() {
        RenderCallback renderCallback = this.renderCallback;
        if (renderCallback != null) {
            renderCallback.removeCallback();
        }
    }
}

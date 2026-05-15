package com.kakao.vectormap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import com.kakao.vectormap.MapAuthenticator;
import com.kakao.vectormap.graphics.IMapSurfaceView;
import com.kakao.vectormap.graphics.gl.KGLSurfaceView;
import com.kakao.vectormap.graphics.vk.KVKSurfaceView;

/* JADX INFO: loaded from: classes4.dex */
public final class MapView extends FrameLayout implements IRenderView {
    private float mapDpScale;
    private IMapSurfaceView surfaceView;
    private boolean useVulkan;

    static native void setLogSettings(boolean z, int i, int i2, int i3, int i4, int i5, int i6);

    static native boolean supportVulkan();

    public MapView(Context context) {
        super(context);
        this.useVulkan = false;
        this.mapDpScale = -1.0f;
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.useVulkan = false;
        this.mapDpScale = -1.0f;
    }

    public synchronized void start(final MapLifeCycleCallback mapLifeCycleCallback, MapReadyCallback... mapReadyCallbackArr) {
        if (!KakaoMapSdk.isInitialized()) {
            MapLogger.e("MapView Start failed. KakaoMapSdk.init() must be called first.");
            return;
        }
        if (checkCallbacks(mapLifeCycleCallback, mapReadyCallbackArr)) {
            if (checkKakaoMapSdk(mapLifeCycleCallback)) {
                setLogSettings(MapLogger.ENABLE, MapLogger.COMMON_LOG, MapLogger.LABEL_LOG, MapLogger.ROUTE_LOG, MapLogger.SHAPE_LOG, MapLogger.MAP_WIDGET_LOG, MapLogger.CAMERA_LOG);
                finish();
                addSurfaceView(mapLifeCycleCallback, mapReadyCallbackArr);
                MapAuthenticator.request(getContext(), new MapAuthenticator.OnResponseListener() { // from class: com.kakao.vectormap.MapView.1
                    @Override // com.kakao.vectormap.MapAuthenticator.OnResponseListener
                    public void onMapAuthSucceed() {
                    }

                    @Override // com.kakao.vectormap.MapAuthenticator.OnResponseListener
                    public void onMapAuthFailure(MapAuthException mapAuthException) {
                        MapView.this.goMapError(mapLifeCycleCallback, mapAuthException);
                    }
                });
            }
        }
    }

    public synchronized void finish() {
        IMapSurfaceView iMapSurfaceView = this.surfaceView;
        if (iMapSurfaceView == null) {
            return;
        }
        iMapSurfaceView.finish();
        try {
            if (getChildAt(0) != null && (getChildAt(0) instanceof IMapSurfaceView)) {
                removeView(getChildAt(0));
            }
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void resume() {
        try {
            this.surfaceView.resume();
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isResumed() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return false;
        }
        return this.surfaceView.isResumed();
    }

    public synchronized void pause() {
        try {
            this.surfaceView.pause();
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isPaused() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return false;
        }
        return this.surfaceView.isPaused();
    }

    public synchronized void setFinishManually(boolean z) {
        IMapSurfaceView iMapSurfaceView = this.surfaceView;
        if (iMapSurfaceView == null || iMapSurfaceView.getMapRenderer() == null) {
            MapLogger.e("setFinishManually failed. MapView is not started.");
            return;
        }
        try {
            this.surfaceView.setFinishManually(z);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isFinishManually() {
        IMapSurfaceView iMapSurfaceView;
        iMapSurfaceView = this.surfaceView;
        return (iMapSurfaceView == null || iMapSurfaceView.getMapRenderer() == null) ? false : this.surfaceView.isFinishManually();
    }

    public synchronized void reverseMouseWheelScroll(boolean z) {
        IMapSurfaceView iMapSurfaceView = this.surfaceView;
        if (iMapSurfaceView == null) {
            MapLogger.w("reverseMouseWheelScroll return.");
        } else {
            iMapSurfaceView.reverseMouseWheelScroll(z);
        }
    }

    public synchronized void setMapDpScale(float f) {
        this.mapDpScale = f;
    }

    public float getMapDpScale() {
        return this.mapDpScale;
    }

    public synchronized boolean isVulkan() {
        return this.useVulkan;
    }

    @Override // com.kakao.vectormap.IRenderView
    public synchronized SurfaceView getSurfaceView() {
        IMapSurfaceView iMapSurfaceView = this.surfaceView;
        if (iMapSurfaceView == null) {
            return null;
        }
        return iMapSurfaceView.getView();
    }

    public synchronized String getEngineState() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return "Map Engine is invalid.";
        }
        return this.surfaceView.getEngineState();
    }

    public static String getApiVersion() {
        return "2.12.18(9be3754e)";
    }

    public static String getEngineVersion() {
        return "Android_deploy/2025/0910_1253(3e529ef3a)";
    }

    private void addSurfaceView(MapLifeCycleCallback mapLifeCycleCallback, MapReadyCallback... mapReadyCallbackArr) {
        try {
            boolean zSupportVulkan = supportVulkan();
            this.useVulkan = zSupportVulkan;
            if (zSupportVulkan) {
                KVKSurfaceView kVKSurfaceView = new KVKSurfaceView(getContext(), mapLifeCycleCallback, this, mapReadyCallbackArr);
                this.surfaceView = kVKSurfaceView;
                addView(kVKSurfaceView.getView(), 0, new FrameLayout.LayoutParams(-1, -1));
                ((KVKSurfaceView) this.surfaceView).initEngine(true);
                return;
            }
            KGLSurfaceView kGLSurfaceView = new KGLSurfaceView(getContext(), mapLifeCycleCallback, this, mapReadyCallbackArr);
            this.surfaceView = kGLSurfaceView;
            addView(kGLSurfaceView.getView(), 0, new FrameLayout.LayoutParams(-1, -1));
            ((KGLSurfaceView) this.surfaceView).initEngine(true);
        } catch (Exception e) {
            goMapError(mapLifeCycleCallback, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMapError(MapLifeCycleCallback mapLifeCycleCallback, Exception exc) {
        MapLogger.e(exc);
        if (mapLifeCycleCallback != null) {
            mapLifeCycleCallback.onMapError(exc);
        }
        try {
            if (getChildAt(0) == null || !(getChildAt(0) instanceof IMapSurfaceView)) {
                return;
            }
            removeView(getChildAt(0));
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    private boolean checkKakaoMapSdk(MapLifeCycleCallback mapLifeCycleCallback) {
        if (KakaoMapSdk.INSTANCE != null) {
            return true;
        }
        goMapError(mapLifeCycleCallback, new RuntimeException("KakaoMapSdk is not initialized."));
        return false;
    }

    private boolean checkCallbacks(MapLifeCycleCallback mapLifeCycleCallback, MapReadyCallback... mapReadyCallbackArr) {
        if (mapReadyCallbackArr == null || mapReadyCallbackArr.length == 0) {
            goMapError(mapLifeCycleCallback, new RuntimeException("MapView Start failed. MapReadyCallbacks is null or empty."));
            return false;
        }
        for (MapReadyCallback mapReadyCallback : mapReadyCallbackArr) {
            if (!(mapReadyCallback instanceof KakaoMapReadyCallback) && !(mapReadyCallback instanceof RoadViewReadyCallback)) {
                goMapError(mapLifeCycleCallback, new RuntimeException("MapView Start failed. The callback must be either KakaoMapReadyCallback or RoadViewReadyCallback."));
                return false;
            }
        }
        return true;
    }
}

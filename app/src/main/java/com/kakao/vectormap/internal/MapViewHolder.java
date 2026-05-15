package com.kakao.vectormap.internal;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.MapInjector;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.MapReadyCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.RenderViewException;
import com.kakao.vectormap.RoadView;
import com.kakao.vectormap.RoadViewInfo;
import com.kakao.vectormap.RoadViewReadyCallback;
import com.kakao.vectormap.RoadViewRequest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MapViewHolder {
    public static final int STATUS_AUTH_BEFORE = 4;
    public static final int STATUS_AUTH_FAILURE = 8;
    public static final int STATUS_AUTH_FAILURE_CALLED = 32;
    public static final int STATUS_AUTH_SUCCEED = 16;
    public static final int STATUS_ENGINE_CREATED = 1;
    public static final int STATUS_RENDER_VIEW_CREATED = 2;
    private MapLifeCycleCallback lifeCycleCallback;
    private MapView mapView;
    private int startStatus = 0;
    private Map<String, RenderViewHolder> renderViewMap = new LinkedHashMap();

    public MapViewHolder(MapLifeCycleCallback mapLifeCycleCallback, MapView mapView) {
        this.mapView = mapView;
        this.lifeCycleCallback = mapLifeCycleCallback;
        this.startStatus |= 4;
    }

    public void setEngineCreate() {
        synchronized (this) {
            this.startStatus |= 1;
        }
    }

    public boolean isEngineCreated() {
        boolean z;
        synchronized (this) {
            z = true;
            if ((this.startStatus & 1) != 1) {
                z = false;
            }
        }
        return z;
    }

    public void resetEngineCreate() {
        synchronized (this) {
            this.startStatus &= -2;
        }
    }

    public float getMapDpScale() {
        return this.mapView.getMapDpScale();
    }

    public synchronized void addRenderView(MapReadyCallback mapReadyCallback) throws RuntimeException {
        if (this.renderViewMap.containsKey(mapReadyCallback.getViewName())) {
            throw new RuntimeException("addRenderView failure. viewName is existed.");
        }
        this.renderViewMap.put(mapReadyCallback.getViewName(), new RenderViewHolder(mapReadyCallback.getViewName(), mapReadyCallback));
    }

    public synchronized RenderViewOptions[] getRenderViewOptions() {
        RenderViewOptions[] renderViewOptionsArr;
        renderViewOptionsArr = new RenderViewOptions[this.renderViewMap.values().size()];
        Iterator<RenderViewHolder> it = this.renderViewMap.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            renderViewOptionsArr[i] = it.next().makeOptions(this);
            i++;
        }
        return renderViewOptionsArr;
    }

    public void onRenderViewResult(long j, String str, boolean z, int i, int i2, int i3, int i4) {
        RenderViewHolder renderViewHolder = this.renderViewMap.get(str);
        if (!z) {
            callOnMapError(new RenderViewException("RenderView Create Failure(" + str + ")."));
            return;
        }
        final MapReadyCallback readyCallback = renderViewHolder.getReadyCallback();
        if (readyCallback instanceof KakaoMapReadyCallback) {
            MapViewInfo mapViewInfo = ((KakaoMapReadyCallback) readyCallback).getMapViewInfo();
            KakaoMapDelegate kakaoMapDelegate = new KakaoMapDelegate(j, readyCallback.getTag(), mapViewInfo.getAppName(), str, mapViewInfo, this.mapView, new Rect(i, i2, i3, i4), readyCallback.isDev(), readyCallback.isVisible());
            final KakaoMap kakaoMapNewKakaoMap = MapInjector.newKakaoMap(kakaoMapDelegate);
            MapCameraController.zoomTo(j, str, readyCallback.getZoomLevel(), false, false, 0);
            EventListener eventListener = new EventListener(kakaoMapNewKakaoMap, kakaoMapDelegate);
            kakaoMapDelegate.setEventListener(eventListener);
            RenderViewController.setEventListener(j, str, eventListener);
            RenderViewController.setViewInfoChangeListener(j, str, true);
            RenderViewController.setMapResizeListener(j, str, true);
            RenderViewController.setPaddingResizeListener(j, str, true);
            renderViewHolder.setRenderViewDelegate(kakaoMapDelegate);
            kakaoMapDelegate.setEngineStatus(true);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ((KakaoMapReadyCallback) readyCallback).onMapReady(kakaoMapNewKakaoMap);
                    } catch (Exception e) {
                        MapLogger.e(e);
                    }
                }
            });
            return;
        }
        RoadViewInfo viewInfo = ((RoadViewReadyCallback) readyCallback).getViewInfo();
        final RoadViewDelegate roadViewDelegate = new RoadViewDelegate(j, readyCallback.getTag(), viewInfo.getAppName(), str, viewInfo.getViewInfoName(), this.mapView, new Rect(i, i2, i3, i4), readyCallback.isDev(), readyCallback.isVisible());
        final RoadView roadViewNewRoadView = MapInjector.newRoadView(roadViewDelegate);
        RoadViewEventListener roadViewEventListener = new RoadViewEventListener(roadViewNewRoadView, roadViewDelegate);
        roadViewDelegate.setEventListener(roadViewEventListener);
        RoadViewController.setEventListener(j, str, roadViewEventListener);
        RoadViewController.setViewportResizeListener(j, str, true);
        renderViewHolder.setRenderViewDelegate(roadViewDelegate);
        roadViewDelegate.setEngineStatus(true);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((RoadViewReadyCallback) readyCallback).onRoadViewReady(roadViewNewRoadView);
                    RoadViewRequest roadViewRequest = ((RoadViewReadyCallback) readyCallback).getRoadViewRequest();
                    if (roadViewRequest != null) {
                        ((RoadViewDelegate) roadViewDelegate).requestRoadView(roadViewRequest);
                    }
                } catch (Exception e) {
                    MapLogger.e(e);
                }
            }
        });
    }

    public void callEngineResumed(final RenderCallback renderCallback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    renderCallback.postCallback();
                    if (MapViewHolder.this.lifeCycleCallback != null) {
                        MapViewHolder.this.lifeCycleCallback.onMapResumed();
                    }
                } catch (Exception e) {
                    MapLogger.e(e);
                }
            }
        });
    }

    public void callEnginePaused(final RenderCallback renderCallback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    renderCallback.removeCallback();
                    if (MapViewHolder.this.lifeCycleCallback != null) {
                        MapViewHolder.this.lifeCycleCallback.onMapPaused();
                    }
                } catch (Exception e) {
                    MapLogger.e(e);
                }
            }
        });
    }

    public void callOnMapError(final Exception exc) {
        if (this.lifeCycleCallback == null) {
            MapLogger.e(exc);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MapViewHolder.this.lifeCycleCallback.onMapError(exc);
                    } catch (Exception e) {
                        MapLogger.e(e);
                    }
                }
            });
        }
    }

    public void callEngineStopped() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.internal.MapViewHolder.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (MapViewHolder.this.lifeCycleCallback == null) {
                        return;
                    }
                    MapViewHolder.this.lifeCycleCallback.onMapDestroy();
                } catch (Exception e) {
                    MapLogger.e(e);
                }
            }
        });
        Iterator<RenderViewHolder> it = this.renderViewMap.values().iterator();
        while (it.hasNext()) {
            RenderViewDelegate renderViewDelegate = it.next().getRenderViewDelegate();
            if (renderViewDelegate != null) {
                renderViewDelegate.setEngineStatus(false);
            }
        }
        this.renderViewMap.clear();
    }

    public void destroy() {
        for (RenderViewHolder renderViewHolder : this.renderViewMap.values()) {
            if (renderViewHolder != null) {
                renderViewHolder.destroy();
            }
        }
        this.renderViewMap.clear();
        this.mapView = null;
    }
}

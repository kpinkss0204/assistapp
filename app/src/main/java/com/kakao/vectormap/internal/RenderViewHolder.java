package com.kakao.vectormap.internal;

import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapReadyCallback;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.RoadViewInfo;
import com.kakao.vectormap.RoadViewReadyCallback;

/* JADX INFO: loaded from: classes4.dex */
class RenderViewHolder {
    private MapLifeCycleCallback lifeCycleCallback;
    private MapReadyCallback readyCallback;
    private RenderViewDelegate renderViewDelegate;
    private String viewName;

    RenderViewHolder(String str, MapReadyCallback mapReadyCallback) {
        this.viewName = str;
        this.readyCallback = mapReadyCallback;
    }

    RenderViewOptions makeOptions(MapViewHolder mapViewHolder) {
        RenderViewOptions renderViewOptions = new RenderViewOptions();
        MapReadyCallback mapReadyCallback = this.readyCallback;
        if (mapReadyCallback instanceof KakaoMapReadyCallback) {
            MapViewInfo mapViewInfo = ((KakaoMapReadyCallback) mapReadyCallback).getMapViewInfo();
            renderViewOptions.appName = mapViewInfo.getAppName();
            renderViewOptions.viewInfo = mapViewInfo.getMapType();
            renderViewOptions.initStyle = mapViewInfo.getMapStyle();
        } else if (mapReadyCallback instanceof RoadViewReadyCallback) {
            RoadViewInfo viewInfo = ((RoadViewReadyCallback) mapReadyCallback).getViewInfo();
            renderViewOptions.appName = viewInfo.getAppName();
            renderViewOptions.viewInfo = viewInfo.getViewInfoName();
            renderViewOptions.initStyle = viewInfo.getStyle();
        } else {
            throw new RuntimeException("readyCallback type is wrong.");
        }
        renderViewOptions.isDev = false;
        LatLng position = this.readyCallback.getPosition();
        renderViewOptions.lat = position.getLatitude();
        renderViewOptions.lng = position.getLongitude();
        renderViewOptions.level = this.readyCallback.getZoomLevel();
        renderViewOptions.viewName = this.readyCallback.getViewName();
        renderViewOptions.visible = this.readyCallback.isVisible();
        MapReadyCallback mapReadyCallback2 = this.readyCallback;
        renderViewOptions.timeout = mapReadyCallback2 instanceof KakaoMapReadyCallback ? mapReadyCallback2.getTimeout() : 5000;
        renderViewOptions.viewType = !(this.readyCallback instanceof KakaoMapReadyCallback) ? 1 : 0;
        renderViewOptions.listener = mapViewHolder;
        return renderViewOptions;
    }

    public void setRenderViewDelegate(RenderViewDelegate renderViewDelegate) {
        this.renderViewDelegate = renderViewDelegate;
    }

    public MapReadyCallback getReadyCallback() {
        return this.readyCallback;
    }

    public MapLifeCycleCallback getLifeCycleCallback() {
        return this.lifeCycleCallback;
    }

    public RenderViewDelegate getRenderViewDelegate() {
        return this.renderViewDelegate;
    }

    void destroy() {
        this.viewName = null;
        this.readyCallback = null;
        this.lifeCycleCallback = null;
        this.renderViewDelegate = null;
    }
}

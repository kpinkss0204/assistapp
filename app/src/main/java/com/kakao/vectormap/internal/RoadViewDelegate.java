package com.kakao.vectormap.internal;

import android.graphics.Rect;
import androidx.camera.video.AudioStats;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.RoadView;
import com.kakao.vectormap.RoadViewRequest;

/* JADX INFO: loaded from: classes4.dex */
public class RoadViewDelegate extends RenderViewDelegate implements IRoadViewDelegate {
    private RoadViewEventListener eventListener;
    private MapResourceManager resourceManager;

    RoadViewDelegate(long j, Object obj, String str, String str2, String str3, MapView mapView, Rect rect, boolean z, boolean z2) {
        super(j, obj, str, str2, mapView, rect, z, z2);
        this.resourceManager = new MapResourceManager(j, str2, mapView.getContext());
    }

    void setEventListener(RoadViewEventListener roadViewEventListener) {
        this.eventListener = roadViewEventListener;
        roadViewEventListener.setRunning(isRunning());
        this.destroyables.add(roadViewEventListener);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void setOnRoadViewRequestListener(RoadView.OnRoadViewRequestListener onRoadViewRequestListener) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setOnRoadViewEventListener(onRoadViewRequestListener);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void setOnRoadViewResizeListener(RoadView.OnRoadViewResizeListener onRoadViewResizeListener) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setOnRoadViewResizeListener(onRoadViewResizeListener);
        RoadViewController.setViewportResizeListener(this.appEngineHandle, this.viewName, onRoadViewResizeListener != null);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void setOnRoadViewClickListener(RoadView.OnRoadViewClickListener onRoadViewClickListener) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setOnRoadViewClickListener(onRoadViewClickListener);
        RoadViewController.setRenderViewClickListener(this.appEngineHandle, this.viewName, onRoadViewClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void setOnRoadViewUpdateListener(RoadView.OnRoadViewUpdateListener onRoadViewUpdateListener) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setOnRoadViewUpdateListener(onRoadViewUpdateListener);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void requestRoadView(RoadViewRequest roadViewRequest) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (roadViewRequest.panoramaCoord == null && (roadViewRequest.panoramaId == null || roadViewRequest.panoramaId.isEmpty())) {
            throw new RuntimeException("requestRoadView failure. PanoramaCoord and PanoramaId is invalid.");
        }
        LatLng latLngFrom = roadViewRequest.panoramaCoord == null ? LatLng.from(AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE) : roadViewRequest.panoramaCoord;
        LatLng latLngFrom2 = roadViewRequest.lookAtPosition == null ? LatLng.from(AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE) : roadViewRequest.lookAtPosition;
        if (roadViewRequest.markers == null || roadViewRequest.markers.isEmpty()) {
            RoadViewController.requestRoadView(this.appEngineHandle, this.viewName, roadViewRequest.panoramaId, latLngFrom.latitude, latLngFrom.longitude, 0, roadViewRequest.lookAtType, latLngFrom2.latitude, latLngFrom2.longitude, roadViewRequest.lookAtPan, roadViewRequest.lookAtTilt, roadViewRequest.defaultSearchRange, roadViewRequest.extendedSearchRange, null, null);
            return;
        }
        int size = roadViewRequest.markers.size();
        byte[][] bArr = new byte[size][];
        for (int i = 0; i < size; i++) {
            RoadViewRequest.Marker marker = roadViewRequest.markers.get(i);
            marker.assetId = this.resourceManager.getAssetId(marker.getResourceId(), marker.getBitmap());
            bArr[i] = this.resourceManager.getResourceBytes(marker.getResourceId(), marker.getBitmap());
        }
        RoadViewController.requestRoadView(this.appEngineHandle, this.viewName, roadViewRequest.panoramaId, latLngFrom.latitude, latLngFrom.longitude, 0, roadViewRequest.lookAtType, latLngFrom2.latitude, latLngFrom2.longitude, roadViewRequest.lookAtPan, roadViewRequest.lookAtTilt, roadViewRequest.defaultSearchRange, roadViewRequest.extendedSearchRange, (RoadViewRequest.Marker[]) roadViewRequest.markers.toArray(new RoadViewRequest.Marker[roadViewRequest.markers.size()]), bArr);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void requestNextRoadView(RoadViewRequest roadViewRequest) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LatLng latLngFrom = roadViewRequest.panoramaCoord == null ? LatLng.from(AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE) : roadViewRequest.panoramaCoord;
        RoadViewController.requestNextRoadView(this.appEngineHandle, this.viewName, roadViewRequest.panoramaId, latLngFrom.latitude, latLngFrom.longitude, 0, roadViewRequest.defaultSearchRange, roadViewRequest.extendedSearchRange);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void moveToRoadView(int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RoadViewController.moveToRoadView(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void linkMap(KakaoMap kakaoMap) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RoadViewController.linkMapToRoadView(this.appEngineHandle, this.viewName, kakaoMap.getViewName());
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void unlinkMap() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RoadViewController.unlinkMapFromRoadView(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public double getTiltAngle() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RoadViewController.getRoadViewTiltAngle(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public double getPanAngle() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RoadViewController.getRoadViewPanAngle(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IRoadViewDelegate
    public void setSearchRange(int i, int i2) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RoadViewController.setSearchRange(this.appEngineHandle, this.viewName, i, i2);
    }

    @Override // com.kakao.vectormap.internal.RenderViewDelegate, com.kakao.vectormap.internal.IRenderViewDelegate
    public void setLogoPosition(int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RoadViewController.setLogoPosition(this.appEngineHandle, this.viewName, i, f, f2);
    }
}

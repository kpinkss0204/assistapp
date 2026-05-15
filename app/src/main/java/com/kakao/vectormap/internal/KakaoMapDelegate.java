package com.kakao.vectormap.internal;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.kakao.vectormap.Compass;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.Coordinate;
import com.kakao.vectormap.GestureType;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.LatLngBounds;
import com.kakao.vectormap.MapCoordType;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.Padding;
import com.kakao.vectormap.PoiCategory;
import com.kakao.vectormap.PoiScale;
import com.kakao.vectormap.ScaleBar;
import com.kakao.vectormap.camera.CameraPosition;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelManager;
import com.kakao.vectormap.label.TrackingManager;
import com.kakao.vectormap.mapwidget.MapWidgetManager;
import com.kakao.vectormap.route.RouteLineManager;
import com.kakao.vectormap.shape.DimScreenManager;
import com.kakao.vectormap.shape.ShapeManager;
import com.kakao.vectormap.utils.MapUtils;
import com.kakao.vectormap.zone.ZoneManager;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public class KakaoMapDelegate extends RenderViewDelegate implements IKakaoMapDelegate, ITrackingDelegate {
    private Compass compass;
    private DimScreenManager dimScreenManager;
    private EventListener eventListener;
    private LabelManager labelManager;
    private MapWidgetManager mapWidgetManager;
    private Padding padding;
    private MapResourceManager resourceManager;
    private RouteLineManager routeLineManager;
    private ScaleBar scaleBar;
    private ShapeManager shapeManager;
    private TrackingManager trackingManager;
    protected MapViewInfo viewInfo;
    private ZoneManager zoneManager;

    KakaoMapDelegate(long j, Object obj, String str, String str2, MapViewInfo mapViewInfo, MapView mapView, Rect rect, boolean z, boolean z2) {
        super(j, obj, str, str2, mapView, rect, z, z2);
        this.resourceManager = new MapResourceManager(j, str2, mapView.getContext());
        this.viewInfo = mapViewInfo;
        this.padding = new Padding();
    }

    void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
        eventListener.setRunning(isRunning());
        this.destroyables.add(this.eventListener);
    }

    synchronized void setMapViewInfo(String str, String str2, String str3) {
        this.viewInfo = MapViewInfo.from(str, str2).setMapStyle(str3);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getZoomLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return MapCameraController.getZoomLevel(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPadding(int i, int i2, int i3, int i4) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int iMax = Math.max(i, 0);
        int iMax2 = Math.max(i2, 0);
        int iMax3 = Math.max(i3, 0);
        int iMax4 = Math.max(i4, 0);
        if (iMax + iMax3 > this.viewport.width() || iMax2 + iMax4 > this.viewport.height()) {
            MapLogger.e("setPadding Failed. Padding cannot be longer than the width or height of Viewport.");
        } else {
            MapCameraController.setVirtualViewport(this.appEngineHandle, this.viewName, iMax, iMax2, iMax3, iMax4);
        }
    }

    protected synchronized Padding setPaddingInternal(int i, int i2, int i3, int i4) {
        this.padding.left = i;
        this.padding.top = i2;
        this.padding.right = i3;
        this.padding.bottom = i4;
        return this.padding;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public synchronized Padding getPadding() throws RuntimeException {
        return new Padding(this.padding);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public LatLng getMapPoint(int i, int i2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.viewport.left < this.viewport.right && this.viewport.top < this.viewport.bottom && i >= this.viewport.left && i <= this.viewport.right && i2 >= this.viewport.top && i2 <= this.viewport.bottom) {
            return MapCameraController.getMapPoint(this.appEngineHandle, this.viewName, i, i2);
        }
        MapLogger.e("getMapPoint failure. (x,y) is not inside viewport.");
        return null;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public Point getScreenPoint(LatLng latLng) throws RuntimeException {
        int[] screenPoint;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (latLng == null || (screenPoint = MapCameraController.getScreenPoint(this.appEngineHandle, this.viewName, latLng.getLatitude(), latLng.getLongitude())) == null || screenPoint.length != 2) {
            return null;
        }
        return new Point(screenPoint[0], screenPoint[1]);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void moveCamera(CameraUpdate cameraUpdate) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        switch (cameraUpdate.getType()) {
            case 0:
                LatLng position = cameraUpdate.getPosition();
                MapCameraController.newCenterPoint(this.appEngineHandle, this.viewName, position.latitude, position.longitude, cameraUpdate.getZoomLevel(), false, false, false, 0);
                return;
            case 1:
                LatLng position2 = cameraUpdate.getPosition();
                MapCameraController.newCameraPosition(this.appEngineHandle, this.viewName, position2.latitude, position2.longitude, cameraUpdate.getZoomLevel(), cameraUpdate.getRotationAngle(), cameraUpdate.getTiltAngle(), cameraUpdate.getHeight(), false, false, false, 0);
                return;
            case 2:
            default:
                return;
            case 3:
                MapCameraController.zoomTo(this.appEngineHandle, this.viewName, cameraUpdate.getZoomLevel(), false, false, 0);
                return;
            case 4:
                MapCameraController.zoomIn(this.appEngineHandle, this.viewName, false, false, 0);
                return;
            case 5:
                MapCameraController.zoomOut(this.appEngineHandle, this.viewName, false, false, 0);
                return;
            case 6:
                MapCameraController.rotateTo(this.appEngineHandle, this.viewName, cameraUpdate.getRotationAngle(), false, false, 0);
                return;
            case 7:
                MapCameraController.tiltTo(this.appEngineHandle, this.viewName, cameraUpdate.getTiltAngle(), false, false, 0);
                return;
            case 8:
                doFitMapPoints(cameraUpdate.getFitPoints(), cameraUpdate.getPadding(), cameraUpdate.getZoomLevel(), false, 0, false, false);
                return;
        }
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public CameraPosition getCameraPosition() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return MapCameraController.getCameraPosition(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void requestCameraPosition(KakaoMap.OnCameraPositionListener onCameraPositionListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (onCameraPositionListener == null) {
            return;
        }
        String uniqueId = MapUtils.getUniqueId(onCameraPositionListener.hashCode());
        this.eventListener.addWaitingListener(uniqueId, onCameraPositionListener);
        MapCameraController.requestCameraPosition(this.appEngineHandle, this.viewName, uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void animateCamera(CameraUpdate cameraUpdate, int i, boolean z, boolean z2) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        switch (cameraUpdate.getType()) {
            case 0:
                LatLng position = cameraUpdate.getPosition();
                MapCameraController.newCenterPoint(this.appEngineHandle, this.viewName, position.latitude, position.longitude, cameraUpdate.getZoomLevel(), true, z, z2, i);
                return;
            case 1:
                LatLng position2 = cameraUpdate.getPosition();
                MapCameraController.newCameraPosition(this.appEngineHandle, this.viewName, position2.latitude, position2.longitude, cameraUpdate.getZoomLevel(), cameraUpdate.getRotationAngle(), cameraUpdate.getTiltAngle(), cameraUpdate.getHeight(), true, z, z2, i);
                return;
            case 2:
            default:
                return;
            case 3:
                MapCameraController.zoomTo(this.appEngineHandle, this.viewName, cameraUpdate.getZoomLevel(), true, z2, i);
                return;
            case 4:
                MapCameraController.zoomIn(this.appEngineHandle, this.viewName, true, z2, i);
                return;
            case 5:
                MapCameraController.zoomOut(this.appEngineHandle, this.viewName, true, z2, i);
                return;
            case 6:
                MapCameraController.rotateTo(this.appEngineHandle, this.viewName, cameraUpdate.getRotationAngle(), true, z2, i);
                return;
            case 7:
                MapCameraController.tiltTo(this.appEngineHandle, this.viewName, cameraUpdate.getTiltAngle(), true, z2, i);
                return;
            case 8:
                doFitMapPoints(cameraUpdate.getFitPoints(), cameraUpdate.getPadding(), cameraUpdate.getZoomLevel(), true, i, z, z2);
                return;
        }
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setBuildingHeightScale(float f) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setMapHeightScale(this.appEngineHandle, this.viewName, f);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public float getBuildingHeightScale() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getMapHeightScale(this.appEngineHandle, this.viewName);
    }

    private void doFitMapPoints(LatLng[] latLngArr, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : latLngArr) {
            builder.include(latLng);
        }
        LatLngBounds latLngBoundsBuild = builder.build();
        LatLng southwest = latLngBoundsBuild.getSouthwest();
        LatLng northeast = latLngBoundsBuild.getNortheast();
        MapCameraController.fitMapPoints(this.appEngineHandle, this.viewName, southwest.getLatitude(), southwest.getLongitude(), northeast.getLatitude(), northeast.getLongitude(), i, i2, z, z2, z3, i3);
    }

    @Override // com.kakao.vectormap.internal.RenderViewDelegate, com.kakao.vectormap.internal.IRenderViewDelegate
    public void setLogoPosition(int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setLogoPosition(this.appEngineHandle, this.viewName, i, f, f2);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setCompassVisible(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setCompassVisible(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setCompassPosition(int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setCompassPosition(this.appEngineHandle, this.viewName, i, f, f2);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setCompassBackToNorth(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setCompassBackToNorth(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public Compass getCompass() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.compass == null) {
            this.compass = new Compass(this);
        }
        return this.compass;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public Compass getCompass(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.compass == null) {
            this.compass = new Compass(this, z);
        }
        RenderViewController.setCompassBackToNorth(this.appEngineHandle, this.viewName, z);
        return this.compass;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public ScaleBar getScaleBar() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.scaleBar == null) {
            this.scaleBar = new ScaleBar(this);
        }
        return this.scaleBar;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public ScaleBar getScaleBar(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.scaleBar == null) {
            this.scaleBar = new ScaleBar(this, z);
        }
        RenderViewController.setScaleBarOptions(this.appEngineHandle, this.viewName, z, this.scaleBar.getFadeInTime(), this.scaleBar.getFadeOutTime(), this.scaleBar.getRetentionTime());
        return this.scaleBar;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setScaleBarVisible(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setScaleBarVisible(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setScaleBarPosition(int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setScaleBarPosition(this.appEngineHandle, this.viewName, i, f, f2);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setScaleBarAutoHide(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setScaleBarAutoHide(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setScaleBarFadeInOutTime(int i, int i2, int i3) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setScaleBarFadeInOutOption(this.appEngineHandle, this.viewName, i, i2, i3);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public synchronized MapViewInfo getMapViewInfo() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return MapViewInfo.from(this.viewInfo.getAppName(), this.viewInfo.getMapType()).setMapStyle(this.viewInfo.getMapStyle());
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getMinZoomLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int minZoomLevel = MapCameraController.getMinZoomLevel(this.appEngineHandle, this.viewName);
        if (minZoomLevel >= 0) {
            return minZoomLevel;
        }
        throw new RuntimeException("Engine is not valid.");
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getMaxZoomLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int maxZoomLevel = MapCameraController.getMaxZoomLevel(this.appEngineHandle, this.viewName);
        if (maxZoomLevel >= 0) {
            return maxZoomLevel;
        }
        throw new RuntimeException("Engine is not valid.");
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setCameraMaxLevel(int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        MapCameraController.setCameraMaxLevel(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setCameraMinLevel(int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        MapCameraController.setCameraMinLevel(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getCameraMaxLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return MapCameraController.getCameraMaxLevel(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getCameraMinLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return MapCameraController.getCameraMinLevel(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnPoiClickListener(KakaoMap.OnPoiClickListener onPoiClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setPoiClickListener(onPoiClickListener);
        RenderViewController.setPoiClickListener(this.appEngineHandle, this.viewName, onPoiClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnViewportChangeListener(KakaoMap.OnViewportChangeListener onViewportChangeListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setMapResizeListener(onViewportChangeListener);
        RenderViewController.setMapResizeListener(this.appEngineHandle, this.viewName, onViewportChangeListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnPaddingResizeListener(KakaoMap.OnPaddingChangeListener onPaddingChangeListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setPaddingResizeListener(onPaddingChangeListener);
        RenderViewController.setPaddingResizeListener(this.appEngineHandle, this.viewName, onPaddingChangeListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnViewportClickListener(KakaoMap.OnViewportClickListener onViewportClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setViewportClickListener(onViewportClickListener);
        RenderViewController.setRenderViewClickListener(this.appEngineHandle, this.viewName, onViewportClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnMapClickListener(KakaoMap.OnMapClickListener onMapClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setMapClickListener(onMapClickListener);
        RenderViewController.setTerrainClickListener(this.appEngineHandle, this.viewName, onMapClickListener != null);
        RenderViewController.setPoiClickListener(this.appEngineHandle, this.viewName, onMapClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnTerrainClickListener(KakaoMap.OnTerrainClickListener onTerrainClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setTerrainClickListener(onTerrainClickListener);
        RenderViewController.setTerrainClickListener(this.appEngineHandle, this.viewName, onTerrainClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnVisibleChangeListener(KakaoMap.OnVisibleChangeListener onVisibleChangeListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setVisibleChangeListener(onVisibleChangeListener);
        RenderViewController.setVisibleChangeListener(this.appEngineHandle, this.viewName, onVisibleChangeListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnTerrainLongClickListener(KakaoMap.OnTerrainLongClickListener onTerrainLongClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setTerrainLongClickListener(onTerrainLongClickListener);
        RenderViewController.setTerrainClickListener(this.appEngineHandle, this.viewName, onTerrainLongClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnCameraMoveStartListener(KakaoMap.OnCameraMoveStartListener onCameraMoveStartListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setCameraMoveStartListener(onCameraMoveStartListener);
        RenderViewController.setCameraMoveStartListener(this.appEngineHandle, this.viewName, onCameraMoveStartListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnCameraMoveEndListener(KakaoMap.OnCameraMoveEndListener onCameraMoveEndListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setCameraMoveEndListener(onCameraMoveEndListener);
        RenderViewController.setCameraMoveEndListener(this.appEngineHandle, this.viewName, onCameraMoveEndListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnLabelClickListener(KakaoMap.OnLabelClickListener onLabelClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setLabelClickListener(onLabelClickListener);
        RenderViewController.setPoiClickListener(this.appEngineHandle, this.viewName, onLabelClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnLodLabelClickListener(KakaoMap.OnLodLabelClickListener onLodLabelClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setLodLabelClickListener(onLodLabelClickListener);
        RenderViewController.setPoiClickListener(this.appEngineHandle, this.viewName, onLodLabelClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnInfoWindowClickListener(KakaoMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setInfoWindowClickListener(onInfoWindowClickListener);
        RenderViewController.setGuiClickListener(this.appEngineHandle, this.viewName, onInfoWindowClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnMapWidgetClickListener(KakaoMap.OnMapWidgetClickListener onMapWidgetClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setMapWidgetClickListener(onMapWidgetClickListener);
        RenderViewController.setGuiClickListener(this.appEngineHandle, this.viewName, onMapWidgetClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnCompassClickListener(KakaoMap.OnCompassClickListener onCompassClickListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setCompassClickListener(onCompassClickListener);
        RenderViewController.setGuiClickListener(this.appEngineHandle, this.viewName, onCompassClickListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnMapViewInfoChangeListener(KakaoMap.OnMapViewInfoChangeListener onMapViewInfoChangeListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setMapViewInfoChangeListener(onMapViewInfoChangeListener);
        RenderViewController.setViewInfoChangeListener(this.appEngineHandle, this.viewName, onMapViewInfoChangeListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setOnZoneInfoEventListener(KakaoMap.OnZoneEventListener onZoneEventListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        this.eventListener.setZoneEventListener(onZoneEventListener);
        RenderViewController.setZoneEventListener(this.appEngineHandle, this.viewName, onZoneEventListener != null);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setGestureEnable(GestureType gestureType, boolean z) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setMapGestureEnable(this.appEngineHandle, this.viewName, gestureType.getValue(), z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void enableFixedCenterPoint(boolean z, GestureType... gestureTypeArr) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int[] iArr = new int[gestureTypeArr.length];
        for (int i = 0; i < gestureTypeArr.length; i++) {
            iArr[i] = gestureTypeArr[i].getValue();
        }
        if (z) {
            RenderViewController.enableFixedCenterPoint(this.appEngineHandle, this.viewName, iArr);
        } else {
            RenderViewController.disableFixedCenterPoint(this.appEngineHandle, this.viewName, iArr);
        }
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public RouteLineManager getRouteLineManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.routeLineManager == null) {
            RouteLineDelegate routeLineDelegate = new RouteLineDelegate(this.viewName, this.appEngineHandle, this.resourceManager);
            routeLineDelegate.setRunning(isRunning());
            this.destroyables.add(routeLineDelegate);
            this.routeLineManager = new RouteLineManager(routeLineDelegate);
            this.eventListener.setRouteLineDelegate(routeLineDelegate);
        }
        return this.routeLineManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public LabelManager getLabelManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.labelManager == null) {
            LabelDelegate labelDelegate = new LabelDelegate(this.viewName, this.appEngineHandle, this.resourceManager);
            labelDelegate.setRunning(isRunning());
            this.destroyables.add(labelDelegate);
            this.labelManager = new LabelManager(labelDelegate);
            this.eventListener.setLabelDelegate(labelDelegate);
        }
        return this.labelManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public ShapeManager getShapeManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.shapeManager == null) {
            ShapeDelegate shapeDelegate = new ShapeDelegate(this.appEngineHandle, this.viewName, this.resourceManager);
            shapeDelegate.setRunning(isRunning());
            this.destroyables.add(shapeDelegate);
            this.shapeManager = new ShapeManager(shapeDelegate);
            this.eventListener.setShapeDelegate(shapeDelegate);
        }
        return this.shapeManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public DimScreenManager getDimScreenManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.dimScreenManager == null) {
            DimScreenDelegate dimScreenDelegate = new DimScreenDelegate(this.appEngineHandle, this.viewName, this.resourceManager);
            dimScreenDelegate.setRunning(isRunning());
            this.destroyables.add(dimScreenDelegate);
            this.dimScreenManager = new DimScreenManager(dimScreenDelegate);
            this.eventListener.setDimScreenDelegate(dimScreenDelegate);
        }
        return this.dimScreenManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public TrackingManager getTrackingManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.trackingManager == null) {
            this.trackingManager = new TrackingManager(this);
        }
        return this.trackingManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public ZoneManager getZoneManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.zoneManager == null) {
            ZoneDelegate zoneDelegate = new ZoneDelegate(this.viewName, this.appEngineHandle);
            zoneDelegate.setRunning(isRunning());
            this.zoneManager = new ZoneManager(zoneDelegate);
        }
        return this.zoneManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public boolean canShowMapPoints(int i, LatLng... latLngArr) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (latLngArr == null || latLngArr.length <= 0) {
            Log.w(Const.TAG, "fitMapPoints Failed. LatLng points is invalid.");
            return false;
        }
        if (latLngArr.length == 1) {
            return true;
        }
        if (latLngArr.length == 2) {
            LatLng latLng = latLngArr[0];
            LatLng latLng2 = latLngArr[1];
            return MapCameraController.canShowMapPoints(this.appEngineHandle, this.viewName, latLng.getLatitude(), latLng.getLongitude(), latLng2.getLatitude(), latLng2.getLongitude(), i);
        }
        double latitude = latLngArr[0].getLatitude();
        double longitude = latLngArr[0].getLongitude();
        double dMax = longitude;
        double dMin = latitude;
        for (LatLng latLng3 : latLngArr) {
            double latitude2 = latLng3.getLatitude();
            double longitude2 = latLng3.getLongitude();
            dMin = Math.min(latitude2, dMin);
            longitude = Math.min(longitude2, longitude);
            latitude = Math.max(latitude2, latitude);
            dMax = Math.max(longitude2, dMax);
        }
        return MapCameraController.canShowMapPoints(this.appEngineHandle, this.viewName, dMin, longitude, latitude, dMax, i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public float getMapDpScale() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getMapDpScale(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public MapWidgetManager getMapWidgetManager() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.mapWidgetManager == null) {
            GuiDelegate guiDelegate = new GuiDelegate(this.appEngineHandle, this.viewName, this.resourceManager);
            guiDelegate.setRunning(isRunning());
            this.destroyables.add(guiDelegate);
            this.mapWidgetManager = new MapWidgetManager(guiDelegate);
        }
        return this.mapWidgetManager;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void showOverlay(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.showOverlayMap(this.appEngineHandle, this.viewInfo.getAppName(), this.viewName, str, this.isDev);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void hideOverlay(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.hideOverlayMap(this.appEngineHandle, this.viewInfo.getAppName(), this.viewName, str, this.isDev);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public MapView getMapView() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.mapView;
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPoiClickable(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setPoiClickable(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public boolean isPoiClickable() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.isPoiClickable(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPoiVisible(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setPoiVisible(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public boolean isPoiVisible() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.isPoiVisible(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public boolean setPoiLanguage(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.setPoiLanguage(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public String getPoiLanguage() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getPoiLanguage(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public String[] getSupportedLanguages() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getSupportedLanguages(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPoiScale(PoiScale poiScale) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setPoiScale(this.appEngineHandle, this.viewName, poiScale.getValue());
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public PoiScale getPoiScale() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return PoiScale.getEnum(RenderViewController.getPoiScale(this.appEngineHandle, this.viewName));
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPoiVisibleMaxLevel(int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setPoiVisibleMaxLevel(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getPoiVisibleMaxLevel() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getPoiVisibleMaxLevel(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void requestPoiState(KakaoMap.OnPoiStateRequestListener onPoiStateRequestListener) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (onPoiStateRequestListener == null) {
            return;
        }
        String uniqueId = MapUtils.getUniqueId(onPoiStateRequestListener.hashCode());
        this.eventListener.addWaitingListener(uniqueId, onPoiStateRequestListener);
        RenderViewController.requestPoiState(this.appEngineHandle, this.viewName, uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setVisiblePoiCategory(PoiCategory poiCategory, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setVisiblePoiCategory(this.appEngineHandle, this.viewName, poiCategory.getValue(), z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setPoiVisibleMaxCount(PoiCategory poiCategory, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setPoiVisibleMaxCount(this.appEngineHandle, this.viewName, poiCategory.getValue(), i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void resetPoiVisibleMaxCount(PoiCategory poiCategory) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.resetPoiVisibleMaxCount(this.appEngineHandle, this.viewName, poiCategory.getValue());
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public float distance(LatLng latLng, LatLng latLng2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (latLng == null || latLng2 == null) {
            throw new RuntimeException("distance failure. point is invalid.");
        }
        return RenderViewController.distance(this.appEngineHandle, latLng.latitude, latLng.longitude, latLng2.latitude, latLng2.longitude);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public float distance(Coordinate coordinate, Coordinate coordinate2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (coordinate == null || coordinate.getCoordType() == MapCoordType.Undefined || coordinate2 == null || coordinate2.getCoordType() == MapCoordType.Undefined) {
            throw new RuntimeException("distance failure. point is invalid.");
        }
        if (coordinate.getCoordType() != coordinate2.getCoordType()) {
            throw new RuntimeException("distance failure. MapCoordType must be the same.");
        }
        return RenderViewController.distance(this.appEngineHandle, coordinate.getX(), coordinate.getY(), coordinate2.getX(), coordinate2.getY());
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setEnableCameraAnimation(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        MapCameraController.setEnableCameraAnimation(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public boolean isMapClickable() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.isMapClickable(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void addFont(final String str, final int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.kakao.vectormap.internal.KakaoMapDelegate.1
            @Override // java.lang.Runnable
            public void run() {
                byte[] resourceBytes = KakaoMapDelegate.this.resourceManager.getResourceBytes(i, null);
                if (resourceBytes == null || resourceBytes.length == 0) {
                    MapLogger.e("addFont failed. resourceId is invalid(" + str + ")");
                } else {
                    RenderViewController.addFont(KakaoMapDelegate.this.appEngineHandle, KakaoMapDelegate.this.viewName, str, resourceBytes);
                }
            }
        });
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void clearDiskCache() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.clearDiskCache(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void clearAllCache() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.clearAllCache(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void setClickIntervalTime(int i) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setClickIntervalTime(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public int getClickIntervalTime() {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return RenderViewController.getClickIntervalTime(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void changeViewInfo(MapViewInfo mapViewInfo) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (mapViewInfo.getAppName() == null || mapViewInfo.getAppName().isEmpty()) {
            throw new RuntimeException("changeViewInfo failed. appName is invalid(" + mapViewInfo.getAppName() + ")");
        }
        if (mapViewInfo.getMapType() == null || mapViewInfo.getMapType().isEmpty()) {
            throw new RuntimeException("changeViewInfo failed. mapType is invalid(" + mapViewInfo.getMapType() + ")");
        }
        RenderViewController.changeViewInfo(this.appEngineHandle, this.viewName, this.isDev, mapViewInfo.getAppName(), mapViewInfo.getMapType(), mapViewInfo.getMapStyle());
    }

    @Override // com.kakao.vectormap.internal.IKakaoMapDelegate
    public void changeMapType(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (str == null || str.isEmpty()) {
            throw new RuntimeException("changeMapType failed. mapType is invalid(" + str + ")");
        }
        RenderViewController.changeViewInfo(this.appEngineHandle, this.viewName, this.isDev, this.viewInfo.getAppName(), str, this.viewInfo.getMapStyle());
    }

    @Override // com.kakao.vectormap.internal.ITrackingDelegate
    public void startTracking(Label label) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (label == null) {
            throw new RuntimeException("startTracking failed. Param is null.");
        }
        RenderViewController.startTrackingLabel(this.appEngineHandle, this.viewName, label.getLayerId(), label.getLabelId());
    }

    @Override // com.kakao.vectormap.internal.ITrackingDelegate
    public void stopTracking() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.stopTracking(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.ITrackingDelegate
    public void setTrackingRotation(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RenderViewController.setTrackingRoll(this.appEngineHandle, this.viewName, z);
    }
}

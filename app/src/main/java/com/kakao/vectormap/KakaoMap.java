package com.kakao.vectormap;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.kakao.vectormap.camera.CameraAnimation;
import com.kakao.vectormap.camera.CameraPosition;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.internal.IKakaoMapDelegate;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelManager;
import com.kakao.vectormap.label.LodLabel;
import com.kakao.vectormap.label.LodLabelLayer;
import com.kakao.vectormap.label.TrackingManager;
import com.kakao.vectormap.mapwidget.InfoWindow;
import com.kakao.vectormap.mapwidget.MapWidget;
import com.kakao.vectormap.mapwidget.MapWidgetManager;
import com.kakao.vectormap.route.RouteLineManager;
import com.kakao.vectormap.shape.DimScreenManager;
import com.kakao.vectormap.shape.ShapeManager;
import com.kakao.vectormap.zone.ZoneEvent;
import com.kakao.vectormap.zone.ZoneManager;

/* JADX INFO: loaded from: classes4.dex */
public final class KakaoMap {
    private IKakaoMapDelegate delegate;

    public interface OnCameraMoveEndListener {
        void onCameraMoveEnd(KakaoMap kakaoMap, CameraPosition cameraPosition, GestureType gestureType);
    }

    public interface OnCameraMoveStartListener {
        void onCameraMoveStart(KakaoMap kakaoMap, GestureType gestureType);
    }

    public interface OnCameraPositionListener {
        void onCameraPosition(CameraPosition cameraPosition);
    }

    public interface OnCompassClickListener {
        void onCompassClicked(KakaoMap kakaoMap, Compass compass);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClicked(KakaoMap kakaoMap, InfoWindow infoWindow, String str);
    }

    public interface OnLabelClickListener {
        boolean onLabelClicked(KakaoMap kakaoMap, LabelLayer labelLayer, Label label);
    }

    public interface OnLodLabelClickListener {
        boolean onLodLabelClicked(KakaoMap kakaoMap, LodLabelLayer lodLabelLayer, LodLabel lodLabel);
    }

    public interface OnMapClickListener {
        void onMapClicked(KakaoMap kakaoMap, LatLng latLng, PointF pointF, Poi poi);
    }

    public interface OnMapViewInfoChangeListener {
        void onMapViewInfoChangeFailed();

        void onMapViewInfoChanged(MapViewInfo mapViewInfo);
    }

    public interface OnMapWidgetClickListener {
        void onMapWidgetClicked(KakaoMap kakaoMap, MapWidget mapWidget, String str);
    }

    public interface OnPaddingChangeListener {
        void onViewportPaddingChanged(KakaoMap kakaoMap);
    }

    public interface OnPoiClickListener {
        void onPoiClicked(KakaoMap kakaoMap, LatLng latLng, String str, String str2);
    }

    public interface OnPoiStateRequestListener {
        void onPoiStateRequested(KakaoMap kakaoMap, PoiState poiState);
    }

    public interface OnTerrainClickListener {
        void onTerrainClicked(KakaoMap kakaoMap, LatLng latLng, PointF pointF);
    }

    public interface OnTerrainLongClickListener {
        void onTerrainLongClicked(KakaoMap kakaoMap, LatLng latLng, PointF pointF);
    }

    public interface OnViewportChangeListener {
        void onViewportChanged(KakaoMap kakaoMap, Rect rect);
    }

    public interface OnViewportClickListener {
        void onViewportClicked(KakaoMap kakaoMap, LatLng latLng, PointF pointF);
    }

    public interface OnVisibleChangeListener {
        void onVisibleChangeListener(KakaoMap kakaoMap, boolean z);
    }

    public interface OnZoneEventListener {
        void onZoneCreated(KakaoMap kakaoMap, ZoneEvent zoneEvent);

        void onZoneRemoved(KakaoMap kakaoMap, ZoneEvent zoneEvent);
    }

    KakaoMap(IKakaoMapDelegate iKakaoMapDelegate) {
        this.delegate = iKakaoMapDelegate;
    }

    public boolean isDev() {
        try {
            return this.delegate.isDev();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
    }

    public Object getTag() {
        try {
            return this.delegate.getTag();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return "";
        }
    }

    public void setTag(Object obj) {
        try {
            this.delegate.setTag(obj);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public String getViewName() {
        try {
            return this.delegate.getViewName();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return "";
        }
    }

    public void setVisible(boolean z) {
        try {
            this.delegate.setVisible(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.delegate.isVisible();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
    }

    public void setViewport(int i, int i2) {
        try {
            this.delegate.setViewport(i, i2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setViewport(int i, int i2, int i3, int i4) {
        try {
            this.delegate.setViewport(i, i2, i3, i4);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setViewport(Rect rect) {
        try {
            this.delegate.setViewport(rect);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public Rect getViewport() {
        try {
            return this.delegate.getViewport();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return new Rect(0, 0, 0, 0);
        }
    }

    public synchronized void setPadding(int i, int i2, int i3, int i4) {
        try {
            this.delegate.setPadding(i, i2, i3, i4);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Padding getPadding() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return new Padding();
        }
        return this.delegate.getPadding();
    }

    public synchronized void setClickIntervalTime(int i) {
        try {
            this.delegate.setClickIntervalTime(i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getClickIntervalTime() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
        return this.delegate.getClickIntervalTime();
    }

    public int getZoomLevel() {
        try {
            return this.delegate.getZoomLevel();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
    }

    public int getMaxZoomLevel() {
        try {
            return this.delegate.getMaxZoomLevel();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
    }

    public int getMinZoomLevel() {
        try {
            return this.delegate.getMinZoomLevel();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
    }

    public int getCameraMaxLevel() {
        try {
            return this.delegate.getCameraMaxLevel();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
    }

    public void setCameraMaxLevel(int i) {
        try {
            this.delegate.setCameraMaxLevel(i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public int getCameraMinLevel() {
        try {
            return this.delegate.getCameraMinLevel();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 0;
        }
    }

    public void setCameraMinLevel(int i) {
        try {
            this.delegate.setCameraMinLevel(i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized LatLng fromScreenPoint(int i, int i2) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getMapPoint(i, i2);
    }

    public synchronized Point toScreenPoint(LatLng latLng) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getScreenPoint(latLng);
    }

    public CameraPosition getCameraPosition() {
        try {
            return this.delegate.getCameraPosition();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public void getCameraPosition(OnCameraPositionListener onCameraPositionListener) {
        try {
            this.delegate.requestCameraPosition(onCameraPositionListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.delegate.moveCamera(cameraUpdate);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void moveCamera(CameraUpdate cameraUpdate, CameraAnimation cameraAnimation) {
        try {
            this.delegate.animateCamera(cameraUpdate, cameraAnimation.getDuration(), cameraAnimation.isAutoElevation(), cameraAnimation.isConsecutive());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setBuildingHeightScale(float f) {
        try {
            this.delegate.setBuildingHeightScale(f);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized float getBuildingHeightScale() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return 1.0f;
        }
        return this.delegate.getBuildingHeightScale();
    }

    public Compass getCompass() {
        try {
            return this.delegate.getCompass();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public Logo getLogo() {
        try {
            return this.delegate.getLogo();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public ScaleBar getScaleBar() {
        try {
            return this.delegate.getScaleBar();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public void setOnViewportClickListener(OnViewportClickListener onViewportClickListener) {
        try {
            this.delegate.setOnViewportClickListener(onViewportClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnPoiClickListener(OnPoiClickListener onPoiClickListener) {
        try {
            this.delegate.setOnPoiClickListener(onPoiClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnViewportChangeListener(OnViewportChangeListener onViewportChangeListener) {
        try {
            this.delegate.setOnViewportChangeListener(onViewportChangeListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnPaddingChangeListener(OnPaddingChangeListener onPaddingChangeListener) {
        try {
            this.delegate.setOnPaddingResizeListener(onPaddingChangeListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        try {
            this.delegate.setOnMapClickListener(onMapClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnTerrainClickListener(OnTerrainClickListener onTerrainClickListener) {
        try {
            this.delegate.setOnTerrainClickListener(onTerrainClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnTerrainLongClickListener(OnTerrainLongClickListener onTerrainLongClickListener) {
        try {
            this.delegate.setOnTerrainLongClickListener(onTerrainLongClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnVisibleChangeListener(OnVisibleChangeListener onVisibleChangeListener) {
        try {
            this.delegate.setOnVisibleChangeListener(onVisibleChangeListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnCameraMoveStartListener(OnCameraMoveStartListener onCameraMoveStartListener) {
        try {
            this.delegate.setOnCameraMoveStartListener(onCameraMoveStartListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnCameraMoveEndListener(OnCameraMoveEndListener onCameraMoveEndListener) {
        try {
            this.delegate.setOnCameraMoveEndListener(onCameraMoveEndListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener) {
        try {
            this.delegate.setOnLabelClickListener(onLabelClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnLodLabelClickListener(OnLodLabelClickListener onLodLabelClickListener) {
        try {
            this.delegate.setOnLodLabelClickListener(onLodLabelClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.delegate.setOnInfoWindowClickListener(onInfoWindowClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnMapWidgetClickListener(OnMapWidgetClickListener onMapWidgetClickListener) {
        try {
            this.delegate.setOnMapWidgetClickListener(onMapWidgetClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnCompassClickListener(OnCompassClickListener onCompassClickListener) {
        try {
            this.delegate.setOnCompassClickListener(onCompassClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnMapViewInfoChangeListener(OnMapViewInfoChangeListener onMapViewInfoChangeListener) {
        try {
            this.delegate.setOnMapViewInfoChangeListener(onMapViewInfoChangeListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setOnZoneEventListener(OnZoneEventListener onZoneEventListener) {
        try {
            this.delegate.setOnZoneInfoEventListener(onZoneEventListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public RouteLineManager getRouteLineManager() {
        try {
            return this.delegate.getRouteLineManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public LabelManager getLabelManager() {
        try {
            return this.delegate.getLabelManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public ShapeManager getShapeManager() {
        try {
            return this.delegate.getShapeManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public DimScreenManager getDimScreenManager() {
        try {
            return this.delegate.getDimScreenManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public TrackingManager getTrackingManager() {
        try {
            return this.delegate.getTrackingManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public ZoneManager getZoneManager() {
        try {
            return this.delegate.getZoneManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public void setFixedCenter(boolean z, GestureType... gestureTypeArr) {
        if (gestureTypeArr != null) {
            try {
                if (gestureTypeArr.length != 0) {
                    this.delegate.enableFixedCenterPoint(z, gestureTypeArr);
                    return;
                }
            } catch (RuntimeException e) {
                MapLogger.e(e);
                return;
            }
        }
        throw new RuntimeException("Invalid GestureType");
    }

    public void setCameraAnimateEnable(boolean z) {
        try {
            this.delegate.setEnableCameraAnimation(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setGestureEnable(GestureType gestureType, boolean z) {
        try {
            this.delegate.setGestureEnable(gestureType, z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean canShowMapPoints(int i, LatLng... latLngArr) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
        return this.delegate.canShowMapPoints(i, latLngArr);
    }

    public float getMapDpScale() {
        try {
            return this.delegate.getMapDpScale();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return -1.0f;
        }
    }

    public MapWidgetManager getMapWidgetManager() {
        try {
            return this.delegate.getMapWidgetManager();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void showOverlay(String str) {
        try {
            this.delegate.showOverlay(str);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showOverlay(MapOverlay mapOverlay) {
        try {
            this.delegate.showOverlay(mapOverlay.getValue());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideOverlay(String str) {
        try {
            this.delegate.hideOverlay(str);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideOverlay(MapOverlay mapOverlay) {
        try {
            this.delegate.hideOverlay(mapOverlay.getValue());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeMapViewInfo(MapViewInfo mapViewInfo) {
        try {
            this.delegate.changeViewInfo(mapViewInfo);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeMapType(MapType mapType) {
        try {
            this.delegate.changeMapType(mapType.getValue());
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeMapType(String str) {
        try {
            this.delegate.changeMapType(str);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized MapViewInfo getMapViewInfo() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getMapViewInfo();
    }

    public synchronized void setPoiClickable(boolean z) {
        try {
            this.delegate.setPoiClickable(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isPoiClickable() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
        return this.delegate.isPoiClickable();
    }

    public synchronized void setPoiVisible(boolean z) {
        try {
            this.delegate.setPoiVisible(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isPoiVisible() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
        return this.delegate.isPoiVisible();
    }

    public synchronized boolean setPoiLanguage(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
        return this.delegate.setPoiLanguage(str);
    }

    public synchronized String getPoiLanguage() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPoiLanguage();
    }

    public synchronized String[] getSupportedLanguages() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getSupportedLanguages();
    }

    public synchronized void setPoiScale(PoiScale poiScale) {
        try {
            this.delegate.setPoiScale(poiScale);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized PoiScale getPoiScale() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return PoiScale.REGULAR;
        }
        return this.delegate.getPoiScale();
    }

    public synchronized void requestPoiState(OnPoiStateRequestListener onPoiStateRequestListener) {
        try {
            this.delegate.requestPoiState(onPoiStateRequestListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setVisiblePoiCategory(PoiCategory poiCategory, boolean z) {
        try {
            this.delegate.setVisiblePoiCategory(poiCategory, z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setPoiVisibleMaxCount(PoiCategory poiCategory, int i) {
        try {
            this.delegate.setPoiVisibleMaxCount(poiCategory, i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void resetPoiVisibleMaxCount(PoiCategory poiCategory) {
        try {
            this.delegate.resetPoiVisibleMaxCount(poiCategory);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isMapClickable() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
        return this.delegate.isMapClickable();
    }

    public synchronized void addFont(int i) {
        try {
            addFont(String.valueOf(i), i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void addFont(String str, int i) {
        try {
            this.delegate.addFont(str, i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearDiskCache() {
        try {
            this.delegate.clearDiskCache();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearAllCache() {
        try {
            this.delegate.clearAllCache();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}

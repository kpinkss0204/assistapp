package com.kakao.vectormap.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.kakao.vectormap.Compass;
import com.kakao.vectormap.Coordinate;
import com.kakao.vectormap.GestureType;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.MapViewInfo;
import com.kakao.vectormap.Padding;
import com.kakao.vectormap.PoiCategory;
import com.kakao.vectormap.PoiScale;
import com.kakao.vectormap.ScaleBar;
import com.kakao.vectormap.camera.CameraPosition;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.label.LabelManager;
import com.kakao.vectormap.label.TrackingManager;
import com.kakao.vectormap.mapwidget.MapWidgetManager;
import com.kakao.vectormap.route.RouteLineManager;
import com.kakao.vectormap.shape.DimScreenManager;
import com.kakao.vectormap.shape.ShapeManager;
import com.kakao.vectormap.zone.ZoneManager;

/* JADX INFO: loaded from: classes4.dex */
public interface IKakaoMapDelegate extends IRenderViewDelegate {
    void addFont(String str, int i) throws RuntimeException;

    void animateCamera(CameraUpdate cameraUpdate, int i, boolean z, boolean z2);

    boolean canShowMapPoints(int i, LatLng... latLngArr) throws RuntimeException;

    void changeMapType(String str) throws RuntimeException;

    void changeViewInfo(MapViewInfo mapViewInfo) throws RuntimeException;

    void clearAllCache() throws RuntimeException;

    void clearDiskCache() throws RuntimeException;

    float distance(Coordinate coordinate, Coordinate coordinate2) throws RuntimeException;

    float distance(LatLng latLng, LatLng latLng2) throws RuntimeException;

    void enableFixedCenterPoint(boolean z, GestureType... gestureTypeArr);

    float getBuildingHeightScale() throws RuntimeException;

    int getCameraMaxLevel() throws RuntimeException;

    int getCameraMinLevel() throws RuntimeException;

    CameraPosition getCameraPosition() throws RuntimeException;

    int getClickIntervalTime();

    Compass getCompass() throws RuntimeException;

    Compass getCompass(boolean z) throws RuntimeException;

    DimScreenManager getDimScreenManager() throws RuntimeException;

    LabelManager getLabelManager() throws RuntimeException;

    float getMapDpScale() throws RuntimeException;

    LatLng getMapPoint(int i, int i2) throws RuntimeException;

    MapView getMapView() throws RuntimeException;

    MapViewInfo getMapViewInfo() throws RuntimeException;

    MapWidgetManager getMapWidgetManager() throws RuntimeException;

    int getMaxZoomLevel() throws RuntimeException;

    int getMinZoomLevel() throws RuntimeException;

    Padding getPadding() throws RuntimeException;

    String getPoiLanguage() throws RuntimeException;

    PoiScale getPoiScale() throws RuntimeException;

    int getPoiVisibleMaxLevel() throws RuntimeException;

    RouteLineManager getRouteLineManager() throws RuntimeException;

    ScaleBar getScaleBar() throws RuntimeException;

    ScaleBar getScaleBar(boolean z) throws RuntimeException;

    Point getScreenPoint(LatLng latLng) throws RuntimeException;

    ShapeManager getShapeManager() throws RuntimeException;

    String[] getSupportedLanguages() throws RuntimeException;

    Object getTag();

    TrackingManager getTrackingManager() throws RuntimeException;

    String getViewName();

    Rect getViewport() throws RuntimeException;

    ZoneManager getZoneManager() throws RuntimeException;

    int getZoomLevel() throws RuntimeException;

    void hideOverlay(String str) throws RuntimeException;

    boolean isDev() throws RuntimeException;

    boolean isMapClickable() throws RuntimeException;

    boolean isPoiClickable() throws RuntimeException;

    boolean isPoiVisible() throws RuntimeException;

    boolean isVisible() throws RuntimeException;

    void moveCamera(CameraUpdate cameraUpdate) throws RuntimeException;

    void requestCameraPosition(KakaoMap.OnCameraPositionListener onCameraPositionListener) throws RuntimeException;

    void requestPoiState(KakaoMap.OnPoiStateRequestListener onPoiStateRequestListener) throws RuntimeException;

    void resetPoiVisibleMaxCount(PoiCategory poiCategory) throws RuntimeException;

    void setBuildingHeightScale(float f) throws RuntimeException;

    void setCameraMaxLevel(int i) throws RuntimeException;

    void setCameraMinLevel(int i) throws RuntimeException;

    void setClickIntervalTime(int i);

    void setCompassBackToNorth(boolean z) throws RuntimeException;

    void setCompassPosition(int i, float f, float f2) throws RuntimeException;

    void setCompassVisible(boolean z) throws RuntimeException;

    void setEnableCameraAnimation(boolean z) throws RuntimeException;

    void setGestureEnable(GestureType gestureType, boolean z);

    void setOnCameraMoveEndListener(KakaoMap.OnCameraMoveEndListener onCameraMoveEndListener) throws RuntimeException;

    void setOnCameraMoveStartListener(KakaoMap.OnCameraMoveStartListener onCameraMoveStartListener) throws RuntimeException;

    void setOnCompassClickListener(KakaoMap.OnCompassClickListener onCompassClickListener) throws RuntimeException;

    void setOnInfoWindowClickListener(KakaoMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RuntimeException;

    void setOnLabelClickListener(KakaoMap.OnLabelClickListener onLabelClickListener) throws RuntimeException;

    void setOnLodLabelClickListener(KakaoMap.OnLodLabelClickListener onLodLabelClickListener) throws RuntimeException;

    void setOnMapClickListener(KakaoMap.OnMapClickListener onMapClickListener) throws RuntimeException;

    void setOnMapViewInfoChangeListener(KakaoMap.OnMapViewInfoChangeListener onMapViewInfoChangeListener) throws RuntimeException;

    void setOnMapWidgetClickListener(KakaoMap.OnMapWidgetClickListener onMapWidgetClickListener) throws RuntimeException;

    void setOnPaddingResizeListener(KakaoMap.OnPaddingChangeListener onPaddingChangeListener) throws RuntimeException;

    void setOnPoiClickListener(KakaoMap.OnPoiClickListener onPoiClickListener) throws RuntimeException;

    void setOnTerrainClickListener(KakaoMap.OnTerrainClickListener onTerrainClickListener) throws RuntimeException;

    void setOnTerrainLongClickListener(KakaoMap.OnTerrainLongClickListener onTerrainLongClickListener) throws RuntimeException;

    void setOnViewportChangeListener(KakaoMap.OnViewportChangeListener onViewportChangeListener) throws RuntimeException;

    void setOnViewportClickListener(KakaoMap.OnViewportClickListener onViewportClickListener) throws RuntimeException;

    void setOnVisibleChangeListener(KakaoMap.OnVisibleChangeListener onVisibleChangeListener) throws RuntimeException;

    void setOnZoneInfoEventListener(KakaoMap.OnZoneEventListener onZoneEventListener) throws RuntimeException;

    void setPadding(int i, int i2, int i3, int i4) throws RuntimeException;

    void setPoiClickable(boolean z) throws RuntimeException;

    boolean setPoiLanguage(String str) throws RuntimeException;

    void setPoiScale(PoiScale poiScale) throws RuntimeException;

    void setPoiVisible(boolean z) throws RuntimeException;

    void setPoiVisibleMaxCount(PoiCategory poiCategory, int i) throws RuntimeException;

    void setPoiVisibleMaxLevel(int i) throws RuntimeException;

    void setScaleBarAutoHide(boolean z) throws RuntimeException;

    void setScaleBarFadeInOutTime(int i, int i2, int i3) throws RuntimeException;

    void setScaleBarPosition(int i, float f, float f2) throws RuntimeException;

    void setScaleBarVisible(boolean z) throws RuntimeException;

    void setTag(Object obj);

    void setViewport(int i, int i2) throws RuntimeException;

    void setViewport(int i, int i2, int i3, int i4) throws RuntimeException;

    void setViewport(Rect rect) throws RuntimeException;

    void setVisible(boolean z) throws RuntimeException;

    void setVisiblePoiCategory(PoiCategory poiCategory, boolean z) throws RuntimeException;

    void showOverlay(String str) throws RuntimeException;
}

package com.kakao.vectormap.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import com.kakao.vectormap.GestureType;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.Padding;
import com.kakao.vectormap.Poi;
import com.kakao.vectormap.PoiScale;
import com.kakao.vectormap.PoiState;
import com.kakao.vectormap.camera.CameraPosition;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelAnimator;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LodLabel;
import com.kakao.vectormap.label.LodLabelLayer;
import com.kakao.vectormap.label.OnLabelAnimationStopListener;
import com.kakao.vectormap.label.OnLabelCreateCallback;
import com.kakao.vectormap.label.OnLabelsCreateCallback;
import com.kakao.vectormap.label.OnLodLabelCreateCallback;
import com.kakao.vectormap.label.OnLodLabelsCreateCallback;
import com.kakao.vectormap.label.OnPolylineLabelCreateCallback;
import com.kakao.vectormap.label.PolylineLabel;
import com.kakao.vectormap.mapwidget.InfoWindow;
import com.kakao.vectormap.mapwidget.MapWidget;
import com.kakao.vectormap.route.OnRouteLineAnimatorStopCallback;
import com.kakao.vectormap.route.OnRouteLineCreateCallback;
import com.kakao.vectormap.route.OnRouteLineProgressEndCallback;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineAnimator;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.shape.DimScreenLayer;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.shape.Polyline;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.zone.LinkingInfo;
import com.kakao.vectormap.zone.ZoneEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class EventListener extends Destroyable {
    private KakaoMap.OnCameraMoveEndListener cameraMoveEndListener;
    private KakaoMap.OnCameraMoveStartListener cameraMoveStartListener;
    private KakaoMap.OnCompassClickListener compassClickListener;
    private IDimScreenDelegate dimScreenDelegate;
    private KakaoMap.OnInfoWindowClickListener infoWindowClickListener;
    private KakaoMap kakaoMap;
    private KakaoMapDelegate kakaoMapDelegate;
    private KakaoMap.OnLabelClickListener labelClickListener;
    private ILabelDelegate labelDelegate;
    private KakaoMap.OnLodLabelClickListener lodLabelClickListener;
    private KakaoMap.OnMapClickListener mapClickListener;
    private KakaoMap.OnViewportChangeListener mapResizeListener;
    private KakaoMap.OnMapViewInfoChangeListener mapViewInfoChangeListener;
    private KakaoMap.OnMapWidgetClickListener mapWidgetClickListener;
    private KakaoMap.OnPaddingChangeListener paddingResizeListener;
    private KakaoMap.OnPoiClickListener poiClickListener;
    private IRouteLineDelegate routeLineDelegate;
    private IShapeDelegate shapeDelegate;
    private KakaoMap.OnTerrainClickListener terrainClickListener;
    private KakaoMap.OnTerrainLongClickListener terrainLongClickListener;
    private KakaoMap.OnViewportClickListener viewportClickListener;
    private KakaoMap.OnVisibleChangeListener visibleChangeListener;
    private KakaoMap.OnZoneEventListener zoneEventListener;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Map<String, Object> waitingListeners = new ConcurrentHashMap();

    @Override // com.kakao.vectormap.internal.Destroyable
    public void onDestroy() {
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ void setRunning(boolean z) {
        super.setRunning(z);
    }

    EventListener(KakaoMap kakaoMap, KakaoMapDelegate kakaoMapDelegate) {
        this.kakaoMap = kakaoMap;
        this.kakaoMapDelegate = kakaoMapDelegate;
    }

    public synchronized void addWaitingListener(String str, Object obj) {
        this.waitingListeners.put(str, obj);
    }

    public void setPoiClickListener(KakaoMap.OnPoiClickListener onPoiClickListener) {
        this.poiClickListener = onPoiClickListener;
    }

    public void setMapResizeListener(KakaoMap.OnViewportChangeListener onViewportChangeListener) {
        this.mapResizeListener = onViewportChangeListener;
    }

    public void setPaddingResizeListener(KakaoMap.OnPaddingChangeListener onPaddingChangeListener) {
        this.paddingResizeListener = onPaddingChangeListener;
    }

    public void setMapClickListener(KakaoMap.OnMapClickListener onMapClickListener) {
        this.mapClickListener = onMapClickListener;
    }

    public void setViewportClickListener(KakaoMap.OnViewportClickListener onViewportClickListener) {
        this.viewportClickListener = onViewportClickListener;
    }

    public void setTerrainClickListener(KakaoMap.OnTerrainClickListener onTerrainClickListener) {
        this.terrainClickListener = onTerrainClickListener;
    }

    public void setTerrainLongClickListener(KakaoMap.OnTerrainLongClickListener onTerrainLongClickListener) {
        this.terrainLongClickListener = onTerrainLongClickListener;
    }

    public void setVisibleChangeListener(KakaoMap.OnVisibleChangeListener onVisibleChangeListener) {
        this.visibleChangeListener = onVisibleChangeListener;
    }

    public void setCameraMoveStartListener(KakaoMap.OnCameraMoveStartListener onCameraMoveStartListener) {
        this.cameraMoveStartListener = onCameraMoveStartListener;
    }

    public void setCameraMoveEndListener(KakaoMap.OnCameraMoveEndListener onCameraMoveEndListener) {
        this.cameraMoveEndListener = onCameraMoveEndListener;
    }

    public void setLabelClickListener(KakaoMap.OnLabelClickListener onLabelClickListener) {
        this.labelClickListener = onLabelClickListener;
    }

    public void setLodLabelClickListener(KakaoMap.OnLodLabelClickListener onLodLabelClickListener) {
        this.lodLabelClickListener = onLodLabelClickListener;
    }

    public void setInfoWindowClickListener(KakaoMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.infoWindowClickListener = onInfoWindowClickListener;
    }

    public void setMapWidgetClickListener(KakaoMap.OnMapWidgetClickListener onMapWidgetClickListener) {
        this.mapWidgetClickListener = onMapWidgetClickListener;
    }

    public void setCompassClickListener(KakaoMap.OnCompassClickListener onCompassClickListener) {
        this.compassClickListener = onCompassClickListener;
    }

    public void setMapViewInfoChangeListener(KakaoMap.OnMapViewInfoChangeListener onMapViewInfoChangeListener) {
        this.mapViewInfoChangeListener = onMapViewInfoChangeListener;
    }

    public void setZoneEventListener(KakaoMap.OnZoneEventListener onZoneEventListener) {
        this.zoneEventListener = onZoneEventListener;
    }

    public void setLabelDelegate(ILabelDelegate iLabelDelegate) {
        this.labelDelegate = iLabelDelegate;
    }

    public void setRouteLineDelegate(IRouteLineDelegate iRouteLineDelegate) {
        this.routeLineDelegate = iRouteLineDelegate;
    }

    public void setShapeDelegate(IShapeDelegate iShapeDelegate) {
        this.shapeDelegate = iShapeDelegate;
    }

    public void setDimScreenDelegate(IDimScreenDelegate iDimScreenDelegate) {
        this.dimScreenDelegate = iDimScreenDelegate;
    }

    public void onPoiClicked(final String str, final String str2, final String str3, final double d, final double d2, final float f, final float f2) {
        if (!isRunning()) {
            MapLogger.e("onPoiClicked return. Initialization Failed, Reload Map Required.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handlePoiClicked(str, str2, str3, d, d2, f, f2);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePoiClicked(String str, String str2, String str3, double d, double d2, float f, float f2) {
        LabelLayer labelLayer;
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate != null) {
            if (iLabelDelegate.hasLodLayer(str) && this.lodLabelClickListener != null) {
                LodLabelLayer lodLabelLayer = this.labelDelegate.getLodLabelLayer(str);
                if (lodLabelLayer != null && lodLabelLayer.containsLabel(str2)) {
                    if (this.lodLabelClickListener.onLodLabelClicked(this.kakaoMap, lodLabelLayer, lodLabelLayer.getLabel(str2))) {
                        return;
                    }
                }
            } else if (this.labelDelegate.hasLayer(str) && this.labelClickListener != null && (labelLayer = this.labelDelegate.getLabelLayer(str)) != null && labelLayer.hasLabel(str2)) {
                if (this.labelClickListener.onLabelClicked(this.kakaoMap, labelLayer, labelLayer.getLabel(str2))) {
                    return;
                }
            }
        }
        KakaoMap.OnMapClickListener onMapClickListener = this.mapClickListener;
        if (onMapClickListener != null) {
            onMapClickListener.onMapClicked(this.kakaoMap, LatLng.from(d, d2), new PointF(f, f2), new Poi(true, str2, str, str3));
        }
        KakaoMap.OnPoiClickListener onPoiClickListener = this.poiClickListener;
        if (onPoiClickListener != null) {
            onPoiClickListener.onPoiClicked(this.kakaoMap, LatLng.from(d, d2), str, str2);
        }
    }

    public void onMapResized(int i, int i2, int i3, int i4) {
        if (!isRunning()) {
            MapLogger.e("onMapResized return. Initialization Failed, Reload Map Required.");
        }
        final Rect viewportInternal = this.kakaoMapDelegate.setViewportInternal(i, i2, i3, i4);
        if (this.mapResizeListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.mapResizeListener.onViewportChanged(EventListener.this.kakaoMap, viewportInternal);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public void onPaddingResized(int i, int i2, int i3, int i4) {
        if (!isRunning()) {
            MapLogger.e("onPaddingResized return. Initialization Failed, Reload Map Required.");
        }
        Padding padding = this.kakaoMapDelegate.getPadding();
        Padding paddingInternal = this.kakaoMapDelegate.setPaddingInternal(i, i2, i3, i4);
        if (this.paddingResizeListener == null || paddingInternal.equals(padding)) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.paddingResizeListener.onViewportPaddingChanged(EventListener.this.kakaoMap);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public void onCameraMoveStart(final int i) {
        if (!isRunning()) {
            MapLogger.e("onCameraMoveStart return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.cameraMoveStartListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handleCameraMoveStart(i);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCameraMoveStart(int i) {
        this.cameraMoveStartListener.onCameraMoveStart(this.kakaoMap, GestureType.getEnum(i));
    }

    public void onCameraMoveEnd(final double d, final double d2, final int i, final double d3, final double d4, final double d5, final int i2) {
        if (!isRunning()) {
            MapLogger.e("onCameraMoveEnd return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.cameraMoveEndListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handleCameraMoveEnd(d, d2, i, d3, d4, d5, i2);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCameraMoveEnd(double d, double d2, int i, double d3, double d4, double d5, int i2) {
        this.cameraMoveEndListener.onCameraMoveEnd(this.kakaoMap, CameraPosition.from(d, d2, i, d3, d4, d5), GestureType.getEnum(i2));
    }

    public void onRenderViewClicked(final float f, final float f2, final double d, final double d2) {
        if (!isRunning()) {
            MapLogger.e("onRenderViewClicked return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.viewportClickListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handleRenderViewClicked(f, f2, d, d2);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public void handleRenderViewClicked(float f, float f2, double d, double d2) {
        this.viewportClickListener.onViewportClicked(this.kakaoMap, LatLng.from(d, d2), new PointF(f, f2));
    }

    public void onTerrainClicked(final float f, final float f2, final double d, final double d2) {
        if (!isRunning()) {
            MapLogger.e("onTerrainClicked return. Initialization Failed, Reload Map Required.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handleTerrainClicked(f, f2, d, d2);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public void handleTerrainClicked(float f, float f2, double d, double d2) {
        KakaoMap.OnMapClickListener onMapClickListener = this.mapClickListener;
        if (onMapClickListener != null) {
            onMapClickListener.onMapClicked(this.kakaoMap, LatLng.from(d, d2), new PointF(f, f2), new Poi(false));
        }
        KakaoMap.OnTerrainClickListener onTerrainClickListener = this.terrainClickListener;
        if (onTerrainClickListener != null) {
            onTerrainClickListener.onTerrainClicked(this.kakaoMap, LatLng.from(d, d2), new PointF(f, f2));
        }
    }

    public void onTerrainLongClicked(final float f, final float f2, final double d, final double d2) {
        if (!isRunning()) {
            MapLogger.e("onTerrainLongClicked return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.terrainLongClickListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.8
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.terrainLongClickListener.onTerrainLongClicked(EventListener.this.kakaoMap, LatLng.from(d, d2), new PointF(f, f2));
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public void onPolylineLabelCreated(String str, String str2) {
        final LabelLayer labelLayer;
        final Pair<OnPolylineLabelCreateCallback, PolylineLabel> polylineLabel;
        if (!isRunning()) {
            MapLogger.e("onPolylineLabelCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate == null || (labelLayer = iLabelDelegate.getLabelLayer(str2)) == null || (polylineLabel = labelLayer.getPolylineLabel(str)) == null || polylineLabel.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handlePolylineLabelCreated(labelLayer, polylineLabel);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePolylineLabelCreated(LabelLayer labelLayer, Pair<OnPolylineLabelCreateCallback, PolylineLabel> pair) {
        ((OnPolylineLabelCreateCallback) pair.first).onPolylineLabelCreated(labelLayer, (PolylineLabel) pair.second);
    }

    public void onLabelCreated(String str, String str2) {
        final LabelLayer labelLayer;
        final Pair<OnLabelCreateCallback, Label> label;
        if (!isRunning()) {
            MapLogger.e("onLabelCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate == null || (labelLayer = iLabelDelegate.getLabelLayer(str2)) == null || (label = labelLayer.getLabel(str)) == null || label.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handleLabelCreated(labelLayer, label);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLabelCreated(LabelLayer labelLayer, Pair<OnLabelCreateCallback, Label> pair) {
        ((OnLabelCreateCallback) pair.first).onLabelCreated(labelLayer, (Label) pair.second);
    }

    public void onLabelsCreated(String str, String str2) {
        final LabelLayer labelLayer;
        final Pair<OnLabelsCreateCallback, Label[]> labels;
        if (!isRunning()) {
            MapLogger.e("onLabelsCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate == null || (labelLayer = iLabelDelegate.getLabelLayer(str2)) == null || (labels = labelLayer.getLabels(str)) == null || labels.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handleLabelsCreated(labelLayer, labels);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLabelsCreated(LabelLayer labelLayer, Pair<OnLabelsCreateCallback, Label[]> pair) {
        ((OnLabelsCreateCallback) pair.first).onLabelsCreated(labelLayer, (Label[]) pair.second);
    }

    public void onLodLabelCreated(String str, String str2) {
        final LodLabelLayer lodLabelLayer;
        final Pair<OnLodLabelCreateCallback, LodLabel> label;
        if (!isRunning()) {
            MapLogger.e("onLodLabelCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate == null || (lodLabelLayer = iLabelDelegate.getLodLabelLayer(str2)) == null || (label = lodLabelLayer.getLabel(str)) == null || label.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handleLodLabelCreated(lodLabelLayer, label);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLodLabelCreated(LodLabelLayer lodLabelLayer, Pair<OnLodLabelCreateCallback, LodLabel> pair) {
        ((OnLodLabelCreateCallback) pair.first).onLodLabelCreated(lodLabelLayer, (LodLabel) pair.second);
    }

    public void onLodLabelsCreated(String str, String str2) {
        final LodLabelLayer lodLabelLayer;
        final Pair<OnLodLabelsCreateCallback, LodLabel[]> labels;
        if (!isRunning()) {
            MapLogger.e("onLodLabelsCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        ILabelDelegate iLabelDelegate = this.labelDelegate;
        if (iLabelDelegate == null || (lodLabelLayer = iLabelDelegate.getLodLabelLayer(str2)) == null || (labels = lodLabelLayer.getLabels(str)) == null || labels.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handleLodLabelsCreated(lodLabelLayer, labels);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLodLabelsCreated(LodLabelLayer lodLabelLayer, Pair<OnLodLabelsCreateCallback, LodLabel[]> pair) {
        ((OnLodLabelsCreateCallback) pair.first).onLodLabelsCreated(lodLabelLayer, (LodLabel[]) pair.second);
    }

    public void onRouteLineCreated(String str, String str2) {
        final RouteLineLayer layer;
        final Pair<OnRouteLineCreateCallback, RouteLine> routeLine;
        if (!isRunning()) {
            MapLogger.e("onRouteLineCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        IRouteLineDelegate iRouteLineDelegate = this.routeLineDelegate;
        if (iRouteLineDelegate == null || (layer = iRouteLineDelegate.getLayer(str2)) == null || (routeLine = layer.getRouteLine(str)) == null || routeLine.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handleRouteLineCreated(layer, routeLine);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRouteLineCreated(RouteLineLayer routeLineLayer, Pair<OnRouteLineCreateCallback, RouteLine> pair) {
        ((OnRouteLineCreateCallback) pair.first).onRouteLineCreated(routeLineLayer, (RouteLine) pair.second);
    }

    public void onRouteLineProgressEnd(String str, String str2, final float f) {
        RouteLineLayer layer;
        final Pair<OnRouteLineProgressEndCallback, RouteLine> routeLineByProgress;
        if (!isRunning()) {
            MapLogger.e("onRouteLineProgressEnd return. Initialization Failed, Reload Map Required.");
            return;
        }
        IRouteLineDelegate iRouteLineDelegate = this.routeLineDelegate;
        if (iRouteLineDelegate == null || (layer = iRouteLineDelegate.getLayer(str2)) == null || (routeLineByProgress = layer.getRouteLineByProgress(str)) == null || routeLineByProgress.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((OnRouteLineProgressEndCallback) routeLineByProgress.first).onRouteLineProgressEnded((RouteLine) routeLineByProgress.second, f);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public void onRouteLineAnimatorStop(String str) {
        final Pair<OnRouteLineAnimatorStopCallback, RouteLineAnimator> animatorCallback;
        if (!isRunning()) {
            MapLogger.e("onRouteLineProgressEnd return. Initialization Failed, Reload Map Required.");
            return;
        }
        IRouteLineDelegate iRouteLineDelegate = this.routeLineDelegate;
        if (iRouteLineDelegate == null || (animatorCallback = iRouteLineDelegate.getAnimatorCallback(str)) == null || animatorCallback.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((OnRouteLineAnimatorStopCallback) animatorCallback.first).onRouteLineAnimatorStopped((RouteLineAnimator) animatorCallback.second);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public void onPolygonCreated(String str, String str2, boolean z) {
        final ShapeLayer layer;
        final Pair<ShapeLayer.OnPolygonCreateCallback, Polygon> polygon;
        if (!isRunning()) {
            MapLogger.e("onPolygonCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        if (z) {
            handleDimScreenPolygonCreated(str);
            return;
        }
        IShapeDelegate iShapeDelegate = this.shapeDelegate;
        if (iShapeDelegate == null || (layer = iShapeDelegate.getLayer(str2)) == null || (polygon = layer.getPolygon(str)) == null || polygon.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handlePolygonCreated(layer, polygon);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    private void handleDimScreenPolygonCreated(String str) {
        IDimScreenDelegate iDimScreenDelegate = this.dimScreenDelegate;
        if (iDimScreenDelegate == null) {
            return;
        }
        final DimScreenLayer dimScreenLayer = iDimScreenDelegate.getDimScreenLayer();
        final Pair<DimScreenLayer.OnPolygonCreateCallback, PolygonOptions[]> callback = this.dimScreenDelegate.getCallback(str);
        if (callback.first == null) {
            MapLogger.e("DimScreenPolygonCreated return. Callback is null.");
        } else {
            final Polygon[] polygonArrNewPolygons = this.dimScreenDelegate.newPolygons((PolygonOptions[]) callback.second);
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.18
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ((DimScreenLayer.OnPolygonCreateCallback) callback.first).onPolygonCreated(dimScreenLayer, polygonArrNewPolygons);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePolygonCreated(ShapeLayer shapeLayer, Pair<ShapeLayer.OnPolygonCreateCallback, Polygon> pair) {
        ((ShapeLayer.OnPolygonCreateCallback) pair.first).onPolygonCreated(shapeLayer, (Polygon) pair.second);
    }

    public void onPolylineCreated(String str, String str2) {
        final ShapeLayer layer;
        final Pair<ShapeLayer.OnPolylineCreateCallback, Polyline> polyline;
        if (!isRunning()) {
            MapLogger.e("onPolylineCreated return. Initialization Failed, Reload Map Required.");
            return;
        }
        IShapeDelegate iShapeDelegate = this.shapeDelegate;
        if (iShapeDelegate == null || (layer = iShapeDelegate.getLayer(str2)) == null || (polyline = layer.getPolyline(str)) == null || polyline.first == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.19
            @Override // java.lang.Runnable
            public void run() {
                try {
                    EventListener.this.handlePolylineCreated(layer, polyline);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePolylineCreated(ShapeLayer shapeLayer, Pair<ShapeLayer.OnPolylineCreateCallback, Polyline> pair) {
        ((ShapeLayer.OnPolylineCreateCallback) pair.first).onPolylineCreated(shapeLayer, (Polyline) pair.second);
    }

    public void onGuiClicked(final String str, final String str2) {
        if (!isRunning()) {
            MapLogger.e("onGuiClicked return. Initialization Failed, Reload Map Required.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.20
                @Override // java.lang.Runnable
                public void run() {
                    if (Objects.equals(str, "system_compass")) {
                        if (EventListener.this.compassClickListener != null) {
                            EventListener.this.compassClickListener.onCompassClicked(EventListener.this.kakaoMap, EventListener.this.kakaoMap.getCompass());
                            return;
                        }
                        return;
                    }
                    InfoWindow infoWindow = EventListener.this.kakaoMap.getMapWidgetManager().getInfoWindowLayer().getInfoWindow(str);
                    if (infoWindow != null) {
                        if (EventListener.this.infoWindowClickListener != null) {
                            EventListener.this.infoWindowClickListener.onInfoWindowClicked(EventListener.this.kakaoMap, infoWindow, str2);
                        }
                    } else {
                        MapWidget mapWidget = EventListener.this.kakaoMap.getMapWidgetManager().getMapWidgetLayer().getMapWidget(str);
                        if (mapWidget == null || EventListener.this.mapWidgetClickListener == null) {
                            return;
                        }
                        EventListener.this.mapWidgetClickListener.onMapWidgetClicked(EventListener.this.kakaoMap, mapWidget, str2);
                    }
                }
            });
        }
    }

    public void onCameraPosition(final String str, final double d, final double d2, final int i, final double d3, final double d4, final double d5) {
        if (!isRunning()) {
            MapLogger.e("onCameraPosition return. Initialization Failed, Reload Map Required.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.21
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.handleCameraPosition(str, d, d2, i, d3, d4, d5);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public void onPoiStateRequested(final String str, final boolean z, final boolean z2, final int i, final String str2, final int i2) {
        if (!isRunning()) {
            MapLogger.e("onPoiStateRequested return. Initialization Failed, Reload Map Required.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.22
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        KakaoMap.OnPoiStateRequestListener onPoiStateRequestListener = (KakaoMap.OnPoiStateRequestListener) EventListener.this.waitingListeners.remove(str);
                        if (onPoiStateRequestListener != null) {
                            onPoiStateRequestListener.onPoiStateRequested(EventListener.this.kakaoMap, new PoiState(z, z2, PoiScale.getEnum(i), str2, i2));
                        }
                    } catch (Exception e) {
                        MapLogger.e(e);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCameraPosition(String str, double d, double d2, int i, double d3, double d4, double d5) {
        KakaoMap.OnCameraPositionListener onCameraPositionListener = (KakaoMap.OnCameraPositionListener) this.waitingListeners.remove(str);
        if (onCameraPositionListener != null) {
            onCameraPositionListener.onCameraPosition(CameraPosition.from(d, d2, i, d3, d4, d5));
        }
    }

    public synchronized void onViewInfoChanged(String str, String str2, String str3, final boolean z) {
        if (!isRunning()) {
            MapLogger.e("onViewInfoChanged return. Initialization Failed, Reload Map Required.");
            return;
        }
        if (z) {
            this.kakaoMapDelegate.setMapViewInfo(str, str2, str3);
        }
        if (this.mapViewInfoChangeListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        EventListener.this.mapViewInfoChangeListener.onMapViewInfoChanged(EventListener.this.kakaoMap.getMapViewInfo());
                    } else {
                        EventListener.this.mapViewInfoChangeListener.onMapViewInfoChangeFailed();
                    }
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public synchronized void onLabelAnimationStopped(String str) {
        if (!isRunning()) {
            MapLogger.e("onLabelAnimationStopped return. Initialization Failed, Reload Map Required.");
            return;
        }
        final LabelAnimator animator = this.labelDelegate.getAnimator(str);
        if (animator == null) {
            MapLogger.e("LabelAnimator(" + str + ") is null.");
            return;
        }
        final OnLabelAnimationStopListener stopListener = animator.getStopListener();
        if (stopListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    stopListener.onAnimationStopped(animator);
                } catch (Exception e) {
                    MapLogger.e(e.getLocalizedMessage());
                }
            }
        });
    }

    public synchronized void onVisibleChanged(final boolean z) {
        if (!isRunning()) {
            MapLogger.e("onVisibleChanged return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.visibleChangeListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.25
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        EventListener.this.visibleChangeListener.onVisibleChangeListener(EventListener.this.kakaoMap, z);
                    } catch (Exception e) {
                        MapLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    public synchronized void onZoneCreated(final String str, final String str2, final String str3, final String[] strArr, final Map<String, LinkingInfo[]> map) throws Throwable {
        Throwable th;
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            if (!isRunning()) {
                try {
                    MapLogger.e("onZoneCreated return. Initialization Failed, Reload Map Required.");
                    return;
                } catch (Throwable th3) {
                    th = th3;
                }
            } else {
                if (this.zoneEventListener == null) {
                    return;
                }
                this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.26
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            HashMap map2 = new HashMap();
                            Map map3 = map;
                            if (map3 != null) {
                                map2.putAll(map3);
                            }
                            EventListener.this.zoneEventListener.onZoneCreated(EventListener.this.kakaoMap, ZoneEvent.from(str, str2, str3, map2, strArr));
                        } catch (Exception e) {
                            MapLogger.e(e.getLocalizedMessage());
                        }
                    }
                });
                return;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
        }
        throw th;
    }

    public synchronized void onZoneRemoved(final String str, final String str2, final String str3, final String[] strArr) throws Throwable {
        Throwable th;
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            if (!isRunning()) {
                try {
                    MapLogger.e("onZoneRemoved return. Initialization Failed, Reload Map Required.");
                    return;
                } catch (Throwable th3) {
                    th = th3;
                }
            } else {
                if (this.zoneEventListener == null) {
                    return;
                }
                this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.EventListener.27
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            EventListener.this.zoneEventListener.onZoneRemoved(EventListener.this.kakaoMap, ZoneEvent.from(str, str2, str3, new HashMap(), strArr));
                        } catch (Exception e) {
                            MapLogger.e(e.getLocalizedMessage());
                        }
                    }
                });
                return;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
        }
        throw th;
    }
}

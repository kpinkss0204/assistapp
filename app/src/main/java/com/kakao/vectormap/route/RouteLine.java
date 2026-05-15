package com.kakao.vectormap.route;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IRouteLineDelegate;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLine {
    private final IRouteLineDelegate delegate;
    private final String layerId;
    private final String lineId;
    private RouteLineSegment[] segments;
    private Object tag;
    private boolean visible;
    private int zOrder;

    RouteLine(IRouteLineDelegate iRouteLineDelegate, String str, String str2, int i, RouteLineSegment[] routeLineSegmentArr, boolean z, Object obj) {
        this.delegate = iRouteLineDelegate;
        this.lineId = str;
        this.layerId = str2;
        this.zOrder = i;
        this.tag = obj;
        this.visible = z;
        this.segments = routeLineSegmentArr;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public String getLineId() {
        return this.lineId;
    }

    public RouteLineLayer getLayer() {
        return this.delegate.getLayer(this.layerId);
    }

    public synchronized List<RouteLineSegment> getSegments() {
        return Arrays.asList(this.segments);
    }

    public synchronized void show() {
        try {
            this.delegate.setVisible(this.layerId, this.lineId, true);
            this.visible = true;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setVisible(this.layerId, this.lineId, false);
            this.visible = false;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isShow() {
        return this.visible;
    }

    public synchronized void setZOrder(int i) {
        try {
            this.delegate.setZOrder(this.layerId, this.lineId, i);
            this.zOrder = i;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeSegments(RouteLineSegment... routeLineSegmentArr) {
        try {
            this.delegate.changeSegments(this.layerId, this.lineId, RouteLineOptions.toStylesSet(routeLineSegmentArr), routeLineSegmentArr);
            this.segments = routeLineSegmentArr;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeSegments(List<RouteLineSegment> list) {
        try {
            RouteLineSegment[] routeLineSegmentArr = (RouteLineSegment[]) list.toArray(new RouteLineSegment[list.size()]);
            this.delegate.changeSegments(this.layerId, this.lineId, RouteLineOptions.toStylesSet(routeLineSegmentArr), routeLineSegmentArr);
            this.segments = routeLineSegmentArr;
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStyle(RouteLineStyle... routeLineStyleArr) {
        try {
            for (RouteLineSegment routeLineSegment : this.segments) {
                routeLineSegment.setStyles(routeLineStyleArr);
            }
            this.delegate.changeStyles(this.layerId, this.lineId, RouteLineOptions.toStylesSet(this.segments), this.segments);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStyle(RouteLineStyles routeLineStyles) {
        try {
            for (RouteLineSegment routeLineSegment : this.segments) {
                routeLineSegment.setStyles(routeLineStyles);
            }
            this.delegate.changeStyles(this.layerId, this.lineId, RouteLineOptions.toStylesSet(this.segments), this.segments);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStyle(RouteLineStylesSet routeLineStylesSet) {
        try {
            this.delegate.changeStyles(this.layerId, this.lineId, routeLineStylesSet, this.segments);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public synchronized void remove() {
        try {
            this.delegate.removeRouteLine(this.layerId, this.lineId);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setProgress(float f) {
        try {
            this.delegate.setProgress(this.layerId, this.lineId, f);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public synchronized float getProgress() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return 0.0f;
        }
        return this.delegate.getProgress(this.layerId, this.lineId);
    }

    public synchronized void progressTo(float f, int i) {
        progressTo(f, i, null);
    }

    public synchronized void progressTo(float f, int i, OnRouteLineProgressEndCallback onRouteLineProgressEndCallback) {
        try {
            this.delegate.progressTo(this.layerId, this.lineId, f, i, onRouteLineProgressEndCallback);
        } catch (Exception e) {
            MapLogger.e(e);
        }
    }

    public RoutePoint getPointFromProgress(float f) {
        try {
            return this.delegate.getPointFromProgress(this.layerId, this.lineId, f);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public float getProgressFromPoint(LatLng latLng) {
        try {
            return this.delegate.getProgressFromPoint(this.layerId, this.lineId, latLng);
        } catch (Exception e) {
            MapLogger.e(e);
            return 0.0f;
        }
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }
}

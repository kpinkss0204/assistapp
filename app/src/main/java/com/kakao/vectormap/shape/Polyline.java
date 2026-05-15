package com.kakao.vectormap.shape;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IShape;
import com.kakao.vectormap.internal.IShapeDelegate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Polyline extends IShape {
    private final IShapeDelegate delegate;
    private List<DotPoints> dotPoints;
    private final String layerId;
    private List<MapPoints> mapPoints;
    private final String polylineId;
    private PolylineStylesSet stylesSet;
    private Object tag;
    private boolean toDimScreen;
    private int zOrder;

    Polyline(IShapeDelegate iShapeDelegate, String str, String str2, boolean z, int i, boolean z2, List<MapPoints> list, List<DotPoints> list2, PolylineStylesSet polylineStylesSet, Object obj) {
        super(z);
        this.delegate = iShapeDelegate;
        this.layerId = str;
        this.polylineId = str2;
        this.zOrder = i;
        this.tag = obj;
        this.toDimScreen = z2;
        this.mapPoints = new ArrayList(list);
        this.dotPoints = new ArrayList(list2);
        this.stylesSet = polylineStylesSet;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public String getId() {
        return this.polylineId;
    }

    public synchronized void show() {
        try {
            this.delegate.setPolylineVisible(this.toDimScreen, this.layerId, this.polylineId, true);
            setVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setPolylineVisible(this.toDimScreen, this.layerId, this.polylineId, false);
            setVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public boolean toDimScreen() {
        return this.toDimScreen;
    }

    public synchronized boolean isShow() {
        return isVisible();
    }

    public synchronized void remove() {
        try {
            this.delegate.removePolyline(this.toDimScreen, this.layerId, this.polylineId);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setZOrder(int i) {
        try {
            this.delegate.setPolylineZOrder(this.layerId, this.polylineId, i);
            this.zOrder = i;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setPosition(LatLng latLng) {
        try {
            this.delegate.setPolylinePosition(this.layerId, this.polylineId, latLng);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeStylesSet(PolylineStylesSet polylineStylesSet) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (polylineStylesSet == null) {
            throw new RuntimeException("Polyline changeStylesSet failure. PolylineStylesSet is invalid.");
        }
        if (!this.dotPoints.isEmpty()) {
            this.delegate.changePolylineStylesAndDotPoints(this.layerId, this.polylineId, polylineStylesSet, this.dotPoints);
            this.stylesSet = polylineStylesSet;
        } else {
            if (!this.mapPoints.isEmpty()) {
                this.delegate.changePolylineStylesAndMapPoints(this.layerId, this.polylineId, polylineStylesSet, this.mapPoints);
                this.stylesSet = polylineStylesSet;
            }
        }
    }

    public synchronized void changeMapPoints(List<MapPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                this.delegate.changePolylineStylesAndMapPoints(this.layerId, this.polylineId, this.stylesSet, list);
                this.mapPoints = new ArrayList(list);
            }
        }
        throw new RuntimeException("Polygon changeMapPoints failure. MapPoints is invalid.");
    }

    public synchronized void changeDotPoints(List<DotPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                this.delegate.changePolylineStylesAndDotPoints(this.layerId, this.polylineId, this.stylesSet, list);
                this.dotPoints = new ArrayList(list);
            }
        }
        throw new RuntimeException("Polygon changeDotPoints failure. DotPoints is invalid.");
    }

    public synchronized void changeStylesAndMapPoints(PolylineStylesSet polylineStylesSet, List<MapPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                if (polylineStylesSet == null) {
                    throw new RuntimeException("Polyline changeStylesAndMapPoints failure. PolylineStylesSet is invalid.");
                }
                this.delegate.changePolylineStylesAndMapPoints(this.layerId, this.polylineId, polylineStylesSet, list);
                this.mapPoints = new ArrayList(list);
                this.stylesSet = polylineStylesSet;
            }
        }
        throw new RuntimeException("Polyline changeStylesAndMapPoints failure. MapPoints is invalid.");
    }

    public synchronized void changeStylesAndDotPoints(PolylineStylesSet polylineStylesSet, List<DotPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                if (polylineStylesSet == null) {
                    throw new RuntimeException("Polyline changeStylesAndDotPoints failure. PolylineStylesSet is invalid.");
                }
                this.delegate.changePolylineStylesAndDotPoints(this.layerId, this.polylineId, polylineStylesSet, list);
                this.dotPoints = new ArrayList(list);
                this.stylesSet = polylineStylesSet;
            }
        }
        throw new RuntimeException("Polyline changeStylesAndDotPoints failure. MapPoints is invalid.");
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public synchronized PolylineStylesSet getStylesSet() {
        return this.stylesSet;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }
}

package com.kakao.vectormap.shape;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IShape;
import com.kakao.vectormap.internal.IVectorDelegate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Polygon extends IShape {
    private final IVectorDelegate delegate;
    private List<DotPoints> dotPoints;
    private final String layerId;
    private List<MapPoints> mapPoints;
    private final String shapeId;
    private PolygonStylesSet stylesSet;
    private Object tag;
    private final boolean toDimScreen;
    private int zOrder;

    Polygon(IVectorDelegate iVectorDelegate, String str, String str2, boolean z, int i, boolean z2, List<MapPoints> list, List<DotPoints> list2, PolygonStylesSet polygonStylesSet, Object obj) {
        super(z);
        this.delegate = iVectorDelegate;
        this.layerId = str;
        this.shapeId = str2;
        this.zOrder = i;
        this.toDimScreen = z2;
        this.mapPoints = new ArrayList(list);
        this.dotPoints = new ArrayList(list2);
        this.stylesSet = polygonStylesSet;
        this.tag = obj;
    }

    public synchronized void show() {
        try {
            this.delegate.setPolygonVisible(this.toDimScreen, this.layerId, this.shapeId, true);
            setVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hide() {
        try {
            this.delegate.setPolygonVisible(this.toDimScreen, this.layerId, this.shapeId, false);
            setVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setPosition(LatLng latLng) {
        try {
            this.delegate.setPolygonCenterPoint(this.layerId, this.shapeId, latLng);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setZOrder(int i) {
        try {
            this.delegate.setPolygonZOrder(this.layerId, this.shapeId, i, this.toDimScreen);
            this.zOrder = i;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized int getZOrder() {
        return this.zOrder;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public String getId() {
        return this.shapeId;
    }

    public synchronized boolean isShow() {
        return isVisible();
    }

    public boolean toDimScreen() {
        return this.toDimScreen;
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    public synchronized void remove() {
        try {
            this.delegate.removePolygon(this.toDimScreen, getLayerId(), getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void changeMapPoints(List<MapPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                this.delegate.changePolygonStylesAndMapPoints(this.layerId, this.shapeId, this.stylesSet, list, this.toDimScreen);
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
                this.delegate.changePolygonStylesAndDotPoints(this.layerId, this.shapeId, this.stylesSet, list, this.toDimScreen);
                this.dotPoints = new ArrayList(list);
            }
        }
        throw new RuntimeException("Polygon changeDotPoints failure. DotPoints is invalid.");
    }

    public synchronized void changeStylesSet(PolygonStylesSet polygonStylesSet) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (polygonStylesSet == null) {
            throw new RuntimeException("Polygon changeStylesSet failure. PolygonStylesSet is invalid.");
        }
        if (!this.dotPoints.isEmpty()) {
            this.delegate.changePolygonStylesAndDotPoints(this.layerId, this.shapeId, polygonStylesSet, this.dotPoints, this.toDimScreen);
            this.stylesSet = polygonStylesSet;
        } else {
            if (!this.mapPoints.isEmpty()) {
                this.delegate.changePolygonStylesAndMapPoints(this.layerId, this.shapeId, polygonStylesSet, this.mapPoints, this.toDimScreen);
                this.stylesSet = polygonStylesSet;
            }
        }
    }

    public synchronized void changeStylesAndMapPoints(PolygonStylesSet polygonStylesSet, List<MapPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                if (polygonStylesSet == null) {
                    throw new RuntimeException("Polygon changeStylesAndMapPoints failure. PolygonStylesSet is invalid.");
                }
                this.delegate.changePolygonStylesAndMapPoints(this.layerId, this.shapeId, polygonStylesSet, list, this.toDimScreen);
                this.mapPoints = new ArrayList(list);
                this.stylesSet = polygonStylesSet;
            }
        }
        throw new RuntimeException("Polygon changeStylesAndMapPoints failure. MapPoints is invalid.");
    }

    public synchronized void changeStylesAndDotPoints(PolygonStylesSet polygonStylesSet, List<DotPoints> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                if (polygonStylesSet == null) {
                    throw new RuntimeException("Polygon changeStylesAndDotPoints failure. PolygonStylesSet is invalid.");
                }
                this.delegate.changePolygonStylesAndDotPoints(this.layerId, this.shapeId, polygonStylesSet, list, this.toDimScreen);
                this.dotPoints = new ArrayList(list);
                this.stylesSet = polygonStylesSet;
            }
        }
        throw new RuntimeException("Polygon changeStylesAndDotPoints failure. DotPoints is invalid.");
    }

    public synchronized List<DotPoints> getDotPoints() {
        return new ArrayList(this.dotPoints);
    }

    public synchronized List<MapPoints> getMapPoints() {
        return new ArrayList(this.mapPoints);
    }

    public synchronized PolygonStylesSet getStylesSet() {
        return this.stylesSet;
    }
}

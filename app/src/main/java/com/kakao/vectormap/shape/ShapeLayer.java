package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IShapeContainer;
import com.kakao.vectormap.internal.IShapeDelegate;
import com.kakao.vectormap.internal.IShapeFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ShapeLayer extends IShapeContainer {
    private ShapeLayerPass passType;
    private Object tag;
    private boolean visible;

    public interface OnPolygonCreateCallback {
        void onPolygonCreated(ShapeLayer shapeLayer, Polygon... polygonArr);
    }

    public interface OnPolylineCreateCallback {
        void onPolylineCreated(ShapeLayer shapeLayer, Polyline... polylineArr);
    }

    ShapeLayer(IShapeDelegate iShapeDelegate, int i, String str, boolean z, boolean z2, ShapeLayerPass shapeLayerPass, IShapeFactory iShapeFactory) {
        super(iShapeDelegate, i, str, iShapeFactory);
        this.visible = z;
        this.isDimScreen = z2;
        this.passType = shapeLayerPass;
    }

    public String getLayerId() {
        return this.layerId;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    private void checkPolygonOptions(PolygonOptions... polygonOptionsArr) throws RuntimeException {
        checkValidate();
        if (polygonOptionsArr == null || polygonOptionsArr.length == 0) {
            throw new RuntimeException("addPolygon failure. PolygonOptions is null or empty.");
        }
    }

    private void checkPolylineOptions(PolylineOptions polylineOptions) throws RuntimeException {
        checkValidate();
        if (polylineOptions == null) {
            throw new RuntimeException("addPolyline failure. PolylineOptions is null.");
        }
        if ((polylineOptions.getDotPoints() == null || polylineOptions.getDotPoints().isEmpty()) && (polylineOptions.getMapPoints() == null || polylineOptions.getMapPoints().isEmpty())) {
            throw new RuntimeException("addPolyline failure. Points is invalid.");
        }
        if (polylineOptions.getStylesSet() == null) {
            throw new RuntimeException("addPolyline failure. PolylineStylesSet is null.");
        }
    }

    public synchronized Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            checkPolygonOptions(polygonOptions);
            if (this.polygonMap.containsKey(polygonOptions.getPolygonId())) {
                return this.polygonMap.get(polygonOptions.getPolygonId());
            }
            this.delegate.addMultiPolygon(this, polygonOptions, this.isDimScreen, null);
            return newPolygon(polygonOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addPolygon(PolygonOptions polygonOptions, OnPolygonCreateCallback onPolygonCreateCallback) {
        try {
            checkPolygonOptions(polygonOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (!this.polygonMap.containsKey(polygonOptions.getPolygonId())) {
            this.delegate.addMultiPolygon(this, polygonOptions, this.isDimScreen, onPolygonCreateCallback);
        } else {
            if (onPolygonCreateCallback != null) {
                onPolygonCreateCallback.onPolygonCreated(this, this.polygonMap.get(polygonOptions.getPolygonId()));
            }
        }
    }

    public synchronized Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            checkPolylineOptions(polylineOptions);
            if (this.polylineMap.containsKey(polylineOptions.getPolylineId())) {
                return this.polylineMap.get(polylineOptions.getPolylineId());
            }
            this.delegate.addPolyline(this, polylineOptions, this.isDimScreen, null);
            return newPolygon(polylineOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addPolyline(PolylineOptions polylineOptions, OnPolylineCreateCallback onPolylineCreateCallback) {
        try {
            checkPolylineOptions(polylineOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (!this.polylineMap.containsKey(polylineOptions.getPolylineId())) {
            this.delegate.addPolyline(this, polylineOptions, this.isDimScreen, onPolylineCreateCallback);
        } else {
            if (onPolylineCreateCallback != null) {
                onPolylineCreateCallback.onPolylineCreated(this, this.polylineMap.get(polylineOptions.getPolylineId()));
            }
        }
    }

    public synchronized void setVisible(boolean z) {
        try {
            checkValidate();
            this.delegate.setLayerVisible(this.layerId, z);
            this.visible = z;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized boolean isVisible() {
        return this.visible;
    }

    public synchronized void remove(Polygon polygon) {
        try {
            checkValidate();
            this.delegate.removePolygon(polygon.toDimScreen(), this.layerId, polygon.getId());
            removePolygonCallback(polygon.getId());
            this.polygonMap.remove(polygon.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(Polyline polyline) {
        try {
            checkValidate();
            this.delegate.removePolyline(this.isDimScreen, this.layerId, polyline.getId());
            removePolylineCallback(polyline.getId());
            this.polylineMap.remove(polyline.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAll() {
        try {
            checkValidate();
            this.delegate.removeAllShape(this.isDimScreen, this.layerId);
            removeAllCallback();
            this.polygonMap.clear();
            this.polylineMap.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showAllPolygon() {
        try {
            checkValidate();
            this.delegate.setAllPolygonVisible(this.layerId, true);
            setAllPolygonVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void hideAllPolygon() {
        try {
            checkValidate();
            this.delegate.setAllPolygonVisible(this.layerId, false);
            setAllPolygonVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void showAllPolyline() {
        try {
            checkValidate();
            this.delegate.setAllPolylineVisible(this.layerId, true);
            setAllPolylineVisible(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public ShapeLayerPass getPassType() {
        return this.passType;
    }

    public synchronized void hideAllPolyline() {
        try {
            checkValidate();
            this.delegate.setAllPolylineVisible(this.layerId, false);
            setAllPolylineVisible(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Polygon getPolygon(String str) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.polygonMap.get(str);
    }

    public synchronized Polygon[] getAllPolygons() {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return (Polygon[]) this.polygonMap.values().toArray(new Polygon[this.polygonMap.size()]);
    }

    public synchronized Polyline getPolyline(String str) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.polylineMap.get(str);
    }

    public synchronized Polyline[] getAllPolylines() {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return (Polyline[]) this.polylineMap.values().toArray(new Polyline[this.polylineMap.size()]);
    }

    public synchronized int getPolygonCount() {
        return this.polygonMap.size();
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    protected void checkValidate() throws RuntimeException {
        if (!this.delegate.hasLayer(this.layerId)) {
            throw new RuntimeException("ShapeLayer(id=" + this.layerId + ") is removed. ShapeLayer must be added first.");
        }
    }
}

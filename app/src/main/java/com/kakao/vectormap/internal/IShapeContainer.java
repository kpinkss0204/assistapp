package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.shape.Polyline;
import com.kakao.vectormap.shape.PolylineOptions;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.utils.MapUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class IShapeContainer {
    protected final IShapeDelegate delegate;
    private IShapeFactory factory;
    protected boolean isDimScreen;
    protected final String layerId;
    protected final int zOrder;
    protected Map<String, Polygon> polygonMap = new ConcurrentHashMap();
    protected Map<String, Polyline> polylineMap = new ConcurrentHashMap();
    private Map<String, Pair<ShapeLayer.OnPolygonCreateCallback, PolygonOptions>> polygonCallback = new ConcurrentHashMap();
    private Map<String, Pair<ShapeLayer.OnPolylineCreateCallback, PolylineOptions>> polylineCallback = new ConcurrentHashMap();
    private Map<String, String> prePolygons = new ConcurrentHashMap();
    private Map<String, String> prePolylines = new ConcurrentHashMap();

    public IShapeContainer(IShapeDelegate iShapeDelegate, int i, String str, IShapeFactory iShapeFactory) {
        this.delegate = iShapeDelegate;
        this.zOrder = i;
        this.layerId = str;
        this.factory = iShapeFactory;
    }

    protected synchronized void removeAllCallback() {
        this.polygonCallback.clear();
        this.polylineCallback.clear();
        this.prePolygons.clear();
        this.prePolylines.clear();
    }

    protected synchronized void removePolygonCallback(String str) {
        String strRemove = this.prePolygons.remove(str);
        if (strRemove != null) {
            this.polygonCallback.remove(strRemove);
        }
    }

    protected synchronized void removePolylineCallback(String str) {
        String strRemove = this.prePolylines.remove(str);
        if (strRemove != null) {
            this.polylineCallback.remove(strRemove);
        }
    }

    protected synchronized void setAllPolygonVisible(boolean z) {
        for (Polygon polygon : this.polygonMap.values()) {
            if (polygon != null) {
                polygon.setVisible(z);
            }
        }
    }

    protected synchronized void setAllPolylineVisible(boolean z) {
        for (Polyline polyline : this.polylineMap.values()) {
            if (polyline != null) {
                polyline.setVisible(z);
            }
        }
    }

    synchronized String addCallback(ShapeLayer.OnPolygonCreateCallback onPolygonCreateCallback, PolygonOptions polygonOptions) {
        if (onPolygonCreateCallback == null) {
            return "";
        }
        String uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.polygonCallback.put(uniqueId, new Pair<>(onPolygonCreateCallback, polygonOptions));
        this.prePolygons.put(polygonOptions.getPolygonId(), uniqueId);
        return uniqueId;
    }

    synchronized String addCallback(ShapeLayer.OnPolylineCreateCallback onPolylineCreateCallback, PolylineOptions polylineOptions) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.polylineCallback.put(uniqueId, new Pair<>(onPolylineCreateCallback, polylineOptions));
        this.prePolylines.put(polylineOptions.getPolylineId(), uniqueId);
        return uniqueId;
    }

    protected synchronized Polygon newPolygon(PolygonOptions polygonOptions) {
        Polygon polygonNewPolygon;
        polygonNewPolygon = this.factory.newPolygon(this.delegate, this.layerId, polygonOptions.getPolygonId(), polygonOptions, this.isDimScreen);
        this.polygonMap.put(polygonNewPolygon.getId(), polygonNewPolygon);
        return polygonNewPolygon;
    }

    synchronized Pair<ShapeLayer.OnPolygonCreateCallback, Polygon> getPolygon(String str) {
        if (!this.polygonCallback.containsKey(str)) {
            return null;
        }
        Pair<ShapeLayer.OnPolygonCreateCallback, PolygonOptions> pairRemove = this.polygonCallback.remove(str);
        String polygonId = ((PolygonOptions) pairRemove.second).getPolygonId();
        if (!this.polygonMap.containsKey(((PolygonOptions) pairRemove.second).getPolygonId())) {
            Polygon polygonNewPolygon = this.factory.newPolygon(this.delegate, this.layerId, polygonId, (PolygonOptions) pairRemove.second, this.isDimScreen);
            this.polygonMap.put(polygonNewPolygon.getId(), polygonNewPolygon);
        }
        this.prePolygons.remove(polygonId);
        return new Pair<>((ShapeLayer.OnPolygonCreateCallback) pairRemove.first, this.polygonMap.get(polygonId));
    }

    protected synchronized Polyline newPolygon(PolylineOptions polylineOptions) {
        Polyline polylineNewPolyline;
        polylineNewPolyline = this.factory.newPolyline(this.delegate, this.layerId, polylineOptions.getPolylineId(), polylineOptions, this.isDimScreen);
        this.polylineMap.put(polylineNewPolyline.getId(), polylineNewPolyline);
        return polylineNewPolyline;
    }

    synchronized Pair<ShapeLayer.OnPolylineCreateCallback, Polyline> getPolyline(String str) {
        if (!this.polylineCallback.containsKey(str)) {
            return null;
        }
        Pair<ShapeLayer.OnPolylineCreateCallback, PolylineOptions> pairRemove = this.polylineCallback.remove(str);
        String polylineId = ((PolylineOptions) pairRemove.second).getPolylineId();
        if (!this.polylineMap.containsKey(((PolylineOptions) pairRemove.second).getPolylineId())) {
            Polyline polylineNewPolyline = this.factory.newPolyline(this.delegate, this.layerId, polylineId, (PolylineOptions) pairRemove.second, this.isDimScreen);
            this.polylineMap.put(polylineNewPolyline.getId(), polylineNewPolyline);
        }
        this.prePolylines.remove(polylineId);
        return new Pair<>((ShapeLayer.OnPolylineCreateCallback) pairRemove.first, this.polylineMap.get(polylineId));
    }
}

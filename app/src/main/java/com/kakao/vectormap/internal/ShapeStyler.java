package com.kakao.vectormap.internal;

import com.kakao.vectormap.shape.PolygonStylesSet;
import com.kakao.vectormap.shape.PolylineStylesSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
class ShapeStyler {
    private Map<String, PolygonStylesSet> lightModePolygonStyles = new ConcurrentHashMap();
    private Map<String, PolygonStylesSet> darkModePolygonStyles = new ConcurrentHashMap();
    private Map<String, PolylineStylesSet> lightModePolylineStyles = new ConcurrentHashMap();
    private Map<String, PolylineStylesSet> darkModePolylineStyles = new ConcurrentHashMap();

    boolean hasDarkPolygonStyles(String str) {
        return this.darkModePolygonStyles.containsKey(str);
    }

    PolygonStylesSet getDarkPolygonStyles(String str) {
        return this.darkModePolygonStyles.get(str);
    }

    PolygonStylesSet addDarkPolygonStyles(PolygonStylesSet polygonStylesSet) {
        this.darkModePolygonStyles.put(polygonStylesSet.getStyleId(), polygonStylesSet);
        return this.darkModePolygonStyles.get(polygonStylesSet.getStyleId());
    }

    boolean hasLightPolygonStyles(String str) {
        return this.lightModePolygonStyles.containsKey(str);
    }

    PolygonStylesSet getLightPolygonStyles(String str) {
        return this.lightModePolygonStyles.get(str);
    }

    PolygonStylesSet addLightPolygonStyles(PolygonStylesSet polygonStylesSet) {
        this.lightModePolygonStyles.put(polygonStylesSet.getStyleId(), polygonStylesSet);
        return this.lightModePolygonStyles.get(polygonStylesSet.getStyleId());
    }

    boolean hasDarkPolylineStyles(String str) {
        return this.darkModePolylineStyles.containsKey(str);
    }

    PolylineStylesSet getDarkPolylineStyles(String str) {
        return this.darkModePolylineStyles.get(str);
    }

    PolylineStylesSet addDarkPolylineStyles(PolylineStylesSet polylineStylesSet) {
        this.darkModePolylineStyles.put(polylineStylesSet.getStyleId(), polylineStylesSet);
        return this.darkModePolylineStyles.get(polylineStylesSet.getStyleId());
    }

    boolean hasLightPolylineStyles(String str) {
        return this.lightModePolylineStyles.containsKey(str);
    }

    PolylineStylesSet getLightPolylineStyles(String str) {
        return this.lightModePolylineStyles.get(str);
    }

    PolylineStylesSet addLightPolylineStyles(PolylineStylesSet polylineStylesSet) {
        this.lightModePolylineStyles.put(polylineStylesSet.getStyleId(), polylineStylesSet);
        return this.lightModePolylineStyles.get(polylineStylesSet.getStyleId());
    }
}

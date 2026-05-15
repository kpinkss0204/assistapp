package com.kakao.vectormap.internal;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.shape.DotPoints;
import com.kakao.vectormap.shape.MapPoints;
import com.kakao.vectormap.shape.PolylineOptions;
import com.kakao.vectormap.shape.PolylineStylesSet;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.shape.ShapeLayerOptions;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IShapeDelegate extends IVectorDelegate {
    ShapeLayer addLayer(ShapeLayerOptions shapeLayerOptions) throws RuntimeException;

    void addPolyline(ShapeLayer shapeLayer, PolylineOptions polylineOptions, boolean z, ShapeLayer.OnPolylineCreateCallback onPolylineCreateCallback) throws RuntimeException;

    String addPolylineStylesSet(PolylineStylesSet polylineStylesSet) throws RuntimeException;

    void changePolylineStylesAndDotPoints(String str, String str2, PolylineStylesSet polylineStylesSet, List<DotPoints> list) throws RuntimeException;

    void changePolylineStylesAndMapPoints(String str, String str2, PolylineStylesSet polylineStylesSet, List<MapPoints> list) throws RuntimeException;

    ShapeLayer getLayer(String str) throws RuntimeException;

    PolylineStylesSet getPolylineStylesSet(String str) throws RuntimeException;

    boolean hasLayer(String str) throws RuntimeException;

    boolean hasPolylineStylesSet(String str) throws RuntimeException;

    void removeAllShape(boolean z, String str) throws RuntimeException;

    void removeLayer(String str) throws RuntimeException;

    void removePolyline(boolean z, String str, String str2) throws RuntimeException;

    void setAllPolygonVisible(String str, boolean z) throws RuntimeException;

    void setAllPolylineVisible(String str, boolean z) throws RuntimeException;

    void setLayerVisible(String str, boolean z) throws RuntimeException;

    void setPolylinePosition(String str, String str2, LatLng latLng) throws RuntimeException;

    void setPolylineVisible(boolean z, String str, String str2, boolean z2) throws RuntimeException;

    void setPolylineZOrder(String str, String str2, int i) throws RuntimeException;

    void setShapeFactory(IShapeFactory iShapeFactory);
}

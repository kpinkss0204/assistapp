package com.kakao.vectormap.internal;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.shape.DimScreenCover;
import com.kakao.vectormap.shape.DotPoints;
import com.kakao.vectormap.shape.MapPoints;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.shape.PolygonStylesSet;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.shape.animation.CircleWaves;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IVectorDelegate {
    void addCircleWaveAnimator(boolean z, CircleWaves circleWaves) throws RuntimeException;

    void addMultiPolygon(ShapeLayer shapeLayer, PolygonOptions polygonOptions, boolean z, ShapeLayer.OnPolygonCreateCallback onPolygonCreateCallback) throws RuntimeException;

    String addPolygonStylesSet(PolygonStylesSet polygonStylesSet, boolean z) throws RuntimeException;

    void changePolygonStylesAndDotPoints(String str, String str2, PolygonStylesSet polygonStylesSet, List<DotPoints> list, boolean z) throws RuntimeException;

    void changePolygonStylesAndMapPoints(String str, String str2, PolygonStylesSet polygonStylesSet, List<MapPoints> list, boolean z) throws RuntimeException;

    void clearAllAnimator(boolean z) throws RuntimeException;

    PolygonStylesSet getPolygonStylesSet(String str) throws RuntimeException;

    boolean hasPolygonStylesSet(String str) throws RuntimeException;

    void removeAllShape(boolean z) throws RuntimeException;

    void removeAnimator(boolean z, String str) throws RuntimeException;

    void removePolygon(boolean z, String str, String str2) throws RuntimeException;

    void removePolygons(String[] strArr, String[] strArr2, boolean z) throws RuntimeException;

    void setDimScreenCover(DimScreenCover dimScreenCover) throws RuntimeException;

    void setPolygonCenterPoint(String str, String str2, LatLng latLng) throws RuntimeException;

    void setPolygonVisible(boolean z, String str, String str2, boolean z2) throws RuntimeException;

    void setPolygonZOrder(String str, String str2, int i, boolean z) throws RuntimeException;

    void startAnimator(String str, boolean z, List<Polygon> list) throws RuntimeException;

    void stopAnimator(String str, boolean z, boolean z2) throws RuntimeException;
}

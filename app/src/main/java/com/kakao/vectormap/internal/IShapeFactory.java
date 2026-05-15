package com.kakao.vectormap.internal;

import com.kakao.vectormap.animation.Interpolation;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.shape.Polyline;
import com.kakao.vectormap.shape.PolylineOptions;
import com.kakao.vectormap.shape.ShapeAnimator;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.shape.ShapeLayerPass;

/* JADX INFO: loaded from: classes4.dex */
public interface IShapeFactory {
    ShapeAnimator newAnimator(IVectorDelegate iVectorDelegate, String str, boolean z, int i, int i2, boolean z2, Interpolation interpolation);

    ShapeLayer newLayer(IShapeDelegate iShapeDelegate, String str, int i, boolean z, boolean z2, ShapeLayerPass shapeLayerPass, IShapeFactory iShapeFactory);

    Polygon newPolygon(IVectorDelegate iVectorDelegate, String str, String str2, PolygonOptions polygonOptions, boolean z);

    Polyline newPolyline(IShapeDelegate iShapeDelegate, String str, String str2, PolylineOptions polylineOptions, boolean z);
}

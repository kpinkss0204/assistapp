package com.kakao.vectormap.shape;

import com.kakao.vectormap.animation.Interpolation;
import com.kakao.vectormap.internal.IShapeDelegate;
import com.kakao.vectormap.internal.IShapeFactory;
import com.kakao.vectormap.internal.IVectorDelegate;

/* JADX INFO: loaded from: classes4.dex */
class ShapeFactory implements IShapeFactory {
    ShapeFactory() {
    }

    @Override // com.kakao.vectormap.internal.IShapeFactory
    public Polygon newPolygon(IVectorDelegate iVectorDelegate, String str, String str2, PolygonOptions polygonOptions, boolean z) {
        return new Polygon(iVectorDelegate, str, str2, polygonOptions.isVisible(), polygonOptions.getZOrder(), z, polygonOptions.getMapPoints(), polygonOptions.getDotPoints(), polygonOptions.getStylesSet(), polygonOptions.getTag());
    }

    @Override // com.kakao.vectormap.internal.IShapeFactory
    public Polyline newPolyline(IShapeDelegate iShapeDelegate, String str, String str2, PolylineOptions polylineOptions, boolean z) {
        return new Polyline(iShapeDelegate, str, str2, polylineOptions.isVisible(), polylineOptions.getZOrder(), z, polylineOptions.getMapPoints(), polylineOptions.getDotPoints(), polylineOptions.getStylesSet(), polylineOptions.getTag());
    }

    @Override // com.kakao.vectormap.internal.IShapeFactory
    public ShapeLayer newLayer(IShapeDelegate iShapeDelegate, String str, int i, boolean z, boolean z2, ShapeLayerPass shapeLayerPass, IShapeFactory iShapeFactory) {
        return new ShapeLayer(iShapeDelegate, i, str, z, z2, shapeLayerPass, iShapeFactory);
    }

    @Override // com.kakao.vectormap.internal.IShapeFactory
    public ShapeAnimator newAnimator(IVectorDelegate iVectorDelegate, String str, boolean z, int i, int i2, boolean z2, Interpolation interpolation) {
        return new ShapeAnimator(iVectorDelegate, str, z, i, i2, z2, interpolation);
    }
}

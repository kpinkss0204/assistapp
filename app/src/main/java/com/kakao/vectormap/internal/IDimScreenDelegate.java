package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.shape.DimScreenLayer;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface IDimScreenDelegate extends IVectorDelegate {
    void addDotPointPolygons(DimScreenLayer.OnPolygonCreateCallback onPolygonCreateCallback, PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2) throws RuntimeException;

    void addMapPointPolygons(DimScreenLayer.OnPolygonCreateCallback onPolygonCreateCallback, PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2) throws RuntimeException;

    void clearAllPolygon();

    boolean containPolygon(String str);

    Polygon[] getAllPolygons() throws RuntimeException;

    Pair<DimScreenLayer.OnPolygonCreateCallback, PolygonOptions[]> getCallback(String str);

    DimScreenLayer getDimScreenLayer();

    Polygon getPolygon(String str) throws RuntimeException;

    Polygon newPolygon(PolygonOptions polygonOptions);

    Polygon[] newPolygons(PolygonOptions... polygonOptionsArr);

    void remove(String str);

    void setDimScreenColor(int i) throws RuntimeException;

    void setDimScreenLayer(DimScreenLayer dimScreenLayer);

    void setShapeFactory(IShapeFactory iShapeFactory);

    void setVisible(boolean z) throws RuntimeException;
}

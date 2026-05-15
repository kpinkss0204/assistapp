package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.internal.VectorDelegate;
import com.kakao.vectormap.shape.DimScreenLayer;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class DimScreenDelegate extends VectorDelegate implements IDimScreenDelegate {
    private Map<String, Pair<DimScreenLayer.OnPolygonCreateCallback, PolygonOptions[]>> callbackList;
    private DimScreenLayer dimScreenLayer;
    private Map<String, Polygon> polygonMap;
    private IShapeFactory shapeFactory;

    static native void setDimScreenColor(long j, String str, int i);

    DimScreenDelegate(long j, String str, MapResourceManager mapResourceManager) {
        super(j, str, mapResourceManager, new ShapeStyler());
        this.polygonMap = new ConcurrentHashMap();
        this.callbackList = new ConcurrentHashMap();
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public DimScreenLayer getDimScreenLayer() {
        return this.dimScreenLayer;
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void setDimScreenLayer(DimScreenLayer dimScreenLayer) {
        this.dimScreenLayer = dimScreenLayer;
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void setShapeFactory(IShapeFactory iShapeFactory) {
        this.shapeFactory = iShapeFactory;
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public boolean containPolygon(String str) {
        return this.polygonMap.containsKey(str);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public Polygon getPolygon(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.polygonMap.get(str);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public Polygon[] getAllPolygons() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return (Polygon[]) this.polygonMap.values().toArray(new Polygon[this.polygonMap.values().size()]);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public Polygon[] newPolygons(PolygonOptions... polygonOptionsArr) {
        DimScreenDelegate dimScreenDelegate;
        ArrayList arrayList = new ArrayList();
        for (PolygonOptions polygonOptions : polygonOptionsArr) {
            if (this.polygonMap.containsKey(polygonOptions.getPolygonId())) {
                dimScreenDelegate = this;
            } else {
                dimScreenDelegate = this;
                Polygon polygonNewPolygon = this.shapeFactory.newPolygon(dimScreenDelegate, "", polygonOptions.getPolygonId(), polygonOptions, true);
                dimScreenDelegate.polygonMap.put(polygonNewPolygon.getId(), polygonNewPolygon);
            }
            arrayList.add(dimScreenDelegate.polygonMap.get(polygonOptions.getPolygonId()));
        }
        return (Polygon[]) arrayList.toArray(new Polygon[arrayList.size()]);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public Polygon newPolygon(PolygonOptions polygonOptions) {
        DimScreenDelegate dimScreenDelegate;
        PolygonOptions polygonOptions2;
        if (this.polygonMap.containsKey(polygonOptions.getPolygonId())) {
            dimScreenDelegate = this;
            polygonOptions2 = polygonOptions;
        } else {
            dimScreenDelegate = this;
            polygonOptions2 = polygonOptions;
            Polygon polygonNewPolygon = this.shapeFactory.newPolygon(dimScreenDelegate, "", polygonOptions.getPolygonId(), polygonOptions2, true);
            dimScreenDelegate.polygonMap.put(polygonNewPolygon.getId(), polygonNewPolygon);
        }
        return dimScreenDelegate.polygonMap.get(polygonOptions2.getPolygonId());
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void remove(String str) {
        this.polygonMap.remove(str);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void clearAllPolygon() {
        this.polygonMap.clear();
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void addMapPointPolygons(DimScreenLayer.OnPolygonCreateCallback onPolygonCreateCallback, PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        VectorDelegate.PolygonData mapPolygonData = getMapPolygonData(polygonOptionsArr, polygonOptionsArr2, true);
        String uniqueId = onPolygonCreateCallback == null ? "" : MapUtils.getUniqueId();
        if (onPolygonCreateCallback != null) {
            this.callbackList.put(uniqueId, new Pair<>(onPolygonCreateCallback, polygonOptionsArr2));
        }
        addMultiLatLngPolygonsToDimScreen(this.appEngineHandle, this.viewName, mapPolygonData.styleIds, mapPolygonData.polygonIds, mapPolygonData.polygonCounts, mapPolygonData.mapVertices, mapPolygonData.styleIndexes, mapPolygonData.zOrders, mapPolygonData.visibles, uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void addDotPointPolygons(DimScreenLayer.OnPolygonCreateCallback onPolygonCreateCallback, PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String uniqueId = onPolygonCreateCallback == null ? "" : MapUtils.getUniqueId();
        VectorDelegate.PolygonData pointPolygonData = getPointPolygonData(polygonOptionsArr, polygonOptionsArr2, true);
        if (onPolygonCreateCallback != null) {
            this.callbackList.put(uniqueId, new Pair<>(onPolygonCreateCallback, polygonOptionsArr2));
        }
        addMultiPointPolygonsToDimScreen(this.appEngineHandle, this.viewName, pointPolygonData.styleIds, pointPolygonData.polygonIds, pointPolygonData.polygonCounts, pointPolygonData.lats, pointPolygonData.lngs, pointPolygonData.pointVertices, pointPolygonData.styleIndexes, pointPolygonData.zOrders, pointPolygonData.visibles, uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void setVisible(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setLayerVisible(this.appEngineHandle, this.viewName, "", z, true);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public void setDimScreenColor(int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setDimScreenColor(this.appEngineHandle, this.viewName, i);
    }

    @Override // com.kakao.vectormap.internal.IDimScreenDelegate
    public Pair<DimScreenLayer.OnPolygonCreateCallback, PolygonOptions[]> getCallback(String str) {
        return this.callbackList.remove(str);
    }
}

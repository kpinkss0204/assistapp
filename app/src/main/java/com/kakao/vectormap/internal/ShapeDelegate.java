package com.kakao.vectormap.internal;

import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.shape.DotPoints;
import com.kakao.vectormap.shape.LatLngVertex;
import com.kakao.vectormap.shape.MapPoints;
import com.kakao.vectormap.shape.PointVertex;
import com.kakao.vectormap.shape.PolylineOptions;
import com.kakao.vectormap.shape.PolylineStyle;
import com.kakao.vectormap.shape.PolylineStylesSet;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.shape.ShapeLayerOptions;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
class ShapeDelegate extends VectorDelegate implements IShapeDelegate {
    private IShapeFactory factory;
    private Map<String, ShapeLayer> layers;

    static native void addLayer(long j, String str, String str2, int i, boolean z, int i2);

    static native void addMultiLatLngPolyline(long j, String str, String str2, int i, int i2, String str3, String str4, int i3, LatLngVertex[] latLngVertexArr, int[] iArr, int i4, boolean z, String str5);

    static native void addMultiPointPolyline(long j, String str, String str2, int i, int i2, String str3, String str4, int i3, double d, double d2, PointVertex[] pointVertexArr, int[] iArr, int i4, boolean z, String str5);

    static native void addPolylineStyles(long j, String str, String str2, int i, int i2, PolylineStyle[][] polylineStyleArr);

    static native void changePolylineStylesAndDotPoints(long j, String str, String str2, String str3, String str4, int i, double d, double d2, PointVertex[] pointVertexArr, int[] iArr);

    static native void changePolylineStylesAndMapPoints(long j, String str, String str2, String str3, String str4, int i, LatLngVertex[] latLngVertexArr, int[] iArr);

    static native void removeAllShape(long j, String str, String str2, boolean z);

    static native void removeLayer(long j, String str, String str2);

    static native void removePolyline(long j, String str, String str2, String str3);

    static native void setPolylinePosition(long j, String str, String str2, String str3, double d, double d2);

    static native void setPolylineVisible(long j, String str, String str2, String str3, boolean z, boolean z2);

    static native void setPolylineZOrder(long j, String str, String str2, String str3, int i);

    static native void showAllPolygon(long j, String str, String str2, boolean z);

    static native void showAllPolyline(long j, String str, String str2, boolean z);

    ShapeDelegate(long j, String str, MapResourceManager mapResourceManager) {
        super(j, str, mapResourceManager, new ShapeStyler());
        this.layers = new ConcurrentHashMap();
    }

    @Override // com.kakao.vectormap.internal.VectorDelegate, com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
        this.appEngineHandle = 0L;
        this.viewName = null;
        this.factory = null;
        this.resourceManager = null;
        this.layers.clear();
        this.layers = null;
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public boolean hasPolylineStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.shapeStyler.hasDarkPolylineStyles(str);
        }
        return this.shapeStyler.hasLightPolylineStyles(str);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public String addPolylineStylesSet(PolylineStylesSet polylineStylesSet) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        polylineStylesSet.checkStyleId();
        if (this.resourceManager.isDarkMode()) {
            if (!this.shapeStyler.hasDarkPolylineStyles(polylineStylesSet.getStyleId())) {
                addPolylineStyles(this.appEngineHandle, this.viewName, polylineStylesSet.getStyleId() + "_dark", polylineStylesSet.getStylesCount(), polylineStylesSet.getPolylineCap().getValue(), toPolylineStyles(polylineStylesSet));
                this.shapeStyler.addDarkPolylineStyles(polylineStylesSet);
            }
            return polylineStylesSet.getStyleId() + "_dark";
        }
        if (!this.shapeStyler.hasLightPolylineStyles(polylineStylesSet.getStyleId())) {
            addPolylineStyles(this.appEngineHandle, this.viewName, polylineStylesSet.getStyleId(), polylineStylesSet.getStylesCount(), polylineStylesSet.getPolylineCap().getValue(), toPolylineStyles(polylineStylesSet));
            this.shapeStyler.addLightPolylineStyles(polylineStylesSet);
        }
        return polylineStylesSet.getStyleId();
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public PolylineStylesSet getPolylineStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.shapeStyler.getDarkPolylineStyles(str);
        }
        return this.shapeStyler.getLightPolylineStyles(str);
    }

    private PolylineStyle[][] toPolylineStyles(PolylineStylesSet polylineStylesSet) {
        PolylineStyle[][] polylineStyleArr = new PolylineStyle[polylineStylesSet.getStylesCount()][];
        for (int i = 0; i < polylineStylesSet.getStylesCount(); i++) {
            polylineStyleArr[i] = polylineStylesSet.getStyles().get(i).getStyles();
        }
        return polylineStyleArr;
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void setShapeFactory(IShapeFactory iShapeFactory) {
        if (this.factory == null) {
            this.factory = iShapeFactory;
        }
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized boolean hasLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layers.containsKey(str);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized ShapeLayer addLayer(ShapeLayerOptions shapeLayerOptions) throws Throwable {
        try {
            try {
                if (!isRunning()) {
                    throw new RuntimeException(Const.UnInitialized);
                }
                addLayer(this.appEngineHandle, this.viewName, shapeLayerOptions.getLayerId(), shapeLayerOptions.getZOrder(), shapeLayerOptions.isVisible(), shapeLayerOptions.getPassType().getValue());
                this.layers.put(shapeLayerOptions.getLayerId(), this.factory.newLayer(this, shapeLayerOptions.getLayerId(), shapeLayerOptions.getZOrder(), shapeLayerOptions.isVisible(), false, shapeLayerOptions.getPassType(), this.factory));
                return this.layers.get(shapeLayerOptions.getLayerId());
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        throw th;
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void addPolyline(ShapeLayer shapeLayer, PolylineOptions polylineOptions, boolean z, ShapeLayer.OnPolylineCreateCallback onPolylineCreateCallback) throws RuntimeException {
        String str;
        String str2;
        int i;
        int value;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (shapeLayer == null) {
            str = "";
            str2 = str;
            i = 0;
            value = 0;
        } else {
            String layerId = shapeLayer.getLayerId();
            String strAddCallback = shapeLayer.addCallback(onPolylineCreateCallback, polylineOptions);
            int zOrder = shapeLayer.getZOrder();
            value = shapeLayer.getPassType().getValue();
            str2 = strAddCallback;
            str = layerId;
            i = zOrder;
        }
        if (polylineOptions.getDotPoints().size() > 0) {
            addMultiDotPointPolyline(str, str2, i, value, polylineOptions, z);
        } else {
            addMultiMapPointPolyline(str, str2, i, value, polylineOptions, z);
        }
    }

    private void addMultiDotPointPolyline(String str, String str2, int i, int i2, PolylineOptions polylineOptions, boolean z) throws RuntimeException {
        List<DotPoints> dotPoints = polylineOptions.getDotPoints();
        LatLng basePosition = dotPoints.get(0).getBasePosition();
        if (basePosition == null) {
            throw new RuntimeException("addMultiPolyline failed. PolylineOptions BasePosition is null.");
        }
        double latitude = basePosition.getLatitude();
        double longitude = basePosition.getLongitude();
        int stylesCount = polylineOptions.getStylesSet().getStylesCount();
        String strAddPolylineStylesSet = addPolylineStylesSet(polylineOptions.getStylesSet());
        int size = dotPoints.size();
        int[] iArr = new int[size];
        PointVertex[] pointVertexArr = new PointVertex[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = Math.min(i3, stylesCount - 1);
            pointVertexArr[i3] = dotPoints.get(i3).getPoints();
        }
        addMultiPointPolyline(this.appEngineHandle, this.viewName, str, i, i2, strAddPolylineStylesSet, polylineOptions.getPolylineId(), size, latitude, longitude, pointVertexArr, iArr, polylineOptions.getZOrder(), polylineOptions.isVisible(), str2);
    }

    private void addMultiMapPointPolyline(String str, String str2, int i, int i2, PolylineOptions polylineOptions, boolean z) throws RuntimeException {
        int stylesCount = polylineOptions.getStylesSet().getStylesCount();
        String strAddPolylineStylesSet = addPolylineStylesSet(polylineOptions.getStylesSet());
        List<MapPoints> mapPoints = polylineOptions.getMapPoints();
        int size = mapPoints.size();
        int[] iArr = new int[size];
        LatLngVertex[] latLngVertexArr = new LatLngVertex[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = Math.min(i3, stylesCount - 1);
            latLngVertexArr[i3] = mapPoints.get(i3).getPoints();
        }
        addMultiLatLngPolyline(this.appEngineHandle, this.viewName, str, i, i2, strAddPolylineStylesSet, polylineOptions.getPolylineId(), size, latLngVertexArr, iArr, polylineOptions.getZOrder(), polylineOptions.isVisible(), str2);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized void setLayerVisible(String str, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setLayerVisible(this.appEngineHandle, this.viewName, str, z, false);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized void setPolylineVisible(boolean z, String str, String str2, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolylineVisible(this.appEngineHandle, this.viewName, str, str2, z2, z);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized ShapeLayer getLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layers.get(str);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized void removePolyline(boolean z, String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removePolyline(this.appEngineHandle, this.viewName, str, str2);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized void removeAllShape(boolean z, String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllShape(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public synchronized void removeLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeLayer(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void setAllPolygonVisible(String str, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        showAllPolygon(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void setAllPolylineVisible(String str, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        showAllPolygon(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void setPolylineZOrder(String str, String str2, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolylineZOrder(this.appEngineHandle, this.viewName, str, str2, i);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void setPolylinePosition(String str, String str2, LatLng latLng) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolylinePosition(this.appEngineHandle, this.viewName, str, str2, latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void changePolylineStylesAndMapPoints(String str, String str2, PolylineStylesSet polylineStylesSet, List<MapPoints> list) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int stylesCount = polylineStylesSet.getStylesCount();
        String strAddPolylineStylesSet = addPolylineStylesSet(polylineStylesSet);
        int size = list.size();
        int[] iArr = new int[size];
        LatLngVertex[] latLngVertexArr = new LatLngVertex[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = Math.min(i, stylesCount - 1);
            latLngVertexArr[i] = list.get(i).getPoints();
        }
        changePolylineStylesAndMapPoints(this.appEngineHandle, this.viewName, str, strAddPolylineStylesSet, str2, size, latLngVertexArr, iArr);
    }

    @Override // com.kakao.vectormap.internal.IShapeDelegate
    public void changePolylineStylesAndDotPoints(String str, String str2, PolylineStylesSet polylineStylesSet, List<DotPoints> list) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LatLng basePosition = list.get(0).getBasePosition();
        if (basePosition == null) {
            throw new RuntimeException("addMultiPolyline failed. PolylineOptions BasePosition is null.");
        }
        double latitude = basePosition.getLatitude();
        double longitude = basePosition.getLongitude();
        int stylesCount = polylineStylesSet.getStylesCount();
        String strAddPolylineStylesSet = addPolylineStylesSet(polylineStylesSet);
        int size = list.size();
        int[] iArr = new int[size];
        PointVertex[] pointVertexArr = new PointVertex[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = Math.min(i, stylesCount - 1);
            pointVertexArr[i] = list.get(i).getPoints();
        }
        changePolylineStylesAndDotPoints(this.appEngineHandle, this.viewName, str, strAddPolylineStylesSet, str2, size, latitude, longitude, pointVertexArr, iArr);
    }
}

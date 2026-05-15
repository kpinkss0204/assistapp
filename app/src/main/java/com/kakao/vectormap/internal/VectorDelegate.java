package com.kakao.vectormap.internal;

import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.shape.DimScreenCover;
import com.kakao.vectormap.shape.DotPoints;
import com.kakao.vectormap.shape.LatLngVertex;
import com.kakao.vectormap.shape.MapPoints;
import com.kakao.vectormap.shape.PointVertex;
import com.kakao.vectormap.shape.Polygon;
import com.kakao.vectormap.shape.PolygonOptions;
import com.kakao.vectormap.shape.PolygonStyle;
import com.kakao.vectormap.shape.PolygonStylesSet;
import com.kakao.vectormap.shape.ShapeLayer;
import com.kakao.vectormap.shape.animation.CircleWave;
import com.kakao.vectormap.shape.animation.CircleWaves;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VectorDelegate extends Destroyable implements IVectorDelegate {
    protected final String DARK_STYLE_POSTFIX = "_dark";
    protected long appEngineHandle;
    protected MapResourceManager resourceManager;
    protected ShapeStyler shapeStyler;
    protected String viewName;

    static native void addAnimator(long j, String str, String str2, int i, int i2, int i3, boolean z, int i4, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, boolean z2);

    static native void addMultiLatLngPolygon(long j, String str, String str2, int i, int i2, String str3, String str4, int i3, LatLngVertex[] latLngVertexArr, int[] iArr, int i4, boolean z, boolean z2, String str5);

    static native void addMultiLatLngPolygonsToDimScreen(long j, String str, String[] strArr, String[] strArr2, int[] iArr, LatLngVertex[][] latLngVertexArr, int[][] iArr2, int[] iArr3, boolean[] zArr, String str2);

    static native void addMultiPointPolygon(long j, String str, String str2, int i, int i2, String str3, String str4, int i3, double d, double d2, PointVertex[] pointVertexArr, int[] iArr, int i4, boolean z, boolean z2, String str5);

    static native void addMultiPointPolygonsToDimScreen(long j, String str, String[] strArr, String[] strArr2, int[] iArr, double[] dArr, double[] dArr2, PointVertex[][] pointVertexArr, int[][] iArr2, int[] iArr3, boolean[] zArr, String str2);

    static native void addPolygonStyles(long j, String str, String str2, int i, boolean z, PolygonStyle[][] polygonStyleArr);

    static native void changeStylesAndDotPoints(long j, String str, String str2, String str3, String str4, int i, double d, double d2, PointVertex[] pointVertexArr, int[] iArr, boolean z);

    static native void changeStylesAndMapPoints(long j, String str, String str2, String str3, String str4, int i, LatLngVertex[] latLngVertexArr, int[] iArr, boolean z);

    static native void clearAll(long j, String str, boolean z);

    static native void clearAllAnimator(long j, String str, boolean z);

    static native void nativeInit();

    static native void removeAnimator(long j, String str, String str2, boolean z);

    static native void removePolygon(long j, String str, String str2, String str3, boolean z);

    static native void removePolygons(long j, String str, int i, String[] strArr, String[] strArr2, boolean z);

    static native void setDimScreenEffectZOrder(long j, String str, int i);

    static native void setLayerVisible(long j, String str, String str2, boolean z, boolean z2);

    static native void setPolygonCenterPoint(long j, String str, String str2, String str3, double d, double d2);

    static native void setPolygonVisible(long j, String str, String str2, String str3, boolean z, boolean z2);

    static native void setPolygonZOrder(long j, String str, String str2, String str3, int i, boolean z);

    static native void startAnimator(long j, String str, String str2, boolean z, int i, String[] strArr, String[] strArr2);

    static native void stopAnimator(long j, String str, String str2, boolean z, boolean z2);

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ void setRunning(boolean z) {
        super.setRunning(z);
    }

    static {
        nativeInit();
    }

    static class PolygonData {
        double[] lats;
        double[] lngs;
        LatLngVertex[][] mapVertices;
        PointVertex[][] pointVertices;
        int[] polygonCounts;
        String[] polygonIds;
        String[] styleIds;
        int[][] styleIndexes;
        boolean[] visibles;
        int[] zOrders;

        PolygonData() {
        }
    }

    VectorDelegate(long j, String str, MapResourceManager mapResourceManager, ShapeStyler shapeStyler) {
        this.appEngineHandle = j;
        this.viewName = str;
        this.resourceManager = mapResourceManager;
        this.shapeStyler = shapeStyler;
    }

    private PolygonStyle[][] toPolygonStyles(PolygonStylesSet polygonStylesSet) {
        PolygonStyle[][] polygonStyleArr = new PolygonStyle[polygonStylesSet.getStylesCount()][];
        for (int i = 0; i < polygonStylesSet.getStylesCount(); i++) {
            polygonStyleArr[i] = polygonStylesSet.getStyles().get(i).getStyles();
        }
        return polygonStyleArr;
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public boolean hasPolygonStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.shapeStyler.hasDarkPolygonStyles(str);
        }
        return this.shapeStyler.hasLightPolygonStyles(str);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public String addPolygonStylesSet(PolygonStylesSet polygonStylesSet, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String styleId = polygonStylesSet.getStyleId();
        if (this.resourceManager.isDarkMode()) {
            if (!this.shapeStyler.hasDarkPolygonStyles(styleId)) {
                addPolygonStyles(this.appEngineHandle, this.viewName, styleId + "_dark", polygonStylesSet.getStylesCount(), z, toPolygonStyles(polygonStylesSet));
                this.shapeStyler.addDarkPolygonStyles(polygonStylesSet);
            }
            return styleId + "_dark";
        }
        if (!this.shapeStyler.hasLightPolygonStyles(styleId)) {
            addPolygonStyles(this.appEngineHandle, this.viewName, styleId, polygonStylesSet.getStylesCount(), z, toPolygonStyles(polygonStylesSet));
            this.shapeStyler.addLightPolygonStyles(polygonStylesSet);
        }
        return styleId;
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public PolygonStylesSet getPolygonStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.shapeStyler.getDarkPolygonStyles(str);
        }
        return this.shapeStyler.getLightPolygonStyles(str);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void addMultiPolygon(ShapeLayer shapeLayer, PolygonOptions polygonOptions, boolean z, ShapeLayer.OnPolygonCreateCallback onPolygonCreateCallback) throws RuntimeException {
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
            String strAddCallback = shapeLayer.addCallback(onPolygonCreateCallback, polygonOptions);
            int zOrder = shapeLayer.getZOrder();
            value = shapeLayer.getPassType().getValue();
            str2 = strAddCallback;
            str = layerId;
            i = zOrder;
        }
        if (polygonOptions.getDotPoints().size() > 0) {
            addMultiDotPointPolygon(str, str2, i, value, polygonOptions, z);
        } else {
            addMultiMapPointPolygon(str, str2, i, value, polygonOptions, z);
        }
    }

    private void addMultiDotPointPolygon(String str, String str2, int i, int i2, PolygonOptions polygonOptions, boolean z) throws RuntimeException {
        List<DotPoints> dotPoints = polygonOptions.getDotPoints();
        LatLng basePosition = dotPoints.get(0).getBasePosition();
        if (basePosition == null) {
            throw new RuntimeException("addMultiPolygon failed. PolygonOptions BasePosition is null.");
        }
        double latitude = basePosition.getLatitude();
        double longitude = basePosition.getLongitude();
        int stylesCount = polygonOptions.getStylesSet().getStylesCount();
        String strAddPolygonStylesSet = addPolygonStylesSet(polygonOptions.getStylesSet(), z);
        int size = dotPoints.size();
        int[] iArr = new int[size];
        PointVertex[] pointVertexArr = new PointVertex[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = Math.min(i3, stylesCount - 1);
            pointVertexArr[i3] = dotPoints.get(i3).getPoints();
        }
        addMultiPointPolygon(this.appEngineHandle, this.viewName, str, i, i2, strAddPolygonStylesSet, polygonOptions.getPolygonId(), size, latitude, longitude, pointVertexArr, iArr, polygonOptions.getZOrder(), polygonOptions.isVisible(), z, str2);
    }

    private void addMultiMapPointPolygon(String str, String str2, int i, int i2, PolygonOptions polygonOptions, boolean z) throws RuntimeException {
        int stylesCount = polygonOptions.getStylesSet().getStylesCount();
        String strAddPolygonStylesSet = addPolygonStylesSet(polygonOptions.getStylesSet(), z);
        List<MapPoints> mapPoints = polygonOptions.getMapPoints();
        int size = mapPoints.size();
        int[] iArr = new int[size];
        LatLngVertex[] latLngVertexArr = new LatLngVertex[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = Math.min(i3, stylesCount - 1);
            latLngVertexArr[i3] = mapPoints.get(i3).getPoints();
        }
        addMultiLatLngPolygon(this.appEngineHandle, this.viewName, str, i, i2, strAddPolygonStylesSet, polygonOptions.getPolygonId(), size, latLngVertexArr, iArr, polygonOptions.getZOrder(), polygonOptions.isVisible(), z, str2);
    }

    PolygonData getMapPolygonData(PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2, boolean z) throws RuntimeException {
        PolygonData polygonData = new PolygonData();
        int length = polygonOptionsArr.length;
        polygonData.styleIds = new String[length];
        polygonData.polygonIds = new String[length];
        polygonData.polygonCounts = new int[length];
        polygonData.mapVertices = new LatLngVertex[length][];
        polygonData.styleIndexes = new int[length][];
        polygonData.zOrders = new int[length];
        polygonData.visibles = new boolean[length];
        for (int i = 0; i < length; i++) {
            PolygonOptions polygonOptions = polygonOptionsArr[i];
            polygonData.styleIds[i] = addPolygonStylesSet(polygonOptions.getStylesSet(), z);
            polygonData.polygonIds[i] = polygonOptions.getPolygonId();
            polygonData.polygonCounts[i] = polygonOptions.getMapPoints().size();
            polygonData.zOrders[i] = polygonOptions.getZOrder();
            polygonData.visibles[i] = polygonOptions.isVisible();
            int stylesCount = polygonOptions.getStylesSet().getStylesCount();
            int i2 = polygonData.polygonCounts[i];
            List<MapPoints> mapPoints = polygonOptions.getMapPoints();
            int[] iArr = new int[i2];
            LatLngVertex[] latLngVertexArr = new LatLngVertex[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = Math.min(i3, stylesCount - 1);
                latLngVertexArr[i3] = mapPoints.get(i3).getPoints();
            }
            polygonData.styleIndexes[i] = iArr;
            polygonData.mapVertices[i] = latLngVertexArr;
        }
        return polygonData;
    }

    PolygonData getPointPolygonData(PolygonOptions[] polygonOptionsArr, PolygonOptions[] polygonOptionsArr2, boolean z) throws RuntimeException {
        PolygonData polygonData = new PolygonData();
        int length = polygonOptionsArr.length;
        polygonData.styleIds = new String[length];
        polygonData.polygonIds = new String[length];
        polygonData.polygonCounts = new int[length];
        polygonData.pointVertices = new PointVertex[length][];
        polygonData.styleIndexes = new int[length][];
        polygonData.zOrders = new int[length];
        polygonData.visibles = new boolean[length];
        for (int i = 0; i < length; i++) {
            PolygonOptions polygonOptions = polygonOptionsArr[i];
            polygonData.styleIds[i] = addPolygonStylesSet(polygonOptions.getStylesSet(), z);
            polygonData.polygonIds[i] = polygonOptions.getPolygonId();
            polygonData.polygonCounts[i] = polygonOptions.getDotPoints().size();
            polygonData.zOrders[i] = polygonOptions.getZOrder();
            polygonData.visibles[i] = polygonOptions.isVisible();
            LatLng basePosition = polygonOptions.getDotPoints().get(0).getBasePosition();
            polygonData.lngs[i] = basePosition.getLongitude();
            polygonData.lats[i] = basePosition.getLatitude();
            int stylesCount = polygonOptions.getStylesSet().getStylesCount();
            int i2 = polygonData.polygonCounts[i];
            List<DotPoints> dotPoints = polygonOptions.getDotPoints();
            int[] iArr = new int[i2];
            PointVertex[] pointVertexArr = new PointVertex[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = Math.min(i3, stylesCount - 1);
                pointVertexArr[i3] = dotPoints.get(i3).getPoints();
            }
            polygonData.styleIndexes[i] = iArr;
            polygonData.pointVertices[i] = pointVertexArr;
        }
        return polygonData;
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void setPolygonVisible(boolean z, String str, String str2, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolygonVisible(this.appEngineHandle, this.viewName, str, str2, z2, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void setPolygonZOrder(String str, String str2, int i, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolygonZOrder(this.appEngineHandle, this.viewName, str, str2, i, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void setPolygonCenterPoint(String str, String str2, LatLng latLng) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPolygonCenterPoint(this.appEngineHandle, this.viewName, str, str2, latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void removePolygon(boolean z, String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removePolygon(this.appEngineHandle, this.viewName, str, str2, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void removePolygons(String[] strArr, String[] strArr2, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (strArr2 == null || strArr2.length == 0) {
            MapLogger.e("removePolygons failure. Polygon is empty.");
        } else {
            removePolygons(this.appEngineHandle, this.viewName, strArr2.length, strArr, strArr2, z);
        }
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void removeAllShape(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        clearAll(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void startAnimator(String str, boolean z, List<Polygon> list) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArr = new String[list.size()];
        String[] strArr2 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getLayerId();
            strArr2[i] = list.get(i).getId();
        }
        startAnimator(this.appEngineHandle, this.viewName, str, z, list.size(), strArr, strArr2);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void stopAnimator(String str, boolean z, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        stopAnimator(this.appEngineHandle, this.viewName, str, z, z2);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void addCircleWaveAnimator(boolean z, CircleWaves circleWaves) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        CircleWave[] circleWaveArr = (CircleWave[]) circleWaves.getCircleWaves().toArray(new CircleWave[circleWaves.getCircleWaveCount()]);
        int length = circleWaveArr.length;
        int[] iArr = new int[length];
        float[] fArr = new float[length];
        float[] fArr2 = new float[length];
        float[] fArr3 = new float[length];
        float[] fArr4 = new float[length];
        for (int i = 0; i < length; i++) {
            CircleWave circleWave = circleWaveArr[i];
            iArr[i] = circleWave.getZoomLevel();
            fArr[i] = circleWave.getStartAlpha();
            fArr2[i] = circleWave.getEndAlpha();
            fArr3[i] = circleWave.getStartRadius();
            fArr4[i] = circleWave.getEndRadius();
        }
        addAnimator(this.appEngineHandle, this.viewName, circleWaves.getId(), circleWaves.getInterpolation().getValue(), circleWaves.getRepeatCount(), circleWaves.getDuration(), circleWaves.isHideShapeAtStop(), length, iArr, fArr, fArr2, fArr3, fArr4, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public synchronized void clearAllAnimator(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        clearAllAnimator(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void setDimScreenCover(DimScreenCover dimScreenCover) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (DimScreenCover.Map == dimScreenCover) {
            setDimScreenEffectZOrder(this.appEngineHandle, this.viewName, 2900);
            return;
        }
        if (DimScreenCover.MapAndLabel == dimScreenCover) {
            setDimScreenEffectZOrder(this.appEngineHandle, this.viewName, 4000);
        } else if (DimScreenCover.All == dimScreenCover) {
            setDimScreenEffectZOrder(this.appEngineHandle, this.viewName, 999999);
        } else {
            setDimScreenEffectZOrder(this.appEngineHandle, this.viewName, 999999);
        }
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public synchronized void removeAnimator(boolean z, String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAnimator(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void changePolygonStylesAndDotPoints(String str, String str2, PolygonStylesSet polygonStylesSet, List<DotPoints> list, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        LatLng basePosition = list.get(0).getBasePosition();
        if (basePosition == null) {
            throw new RuntimeException("changePolygonStylesAndDotPoints failed. PolygonOptions BasePosition is null.");
        }
        double latitude = basePosition.getLatitude();
        double longitude = basePosition.getLongitude();
        int stylesCount = polygonStylesSet.getStylesCount();
        String strAddPolygonStylesSet = addPolygonStylesSet(polygonStylesSet, z);
        int size = list.size();
        int[] iArr = new int[size];
        PointVertex[] pointVertexArr = new PointVertex[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = Math.min(i, stylesCount - 1);
            pointVertexArr[i] = list.get(i).getPoints();
        }
        changeStylesAndDotPoints(this.appEngineHandle, this.viewName, str, strAddPolygonStylesSet, str2, size, latitude, longitude, pointVertexArr, iArr, z);
    }

    @Override // com.kakao.vectormap.internal.IVectorDelegate
    public void changePolygonStylesAndMapPoints(String str, String str2, PolygonStylesSet polygonStylesSet, List<MapPoints> list, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        int stylesCount = polygonStylesSet.getStylesCount();
        String strAddPolygonStylesSet = addPolygonStylesSet(polygonStylesSet, z);
        int size = list.size();
        int[] iArr = new int[size];
        LatLngVertex[] latLngVertexArr = new LatLngVertex[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = Math.min(i, stylesCount - 1);
            latLngVertexArr[i] = list.get(i).getPoints();
        }
        changeStylesAndMapPoints(this.appEngineHandle, this.viewName, str, strAddPolygonStylesSet, str2, size, latLngVertexArr, iArr, z);
    }
}

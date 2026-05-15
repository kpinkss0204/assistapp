package com.kakao.vectormap.internal;

import android.util.ArrayMap;
import android.util.Pair;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.route.OnRouteLineAnimatorStopCallback;
import com.kakao.vectormap.route.OnRouteLineCreateCallback;
import com.kakao.vectormap.route.OnRouteLineProgressEndCallback;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineAnimator;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.route.RouteLineOptions;
import com.kakao.vectormap.route.RouteLinePattern;
import com.kakao.vectormap.route.RouteLineSegment;
import com.kakao.vectormap.route.RouteLineStyle;
import com.kakao.vectormap.route.RouteLineStyles;
import com.kakao.vectormap.route.RouteLineStylesSet;
import com.kakao.vectormap.route.RoutePoint;
import com.kakao.vectormap.route.animation.ProgressAnimation;
import com.kakao.vectormap.route.animation.ProgressDirection;
import com.kakao.vectormap.route.animation.ProgressType;
import com.kakao.vectormap.utils.MapUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
class RouteLineDelegate extends Destroyable implements IRouteLineDelegate {
    private long appEngineHandle;
    private IRouteLineFactory lineFactory;
    private MapResourceManager resourceManager;
    private String viewName;
    private final String DARK_STYLE_POSTFIX = "_dark";
    private Map<String, RouteLineLayer> layerMap = new ConcurrentHashMap();
    private Map<String, Pair<OnRouteLineAnimatorStopCallback, RouteLineAnimator>> animatorCallbacks = new ConcurrentHashMap();
    private RouteLineStyler routeLineStyler = new RouteLineStyler();
    private Map<String, RouteLineAnimator> animators = new ArrayMap();

    static native void addLayer(long j, String str, String str2, int i);

    static native void addMultiRouteLineStyles(long j, String str, String str2, RouteLineStyle[][] routeLineStyleArr, RouteLinePattern[] routeLinePatternArr);

    static native void addProgressAnimator(long j, String str, String str2, boolean z, boolean z2, int i, int i2, float f, float f2);

    static native void addRouteLine(long j, String str, String str2, int i, String str3, int i2, boolean z, String str4, RouteLineSegment[] routeLineSegmentArr, String str5);

    static native void addSingleRouteLineStyles(long j, String str, String str2, RouteLineStyle[] routeLineStyleArr, RouteLinePattern[] routeLinePatternArr);

    static native void changeSegments(long j, String str, String str2, String str3, String str4, RouteLineSegment[] routeLineSegmentArr);

    static native RoutePoint getPointFromProgress(long j, String str, String str2, String str3, float f);

    static native float getProgress(long j, String str, String str2, String str3);

    static native float getProgressFromPoint(long j, String str, String str2, String str3, double d, double d2);

    static native void nativeInit();

    static native void progressTo(long j, String str, String str2, String str3, float f, int i, String str4);

    static native void removeAllRouteLine(long j, String str);

    static native void removeAnimator(long j, String str, String str2);

    static native void removeLayer(long j, String str, String str2);

    static native void removeLayerRouteLine(long j, String str, String str2);

    static native void removeRouteLine(long j, String str, String str2, String str3);

    static native void setLayerVisible(long j, String str, String str2, boolean z);

    static native void setProgress(long j, String str, String str2, String str3, float f);

    static native void setVisible(long j, String str, String str2, String str3, boolean z);

    static native void setZOrder(long j, String str, String str2, String str3, int i);

    static native void startAnimator(long j, String str, String str2, int i, String[] strArr, String[] strArr2, String str3);

    static native void stopAnimator(long j, String str, String str2);

    static {
        nativeInit();
    }

    RouteLineDelegate(String str, long j, MapResourceManager mapResourceManager) {
        this.viewName = str;
        this.resourceManager = mapResourceManager;
        this.appEngineHandle = j;
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
        this.viewName = null;
        this.layerMap.clear();
        this.layerMap = null;
        this.resourceManager = null;
        this.lineFactory = null;
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public Pair<OnRouteLineAnimatorStopCallback, RouteLineAnimator> getAnimatorCallback(String str) {
        return this.animatorCallbacks.remove(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized boolean hasLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layerMap.containsKey(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void setFactory(IRouteLineFactory iRouteLineFactory) {
        this.lineFactory = iRouteLineFactory;
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public boolean hasStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.routeLineStyler.hasDarkStyles(str);
        }
        return this.routeLineStyler.hasLightStyles(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public RouteLineStylesSet getStylesSet(String str) throws RuntimeException {
        if (this.resourceManager.isDarkMode()) {
            return this.routeLineStyler.getDarkStyles(str);
        }
        return this.routeLineStyler.getLightStyles(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized RouteLineLayer addLayer(String str, int i) throws RuntimeException {
        String uniqueId;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        uniqueId = MapUtils.getUniqueId(str, i);
        addLayer(this.appEngineHandle, this.viewName, uniqueId, i);
        Map<String, RouteLineLayer> map = this.layerMap;
        IRouteLineFactory iRouteLineFactory = this.lineFactory;
        map.put(uniqueId, iRouteLineFactory.newLayer(this, uniqueId, i, iRouteLineFactory));
        return this.layerMap.get(uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized RouteLineLayer getLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.layerMap.get(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public String addRouteLineStyles(RouteLineStylesSet routeLineStylesSet) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        routeLineStylesSet.checkStyleId();
        if (this.resourceManager.isDarkMode()) {
            if (!this.routeLineStyler.hasDarkStyles(routeLineStylesSet.getStyleId())) {
                addMultiRouteLineStyles(routeLineStylesSet.getStyleId() + "_dark", routeLineStylesSet.getStyles(), routeLineStylesSet.getPatterns());
                this.routeLineStyler.addDarkStyles(routeLineStylesSet);
            }
            return routeLineStylesSet.getStyleId() + "_dark";
        }
        if (!this.routeLineStyler.hasLightStyles(routeLineStylesSet.getStyleId())) {
            addMultiRouteLineStyles(routeLineStylesSet.getStyleId(), routeLineStylesSet.getStyles(), routeLineStylesSet.getPatterns());
            this.routeLineStyler.addLightStyles(routeLineStylesSet);
        }
        return routeLineStylesSet.getStyleId();
    }

    private String addMultiRouteLineStyles(String str, List<RouteLineStyles> list, List<RouteLinePattern> list2) {
        RouteLinePattern[] routeLinePatternArr;
        RouteLineStyle[][] routeLineStyleArr = new RouteLineStyle[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            routeLineStyleArr[i] = setRouteLineStyleContent(list.get(i), list2);
        }
        long j = this.appEngineHandle;
        String str2 = this.viewName;
        if (list2.isEmpty()) {
            routeLinePatternArr = new RouteLinePattern[0];
        } else {
            routeLinePatternArr = (RouteLinePattern[]) list2.toArray(new RouteLinePattern[list2.size()]);
        }
        addMultiRouteLineStyles(j, str2, str, routeLineStyleArr, routeLinePatternArr);
        return str;
    }

    private String addSingleRouteLineStyles(String str, RouteLineStyles routeLineStyles, List<RouteLinePattern> list) {
        RouteLinePattern[] routeLinePatternArr;
        RouteLineStyle[] routeLineStyleContent = setRouteLineStyleContent(routeLineStyles, list);
        long j = this.appEngineHandle;
        String str2 = this.viewName;
        if (list.isEmpty()) {
            routeLinePatternArr = new RouteLinePattern[0];
        } else {
            routeLinePatternArr = (RouteLinePattern[]) list.toArray(new RouteLinePattern[list.size()]);
        }
        addSingleRouteLineStyles(j, str2, str, routeLineStyleContent, routeLinePatternArr);
        return str;
    }

    private RouteLineStyle[] setRouteLineStyleContent(RouteLineStyles routeLineStyles, List<RouteLinePattern> list) {
        int length = routeLineStyles.getStyles().length;
        RouteLineStyle[] routeLineStyleArr = new RouteLineStyle[length];
        for (RouteLinePattern routeLinePattern : list) {
            routeLinePattern.patternAssetId = this.resourceManager.addImage(routeLinePattern.patternResId, routeLinePattern.patternBitmap);
            routeLinePattern.symbolAssetId = this.resourceManager.addImage(routeLinePattern.symbolResId, routeLinePattern.symbolBitmap);
        }
        for (int i = 0; i < length; i++) {
            RouteLineStyle routeLineStyle = routeLineStyles.getStyles()[i];
            routeLineStyleArr[i] = routeLineStyle;
            routeLineStyle.patternIndex = list.indexOf(routeLineStyle.getPattern());
        }
        return routeLineStyleArr;
    }

    private int getStyleIndex(List<RouteLineStyles> list, RouteLineStyles routeLineStyles) {
        int iIndexOf = list.indexOf(routeLineStyles);
        if (iIndexOf < 0) {
            MapLogger.e("RouteLineStyles index not matched!");
        }
        return iIndexOf;
    }

    private RouteLineSegment buildSegment(RouteLineSegment routeLineSegment, List<RouteLineStyles> list) throws RuntimeException {
        routeLineSegment.styleIndex = getStyleIndex(list, routeLineSegment.getStyles());
        int size = routeLineSegment.getPoints().size();
        if (size <= 1) {
            throw new RuntimeException("RouteLineSegment Points must be at least two.");
        }
        List<LatLng> points = routeLineSegment.getPoints();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            LatLng latLng = points.get(i);
            dArr[i] = latLng.latitude;
            dArr2[i] = latLng.longitude;
        }
        routeLineSegment.lats = dArr;
        routeLineSegment.lngs = dArr2;
        return routeLineSegment;
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void changeSegments(String str, String str2, RouteLineStylesSet routeLineStylesSet, RouteLineSegment... routeLineSegmentArr) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (routeLineSegmentArr == null || routeLineSegmentArr.length < 1) {
            throw new RuntimeException("changeSegments failure. RouteLineSegments is invalid.");
        }
        String strAddRouteLineStyles = addRouteLineStyles(routeLineStylesSet);
        for (RouteLineSegment routeLineSegment : routeLineSegmentArr) {
            buildSegment(routeLineSegment, routeLineStylesSet.getStyles());
        }
        changeSegments(this.appEngineHandle, this.viewName, str, str2, strAddRouteLineStyles, routeLineSegmentArr);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void changeStyles(String str, String str2, RouteLineStylesSet routeLineStylesSet, RouteLineSegment... routeLineSegmentArr) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String strAddRouteLineStyles = addRouteLineStyles(routeLineStylesSet);
        int length = routeLineSegmentArr.length;
        for (int i = 0; i < length; i++) {
            routeLineSegmentArr[i].styleIndex = getStyleIndex(routeLineStylesSet.getStyles(), routeLineSegmentArr[i].getStyles());
        }
        changeSegments(this.appEngineHandle, this.viewName, str, str2, strAddRouteLineStyles, routeLineSegmentArr);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void addRouteLine(RouteLineLayer routeLineLayer, RouteLineOptions routeLineOptions, OnRouteLineCreateCallback onRouteLineCreateCallback) throws RuntimeException {
        String strAddCallback;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (routeLineOptions.getSegments() == null || routeLineOptions.getSegments().length < 1) {
            throw new RuntimeException("addRouteLine failure. RouteLineSegments is invalid.");
        }
        if (routeLineOptions.getStylesSet() == null || routeLineOptions.getStylesSet().getStyles().isEmpty()) {
            throw new RuntimeException("addRouteLine failure. RouteLineStyles is invalid.");
        }
        String strAddRouteLineStyles = addRouteLineStyles(routeLineOptions.getStylesSet());
        int length = routeLineOptions.getSegments().length;
        for (int i = 0; i < length; i++) {
            buildSegment(routeLineOptions.getSegments()[i], routeLineOptions.getStylesSet().getStyles());
        }
        if (onRouteLineCreateCallback == null) {
            strAddCallback = "";
        } else {
            strAddCallback = routeLineLayer.addCallback(onRouteLineCreateCallback, routeLineOptions);
        }
        addRouteLine(this.appEngineHandle, this.viewName, routeLineLayer.getLayerId(), routeLineLayer.getZOrder(), routeLineOptions.getLineId(), routeLineOptions.getZOrder(), routeLineOptions.isVisible(), strAddRouteLineStyles, routeLineOptions.getSegments(), strAddCallback);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void setVisible(String str, String str2, boolean z) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setVisible(this.appEngineHandle, this.viewName, str, str2, z);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void removeRouteLine(String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeRouteLine(this.appEngineHandle, this.viewName, str, str2);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void setProgress(String str, String str2, float f) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setProgress(this.appEngineHandle, this.viewName, str, str2, f);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public float getProgress(String str, String str2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return getProgress(this.appEngineHandle, this.viewName, str, str2);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void progressTo(String str, String str2, float f, int i, OnRouteLineProgressEndCallback onRouteLineProgressEndCallback) throws RuntimeException {
        RouteLine routeLine;
        String strAddCallbackByProgress;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        RouteLineLayer layer = getLayer(str);
        if (layer == null || (routeLine = layer.getRouteLine(str2)) == null) {
            return;
        }
        if (onRouteLineProgressEndCallback == null) {
            strAddCallbackByProgress = "";
        } else {
            strAddCallbackByProgress = layer.addCallbackByProgress(onRouteLineProgressEndCallback, routeLine);
        }
        progressTo(this.appEngineHandle, this.viewName, str, str2, f, i, strAddCallbackByProgress);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public RoutePoint getPointFromProgress(String str, String str2, float f) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return getPointFromProgress(this.appEngineHandle, this.viewName, str, str2, f);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public float getProgressFromPoint(String str, String str2, LatLng latLng) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return getProgressFromPoint(this.appEngineHandle, this.viewName, str, str2, latLng.latitude, latLng.longitude);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void removeLayer(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeLayer(this.appEngineHandle, this.viewName, str);
        this.layerMap.remove(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void removeAllRouteLine() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllRouteLine(this.appEngineHandle, this.viewName);
        this.layerMap.clear();
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void setZOrder(String str, String str2, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setZOrder(this.appEngineHandle, this.viewName, str, str2, i);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void removeLayerRouteLine(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeLayerRouteLine(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public synchronized void setLayerVisible(String str, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setLayerVisible(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public RouteLineAnimator addProgressAnimator(String str, ProgressAnimation progressAnimation) throws RuntimeException {
        float f;
        float f2;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (progressAnimation.getProgressDirection() == ProgressDirection.StartFirst) {
            if (progressAnimation.getProgressType() == ProgressType.ToHide) {
                f2 = 1.0f;
                f = 0.0f;
            } else {
                f = -1.0f;
                f2 = 0.0f;
            }
        } else if (progressAnimation.getProgressType() == ProgressType.ToHide) {
            f2 = -1.0f;
            f = 0.0f;
        } else {
            f = 1.0f;
            f2 = 0.0f;
        }
        addProgressAnimator(this.appEngineHandle, this.viewName, str, progressAnimation.isHideAtStop(), progressAnimation.isResetToInitialState(), progressAnimation.getDuration(), progressAnimation.getInterpolation().getValue(), f, f2);
        RouteLineAnimator routeLineAnimatorNewAnimator = this.lineFactory.newAnimator(this, str, progressAnimation.isHideAtStop(), progressAnimation.isResetToInitialState(), progressAnimation.getDuration());
        this.animators.put(routeLineAnimatorNewAnimator.getId(), routeLineAnimatorNewAnimator);
        return routeLineAnimatorNewAnimator;
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void startAnimator(String str, List<RouteLine> list, OnRouteLineAnimatorStopCallback onRouteLineAnimatorStopCallback) throws RuntimeException {
        String uniqueId;
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        String[] strArr = new String[list.size()];
        String[] strArr2 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i).getLayerId();
            strArr2[i] = list.get(i).getLineId();
        }
        if (onRouteLineAnimatorStopCallback == null) {
            uniqueId = "";
        } else {
            uniqueId = MapUtils.getUniqueId(onRouteLineAnimatorStopCallback.hashCode());
            this.animatorCallbacks.put(uniqueId, new Pair<>(onRouteLineAnimatorStopCallback, this.animators.get(str)));
        }
        startAnimator(this.appEngineHandle, this.viewName, str, list.size(), strArr, strArr2, uniqueId);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void stopAnimator(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        stopAnimator(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public RouteLineAnimator getAnimator(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        return this.animators.get(str);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineDelegate
    public void removeAnimator(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAnimator(this.appEngineHandle, this.viewName, str);
        this.animators.remove(str);
    }
}

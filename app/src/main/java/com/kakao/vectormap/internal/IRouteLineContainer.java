package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.route.OnRouteLineCreateCallback;
import com.kakao.vectormap.route.OnRouteLineProgressEndCallback;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineOptions;
import com.kakao.vectormap.utils.MapUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class IRouteLineContainer {
    protected final IRouteLineDelegate delegate;
    private IRouteLineFactory factory;
    protected final String layerId;
    protected final int zOrder;
    protected final Map<String, RouteLine> lineMap = new ConcurrentHashMap();
    private Map<String, Pair<OnRouteLineCreateCallback, RouteLineOptions>> lineCallback = new ConcurrentHashMap();
    private Map<String, String> preRouteLines = new ConcurrentHashMap();
    private Map<String, Pair<OnRouteLineProgressEndCallback, RouteLine>> progressEndCallback = new ConcurrentHashMap();

    public IRouteLineContainer(IRouteLineDelegate iRouteLineDelegate, String str, int i, IRouteLineFactory iRouteLineFactory) {
        this.delegate = iRouteLineDelegate;
        this.layerId = str;
        this.zOrder = i;
        this.factory = iRouteLineFactory;
    }

    protected synchronized void removeAllCallback() {
        this.lineCallback.clear();
        this.preRouteLines.clear();
    }

    protected synchronized void removeCallback(String str) {
        String strRemove = this.preRouteLines.remove(str);
        if (strRemove != null) {
            this.lineCallback.remove(strRemove);
        }
    }

    synchronized String addCallback(OnRouteLineCreateCallback onRouteLineCreateCallback, RouteLineOptions routeLineOptions) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(this.layerId.hashCode());
        this.lineCallback.put(uniqueId, new Pair<>(onRouteLineCreateCallback, routeLineOptions));
        this.preRouteLines.put(routeLineOptions.getLineId(), uniqueId);
        return uniqueId;
    }

    protected synchronized RouteLine newRouteLine(RouteLineOptions routeLineOptions) {
        RouteLine routeLineNewRouteLine;
        routeLineNewRouteLine = this.factory.newRouteLine(this.delegate, this.layerId, routeLineOptions);
        this.lineMap.put(routeLineNewRouteLine.getLineId(), routeLineNewRouteLine);
        return routeLineNewRouteLine;
    }

    synchronized Pair<OnRouteLineCreateCallback, RouteLine> getRouteLine(String str) {
        if (!this.lineCallback.containsKey(str)) {
            return null;
        }
        Pair<OnRouteLineCreateCallback, RouteLineOptions> pairRemove = this.lineCallback.remove(str);
        String lineId = ((RouteLineOptions) pairRemove.second).getLineId();
        if (!this.lineMap.containsKey(((RouteLineOptions) pairRemove.second).getLineId())) {
            RouteLine routeLineNewRouteLine = this.factory.newRouteLine(this.delegate, this.layerId, (RouteLineOptions) pairRemove.second);
            this.lineMap.put(routeLineNewRouteLine.getLineId(), routeLineNewRouteLine);
        }
        this.preRouteLines.remove(lineId);
        return new Pair<>((OnRouteLineCreateCallback) pairRemove.first, this.lineMap.get(lineId));
    }

    synchronized String addCallbackByProgress(OnRouteLineProgressEndCallback onRouteLineProgressEndCallback, RouteLine routeLine) {
        String uniqueId;
        uniqueId = MapUtils.getUniqueId(onRouteLineProgressEndCallback.hashCode());
        this.progressEndCallback.put(uniqueId, new Pair<>(onRouteLineProgressEndCallback, routeLine));
        return uniqueId;
    }

    synchronized Pair<OnRouteLineProgressEndCallback, RouteLine> getRouteLineByProgress(String str) {
        if (!this.progressEndCallback.containsKey(str)) {
            return null;
        }
        return this.progressEndCallback.remove(str);
    }
}

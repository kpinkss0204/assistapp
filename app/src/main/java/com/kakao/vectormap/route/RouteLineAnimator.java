package com.kakao.vectormap.route;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IRouteLineDelegate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineAnimator {
    private IRouteLineDelegate delegate;
    private final int duration;
    private final boolean hideLineAtStop;
    private final String id;
    private final boolean resetToInitialState;
    private final List<RouteLine> routeLines = new ArrayList();
    private Object tag;

    RouteLineAnimator(IRouteLineDelegate iRouteLineDelegate, String str, boolean z, boolean z2, int i) {
        this.delegate = iRouteLineDelegate;
        this.id = str;
        this.hideLineAtStop = z;
        this.resetToInitialState = z2;
        this.duration = i;
    }

    public synchronized String getId() {
        return this.id;
    }

    public synchronized void addRouteLines(RouteLine... routeLineArr) {
        if (routeLineArr != null) {
            if (routeLineArr.length != 0) {
                this.routeLines.addAll(Arrays.asList(routeLineArr));
                return;
            }
        }
        MapLogger.e("RouteLineAnimator addRouteLines failure. routeLines is invalid.");
    }

    public synchronized void start() {
        start(null);
    }

    public synchronized void start(OnRouteLineAnimatorStopCallback onRouteLineAnimatorStopCallback) {
        try {
            this.delegate.startAnimator(this.id, this.routeLines, onRouteLineAnimatorStopCallback);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void stop() {
        try {
            this.delegate.stopAnimator(this.id);
            this.routeLines.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}

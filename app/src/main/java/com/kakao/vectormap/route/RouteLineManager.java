package com.kakao.vectormap.route;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IRouteLineDelegate;
import com.kakao.vectormap.route.animation.ProgressAnimation;
import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineManager {
    public static final String DEFAULT_LAYER_ID = "route_layer_0";
    public static final int DEFAULT_LAYER_Z_ORDER = 10000;
    private IRouteLineDelegate delegate;

    public RouteLineManager(IRouteLineDelegate iRouteLineDelegate) {
        this.delegate = iRouteLineDelegate;
        iRouteLineDelegate.setFactory(new RouteLineFactory());
        this.delegate.addLayer(DEFAULT_LAYER_ID, 10000);
    }

    public synchronized RouteLineStylesSet addStylesSet(RouteLineStylesSet routeLineStylesSet) {
        try {
            if (routeLineStylesSet == null) {
                throw new RuntimeException("addRouteLineStylesSet failure. RouteLineStylesSet is null.");
            }
            if (!this.delegate.hasStylesSet(routeLineStylesSet.getStyleId())) {
                this.delegate.addRouteLineStyles(routeLineStylesSet);
            }
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getStylesSet(routeLineStylesSet.getStyleId());
    }

    public synchronized RouteLineStylesSet getStylesSet(String str) {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getStylesSet(str);
    }

    public synchronized RouteLineLayer addLayer() {
        try {
            String uniqueId = MapUtils.getUniqueId();
            if (this.delegate.hasLayer(uniqueId)) {
                return this.delegate.getLayer(uniqueId);
            }
            return this.delegate.addLayer(uniqueId, 10000);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized RouteLineLayer addLayer(int i) {
        try {
            String uniqueId = MapUtils.getUniqueId();
            if (this.delegate.hasLayer(uniqueId)) {
                return this.delegate.getLayer(uniqueId);
            }
            return this.delegate.addLayer(uniqueId, i);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized RouteLineLayer addLayer(String str) {
        return addLayer(str, 10000);
    }

    public synchronized RouteLineLayer addLayer(String str, int i) {
        try {
            if (this.delegate.hasLayer(str)) {
                return this.delegate.getLayer(str);
            }
            return this.delegate.addLayer(str, i);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized RouteLineAnimator addAnimator(ProgressAnimation progressAnimation) {
        try {
            if (this.delegate.getAnimator(progressAnimation.getId()) != null) {
                return this.delegate.getAnimator(progressAnimation.getId());
            }
            return this.delegate.addProgressAnimator(progressAnimation.getId(), progressAnimation);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized RouteLineAnimator getAnimator(String str) {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getAnimator(str);
    }

    public synchronized RouteLineLayer getLayer() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLayer(DEFAULT_LAYER_ID);
    }

    public synchronized RouteLineLayer getLayer(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLayer(str);
    }

    public synchronized void remove(RouteLine routeLine) {
        try {
            this.delegate.removeRouteLine(routeLine.getLayerId(), routeLine.getLineId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(RouteLineLayer routeLineLayer) {
        try {
            this.delegate.removeLayer(routeLineLayer.getLayerId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearAll() {
        try {
            this.delegate.removeAllRouteLine();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}

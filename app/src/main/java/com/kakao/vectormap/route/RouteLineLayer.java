package com.kakao.vectormap.route;

import android.util.Log;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IRouteLineContainer;
import com.kakao.vectormap.internal.IRouteLineDelegate;
import com.kakao.vectormap.internal.IRouteLineFactory;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineLayer extends IRouteLineContainer {
    private Object tag;

    RouteLineLayer(IRouteLineDelegate iRouteLineDelegate, String str, int i, IRouteLineFactory iRouteLineFactory) {
        super(iRouteLineDelegate, str, i, iRouteLineFactory);
    }

    public synchronized RouteLine addRouteLine(RouteLineOptions routeLineOptions) {
        try {
            checkValidate();
            if (routeLineOptions == null) {
                throw new RuntimeException("addRouteLine failure. RouteLineOptions is null.");
            }
            if (this.lineMap.containsKey(routeLineOptions.getLineId())) {
                return this.lineMap.get(routeLineOptions.getLineId());
            }
            this.delegate.addRouteLine(this, routeLineOptions, null);
            return newRouteLine(routeLineOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized void addRouteLine(RouteLineOptions routeLineOptions, OnRouteLineCreateCallback onRouteLineCreateCallback) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (routeLineOptions == null) {
            Log.e(Const.TAG, "addLines failed. Params is null.");
            return;
        }
        if (routeLineOptions.getSegments() != null && routeLineOptions.getSegments().length != 0) {
            if (!this.lineMap.containsKey(routeLineOptions.getLineId())) {
                this.delegate.addRouteLine(this, routeLineOptions, onRouteLineCreateCallback);
                return;
            } else {
                if (onRouteLineCreateCallback != null) {
                    onRouteLineCreateCallback.onRouteLineCreated(this, this.lineMap.get(routeLineOptions.getLineId()));
                }
                return;
            }
        }
        Log.e(Const.TAG, "addLines failed. RouteLineSegments is invalid.");
    }

    public synchronized RouteLine getRouteLine(String str) {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.lineMap.get(str);
    }

    public synchronized RouteLine[] getAllRouteLines() {
        try {
            checkValidate();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return (RouteLine[]) this.lineMap.values().toArray(new RouteLine[this.lineMap.size()]);
    }

    public String getLayerId() {
        return this.layerId;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public synchronized int getRouteLineCount() {
        return this.lineMap.size();
    }

    public synchronized void setVisible(boolean z) {
        try {
            checkValidate();
            this.delegate.setLayerVisible(this.layerId, z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(RouteLine routeLine) {
        try {
            checkValidate();
            this.delegate.removeRouteLine(this.layerId, routeLine.getLineId());
            removeCallback(routeLine.getLineId());
            this.lineMap.remove(routeLine.getLineId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAll() {
        try {
            checkValidate();
            this.delegate.removeLayerRouteLine(this.layerId);
            removeAllCallback();
            this.lineMap.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public synchronized Object getTag() {
        return this.tag;
    }

    private synchronized void checkValidate() throws RuntimeException {
        if (!this.delegate.hasLayer(this.layerId)) {
            throw new RuntimeException("RouteLineLayer(id=" + this.layerId + ") is removed. RouteLineLayer must be added first.");
        }
    }
}

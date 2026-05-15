package com.kakao.vectormap.mapwidget;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IGuiDelegate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class MapWidgetLayer {
    private IGuiDelegate delegate;
    private Map<String, MapWidget> mapWidgets = new ConcurrentHashMap();
    private Object tag;

    MapWidgetLayer(IGuiDelegate iGuiDelegate) {
        this.delegate = iGuiDelegate;
    }

    public synchronized MapWidget addMapWidget(MapWidgetOptions mapWidgetOptions) {
        try {
            if (mapWidgetOptions == null) {
                throw new RuntimeException("addMapWidget failure. MapWidgetOptions is null.");
            }
            if (mapWidgetOptions.getRootView() == null) {
                throw new RuntimeException("addMapWidget failure. MapWidgetOptions rootView is null.");
            }
            if (this.mapWidgets.containsKey(mapWidgetOptions.getId())) {
                return this.mapWidgets.get(mapWidgetOptions.getId());
            }
            this.delegate.addMapWidget(mapWidgetOptions);
            this.mapWidgets.put(mapWidgetOptions.getId(), new MapWidget(this.delegate, mapWidgetOptions.getId(), mapWidgetOptions.getMapGravity(), mapWidgetOptions.getPosition(), mapWidgetOptions.getRotation(), mapWidgetOptions.isVisible(), mapWidgetOptions.getZOrder(), mapWidgetOptions.getRootView(), mapWidgetOptions.getTag()));
            return this.mapWidgets.get(mapWidgetOptions.getId());
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized MapWidget getMapWidget(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.mapWidgets.get(str);
    }

    public synchronized MapWidget[] getAllMapWidgets() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return (MapWidget[]) this.mapWidgets.values().toArray(new MapWidget[this.mapWidgets.size()]);
    }

    public synchronized void remove(MapWidget mapWidget) {
        try {
            this.delegate.removeMapWidget(mapWidget.getId());
            this.mapWidgets.remove(mapWidget.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAll() {
        try {
            this.delegate.removeAllMapWidget();
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
}

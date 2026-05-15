package com.kakao.vectormap.mapwidget;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IGuiDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class MapWidgetManager {
    private final IGuiDelegate delegate;

    public MapWidgetManager(IGuiDelegate iGuiDelegate) {
        this.delegate = iGuiDelegate;
        iGuiDelegate.setInfoWindowLayer(new InfoWindowLayer(iGuiDelegate));
        iGuiDelegate.setMapWidgetLayer(new MapWidgetLayer(iGuiDelegate));
    }

    public InfoWindowLayer getInfoWindowLayer() {
        try {
            return this.delegate.getInfoWindowLayer();
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public MapWidgetLayer getMapWidgetLayer() {
        try {
            return this.delegate.getMapWidgetLayer();
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }
}

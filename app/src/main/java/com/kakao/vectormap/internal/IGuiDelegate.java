package com.kakao.vectormap.internal;

import android.graphics.PointF;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.mapwidget.InfoWindowLayer;
import com.kakao.vectormap.mapwidget.InfoWindowOptions;
import com.kakao.vectormap.mapwidget.MapWidgetLayer;
import com.kakao.vectormap.mapwidget.MapWidgetOptions;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiView;

/* JADX INFO: loaded from: classes4.dex */
public interface IGuiDelegate {
    void addInfoWindow(InfoWindowOptions infoWindowOptions) throws RuntimeException;

    void addMapWidget(MapWidgetOptions mapWidgetOptions) throws RuntimeException;

    InfoWindowLayer getInfoWindowLayer();

    MapWidgetLayer getMapWidgetLayer();

    void moveTo(String str, LatLng latLng, long j) throws RuntimeException;

    void removeAllInfoWindow() throws RuntimeException;

    void removeAllMapWidget() throws RuntimeException;

    void removeInfoWindow(String str) throws RuntimeException;

    void removeMapWidget(String str) throws RuntimeException;

    void setInfoWindowLayer(InfoWindowLayer infoWindowLayer);

    void setInfoWindowLayerVisible(boolean z) throws RuntimeException;

    void setMapWidgetLayer(MapWidgetLayer mapWidgetLayer);

    void setMapWidgetPosition(String str, int i, float f, float f2) throws RuntimeException;

    void setMapWidgetRotation(String str, float f) throws RuntimeException;

    void setMapWidgetVisible(String str, boolean z) throws RuntimeException;

    void setMapWidgetZOrder(String str, int i) throws RuntimeException;

    void setPosition(String str, LatLng latLng) throws RuntimeException;

    void setVisible(String str, boolean z, boolean z2, int i) throws RuntimeException;

    void setZOrder(String str, int i) throws RuntimeException;

    void update(String str, LatLng latLng, GuiView guiView, GuiImage guiImage, PointF pointF, PointF pointF2, boolean z, boolean z2) throws RuntimeException;
}

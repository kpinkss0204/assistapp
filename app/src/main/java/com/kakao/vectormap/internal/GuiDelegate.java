package com.kakao.vectormap.internal;

import android.graphics.PointF;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.mapwidget.InfoWindowLayer;
import com.kakao.vectormap.mapwidget.InfoWindowOptions;
import com.kakao.vectormap.mapwidget.MapWidgetLayer;
import com.kakao.vectormap.mapwidget.MapWidgetOptions;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiLayout;
import com.kakao.vectormap.mapwidget.component.GuiView;

/* JADX INFO: loaded from: classes4.dex */
class GuiDelegate extends Destroyable implements IGuiDelegate {
    private long appEngineHandle;
    private InfoWindowLayer infoWindowLayer;
    private MapWidgetLayer mapWidgetLayer;
    private MapResourceManager resourceManager;
    private String viewName;

    static native void addInfoWindow(long j, String str, double d, double d2, String str2, GuiView guiView, GuiView guiView2, float f, float f2, float f3, float f4, boolean z, boolean z2, boolean z3, int i, int i2);

    static native void addMapWidget(long j, String str, int i, float f, float f2, String str2, int i2, boolean z, GuiView guiView);

    static native void moveTo(long j, String str, String str2, double d, double d2, long j2);

    static native void nativeInit();

    static native void removeAllInfoWindow(long j, String str);

    static native void removeAllMapWidget(long j, String str);

    static native void removeInfoWindow(long j, String str, String str2);

    static native void removeMapWidget(long j, String str, String str2);

    static native void setInfoWindowLayerVisible(long j, String str, boolean z);

    static native void setMapWidgetPosition(long j, String str, String str2, int i, float f, float f2);

    static native void setMapWidgetRotation(long j, String str, String str2, float f);

    static native void setMapWidgetVisible(long j, String str, String str2, boolean z);

    static native void setMapWidgetZOrder(long j, String str, String str2, int i);

    static native void setPosition(long j, String str, String str2, double d, double d2);

    static native void setVisible(long j, String str, String str2, boolean z, boolean z2, int i);

    static native void setZOrder(long j, String str, String str2, int i);

    static native void updateInfoWindow(long j, String str, String str2, double d, double d2, GuiView guiView, GuiView guiView2, float f, float f2, float f3, float f4, boolean z, boolean z2);

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
    }

    static {
        nativeInit();
    }

    public GuiDelegate(long j, String str, MapResourceManager mapResourceManager) {
        this.viewName = str;
        this.resourceManager = mapResourceManager;
        this.appEngineHandle = j;
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setInfoWindowLayer(InfoWindowLayer infoWindowLayer) {
        this.infoWindowLayer = infoWindowLayer;
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public InfoWindowLayer getInfoWindowLayer() {
        return this.infoWindowLayer;
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setMapWidgetLayer(MapWidgetLayer mapWidgetLayer) {
        this.mapWidgetLayer = mapWidgetLayer;
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public MapWidgetLayer getMapWidgetLayer() {
        return this.mapWidgetLayer;
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void addInfoWindow(InfoWindowOptions infoWindowOptions) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addAssetImage(infoWindowOptions.getBody());
        if (infoWindowOptions.getTail() != null) {
            addAssetImage(infoWindowOptions.getTail());
        }
        PointF bodyOffset = infoWindowOptions.getBodyOffset();
        PointF tailOffset = infoWindowOptions.getTailOffset();
        LatLng position = infoWindowOptions.getPosition();
        addInfoWindow(this.appEngineHandle, this.viewName, position.getLatitude(), position.getLongitude(), infoWindowOptions.getId(), infoWindowOptions.getBody(), infoWindowOptions.getTail(), bodyOffset.x, bodyOffset.y, tailOffset.x, tailOffset.y, infoWindowOptions.isVisible(), infoWindowOptions.isApplyDpScale(), infoWindowOptions.isAutoMove(), infoWindowOptions.getAutoMoveDuration(), infoWindowOptions.getZOrder());
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void addMapWidget(MapWidgetOptions mapWidgetOptions) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        addMapWidget(this.appEngineHandle, this.viewName, mapWidgetOptions.getMapGravity(), mapWidgetOptions.getPosition().x, mapWidgetOptions.getPosition().y, mapWidgetOptions.getId(), mapWidgetOptions.getZOrder(), mapWidgetOptions.isVisible(), mapWidgetOptions.getRootView());
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setZOrder(String str, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setZOrder(this.appEngineHandle, this.viewName, str, i);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setVisible(String str, boolean z, boolean z2, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setVisible(this.appEngineHandle, this.viewName, str, z, z2, i);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setPosition(String str, LatLng latLng) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setPosition(this.appEngineHandle, this.viewName, str, latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void moveTo(String str, LatLng latLng, long j) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        moveTo(this.appEngineHandle, this.viewName, str, latLng.getLatitude(), latLng.getLongitude(), j);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void update(String str, LatLng latLng, GuiView guiView, GuiImage guiImage, PointF pointF, PointF pointF2, boolean z, boolean z2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (guiView != null) {
            addAssetImage(guiView);
        }
        if (guiImage != null) {
            addAssetImage(guiImage);
        }
        updateInfoWindow(this.appEngineHandle, this.viewName, str, latLng.getLatitude(), latLng.getLongitude(), guiView, guiImage, pointF.x, pointF.y, pointF2.x, pointF2.y, z, z2);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void removeInfoWindow(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeInfoWindow(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setInfoWindowLayerVisible(boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setInfoWindowLayerVisible(this.appEngineHandle, this.viewName, z);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void removeAllInfoWindow() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllInfoWindow(this.appEngineHandle, this.viewName);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setMapWidgetVisible(String str, boolean z) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setMapWidgetVisible(this.appEngineHandle, this.viewName, str, z);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setMapWidgetPosition(String str, int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setMapWidgetPosition(this.appEngineHandle, this.viewName, str, i, f, f2);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setMapWidgetZOrder(String str, int i) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setMapWidgetZOrder(this.appEngineHandle, this.viewName, str, i);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void setMapWidgetRotation(String str, float f) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setMapWidgetRotation(this.appEngineHandle, this.viewName, str, f);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void removeMapWidget(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeMapWidget(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IGuiDelegate
    public void removeAllMapWidget() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        removeAllMapWidget(this.appEngineHandle, this.viewName);
    }

    private void addAssetImage(GuiView guiView) {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (guiView instanceof GuiLayout) {
            GuiLayout guiLayout = (GuiLayout) guiView;
            if (guiLayout.getBackground() != null) {
                guiLayout.getBackground().assetId = this.resourceManager.addImage(guiLayout.getBackground().resourceId, guiLayout.getBackground().bitmap);
            }
            if (guiLayout.hasChild()) {
                for (GuiView guiView2 : guiLayout.getChildArray()) {
                    addAssetImage(guiView2);
                }
                return;
            }
            return;
        }
        if (guiView instanceof GuiImage) {
            GuiImage guiImage = (GuiImage) guiView;
            guiImage.assetId = this.resourceManager.addImage(guiImage.resourceId, guiImage.bitmap);
            if (guiImage.getChild() != null) {
                addAssetImage(guiImage.getChild());
            }
        }
    }
}

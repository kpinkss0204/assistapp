package com.kakao.vectormap.internal;

import android.graphics.Rect;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.Logo;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.MapView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class RenderViewDelegate {
    protected long appEngineHandle;
    protected final boolean isDev;
    private boolean isVisible;
    protected Logo logo;
    protected MapView mapView;
    protected Object tag;
    protected final String viewName;
    protected Rect viewport;
    private boolean isRunning = false;
    protected List<Destroyable> destroyables = new ArrayList();

    public abstract void setLogoPosition(int i, float f, float f2) throws RuntimeException;

    public RenderViewDelegate(long j, Object obj, String str, String str2, MapView mapView, Rect rect, boolean z, boolean z2) {
        this.appEngineHandle = j;
        this.tag = obj;
        this.viewName = str2;
        this.mapView = mapView;
        this.viewport = rect;
        this.isDev = z;
        this.isVisible = z2;
    }

    synchronized void setEngineStatus(boolean z) {
        this.isRunning = z;
        for (Destroyable destroyable : this.destroyables) {
            destroyable.setRunning(z);
            if (!z) {
                destroyable.onDestroy();
            }
        }
        if (!z) {
            this.destroyables.clear();
        }
    }

    synchronized boolean isRunning() {
        return this.isRunning;
    }

    synchronized Rect setViewportInternal(int i, int i2, int i3, int i4) {
        this.viewport.set(i, i2, i3 + i, i4 + i2);
        return this.viewport;
    }

    public void setTag(Object obj) {
        synchronized (this) {
            this.tag = obj;
        }
    }

    public Object getTag() {
        Object obj;
        synchronized (this) {
            obj = this.tag;
        }
        return obj;
    }

    public String getViewName() {
        return this.viewName;
    }

    public boolean isDev() throws RuntimeException {
        return this.isDev;
    }

    public void setVisible(boolean z) throws RuntimeException {
        synchronized (this) {
            if (!this.isRunning) {
                throw new RuntimeException(Const.UnInitialized);
            }
            RenderViewController.setVisible(this.appEngineHandle, this.viewName, z);
            this.isVisible = z;
        }
    }

    public boolean isVisible() throws RuntimeException {
        boolean z;
        synchronized (this) {
            if (!this.isRunning) {
                throw new RuntimeException(Const.UnInitialized);
            }
            z = this.isVisible;
        }
        return z;
    }

    private int validateViewportWidth(int i) {
        if (i < 1) {
            MapLogger.w("Viewport width can't be less than 1.");
            return 1;
        }
        if (this.mapView.getWidth() >= i) {
            return i;
        }
        MapLogger.w("Param width can't exceed MapView's width(" + this.mapView.getWidth() + ").");
        return this.mapView.getWidth();
    }

    private int validateViewportHeight(int i) {
        if (i < 1) {
            MapLogger.w("Viewport height can't be less than 1.");
            return 1;
        }
        if (this.mapView.getHeight() >= i) {
            return i;
        }
        MapLogger.w("Param height can't exceed MapView's height(" + this.mapView.getHeight() + ").");
        return this.mapView.getHeight();
    }

    public void setViewport(int i, int i2) throws RuntimeException {
        synchronized (this) {
            if (!isRunning()) {
                throw new RuntimeException(Const.UnInitialized);
            }
            APILogger.callStart("width=" + i + ", height=" + i2);
            EngineHandler.setViewport(this.appEngineHandle, this.viewName, this.viewport.left, this.viewport.top, i, i2, this.mapView.getWidth(), this.mapView.getHeight());
        }
    }

    public void setViewport(Rect rect) throws RuntimeException {
        synchronized (this) {
            if (!isRunning()) {
                throw new RuntimeException(Const.UnInitialized);
            }
            EngineHandler.setViewport(this.appEngineHandle, this.viewName, rect.left, rect.top, rect.width(), rect.height(), this.mapView.getWidth(), this.mapView.getHeight());
        }
    }

    public void setViewport(int i, int i2, int i3, int i4) throws RuntimeException {
        synchronized (this) {
            if (!isRunning()) {
                throw new RuntimeException(Const.UnInitialized);
            }
            EngineHandler.setViewport(this.appEngineHandle, this.viewName, i, i2, i3, i4, this.mapView.getWidth(), this.mapView.getHeight());
        }
    }

    public Rect getViewport() throws RuntimeException {
        Rect rect;
        synchronized (this) {
            if (!isRunning()) {
                throw new RuntimeException(Const.UnInitialized);
            }
            rect = new Rect(this.viewport);
        }
        return rect;
    }

    public Logo getLogo() throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        if (this.logo == null) {
            this.logo = new Logo(this);
        }
        return this.logo;
    }
}

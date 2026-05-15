package com.kakao.vectormap.mapwidget;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IGuiDelegate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class InfoWindowLayer {
    private IGuiDelegate delegate;
    private Map<String, InfoWindow> infoWindows = new ConcurrentHashMap();
    private Object tag;

    InfoWindowLayer(IGuiDelegate iGuiDelegate) {
        this.delegate = iGuiDelegate;
    }

    public synchronized InfoWindow addInfoWindow(InfoWindowOptions infoWindowOptions) {
        try {
            try {
                if (infoWindowOptions == null) {
                    throw new RuntimeException("addInfoWindow failure. InfoWindowOptions is null.");
                }
                if (infoWindowOptions.getPosition() == null) {
                    throw new RuntimeException("addInfoWindow failure. LatLng is null.");
                }
                if (infoWindowOptions.getBody() == null) {
                    throw new RuntimeException("addInfoWindow failure. Body is null.");
                }
                this.delegate.addInfoWindow(infoWindowOptions);
                this.infoWindows.put(infoWindowOptions.getId(), new InfoWindow(this.delegate, infoWindowOptions.getPosition(), infoWindowOptions.getId(), infoWindowOptions.isVisible(), infoWindowOptions.getZOrder(), infoWindowOptions.isApplyDpScale(), infoWindowOptions.bodyOffset, infoWindowOptions.tailOffset, infoWindowOptions.body, infoWindowOptions.tail, infoWindowOptions.getTag()));
            } catch (RuntimeException e) {
                MapLogger.e(e);
                return null;
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.infoWindows.get(infoWindowOptions.getId());
    }

    public synchronized void addInfoWindow(InfoWindowOptions infoWindowOptions, OnInfoWindowCreateCallback onInfoWindowCreateCallback) {
        try {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (infoWindowOptions == null) {
                throw new RuntimeException("addInfoWindow failure. InfoWindowOptions is null.");
            }
            if (infoWindowOptions.getPosition() == null) {
                throw new RuntimeException("addInfoWindow failure. LatLng is null.");
            }
            if (infoWindowOptions.getBody() == null) {
                throw new RuntimeException("addInfoWindow failure. Body is null.");
            }
            if (this.infoWindows.containsKey(infoWindowOptions.getId())) {
                if (onInfoWindowCreateCallback != null) {
                    onInfoWindowCreateCallback.onInfoWindowCreated(this, this.infoWindows.get(infoWindowOptions.getId()));
                }
            } else {
                this.delegate.addInfoWindow(infoWindowOptions);
                this.infoWindows.put(infoWindowOptions.getId(), new InfoWindow(this.delegate, infoWindowOptions.getPosition(), infoWindowOptions.getId(), infoWindowOptions.isVisible(), infoWindowOptions.getZOrder(), infoWindowOptions.isApplyDpScale(), infoWindowOptions.bodyOffset, infoWindowOptions.tailOffset, infoWindowOptions.body, infoWindowOptions.tail, infoWindowOptions.getTag()));
                if (onInfoWindowCreateCallback != null) {
                    onInfoWindowCreateCallback.onInfoWindowCreated(this, this.infoWindows.get(infoWindowOptions.getId()));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized InfoWindow getInfoWindow(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.infoWindows.get(str);
    }

    public synchronized InfoWindow[] getAllInfoWindows() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return (InfoWindow[]) this.infoWindows.values().toArray(new InfoWindow[this.infoWindows.size()]);
    }

    public synchronized void setVisible(boolean z) {
        try {
            this.delegate.setInfoWindowLayerVisible(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(InfoWindow infoWindow) {
        try {
            this.delegate.removeInfoWindow(infoWindow.getId());
            this.infoWindows.remove(infoWindow.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAll() {
        try {
            this.delegate.removeAllInfoWindow();
            this.infoWindows.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setTag(Object obj) {
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }
}

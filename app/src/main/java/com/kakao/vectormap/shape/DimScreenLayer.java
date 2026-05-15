package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.IDimScreenDelegate;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes4.dex */
public class DimScreenLayer {
    private IDimScreenDelegate delegate;
    private Object tag;

    public interface OnPolygonCreateCallback {
        void onPolygonCreated(DimScreenLayer dimScreenLayer, Polygon... polygonArr);
    }

    DimScreenLayer(IDimScreenDelegate iDimScreenDelegate) {
        this.delegate = iDimScreenDelegate;
        iDimScreenDelegate.setShapeFactory(new ShapeFactory());
        this.delegate.setDimScreenLayer(this);
    }

    private void checkPolygonOptions(PolygonOptions... polygonOptionsArr) throws RuntimeException {
        if (polygonOptionsArr == null || polygonOptionsArr.length == 0) {
            throw new RuntimeException("addPolygon failure. PolygonOptions is null or empty.");
        }
    }

    public synchronized Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            checkPolygonOptions(polygonOptions);
            if (this.delegate.containPolygon(polygonOptions.getPolygonId())) {
                return this.delegate.getPolygon(polygonOptions.getPolygonId());
            }
            this.delegate.addMultiPolygon(null, polygonOptions, true, null);
            return this.delegate.newPolygon(polygonOptions);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
    }

    public synchronized Polygon[] addMapPointPolygons(PolygonOptions... polygonOptionsArr) {
        try {
            checkPolygonOptions(polygonOptionsArr);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (PolygonOptions polygonOptions : polygonOptionsArr) {
                if (!this.delegate.containPolygon(polygonOptions.getPolygonId())) {
                    linkedHashSet.add(polygonOptions);
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.delegate.addMapPointPolygons(null, (PolygonOptions[]) linkedHashSet.toArray(new PolygonOptions[linkedHashSet.size()]), polygonOptionsArr);
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.newPolygons(polygonOptionsArr);
    }

    public synchronized void addMapPointPolygons(PolygonOptions[] polygonOptionsArr, OnPolygonCreateCallback onPolygonCreateCallback) {
        try {
            checkPolygonOptions(polygonOptionsArr);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (PolygonOptions polygonOptions : polygonOptionsArr) {
                if (!this.delegate.containPolygon(polygonOptions.getPolygonId())) {
                    linkedHashSet.add(polygonOptions);
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.delegate.addMapPointPolygons(onPolygonCreateCallback, (PolygonOptions[]) linkedHashSet.toArray(new PolygonOptions[linkedHashSet.size()]), polygonOptionsArr);
            } else if (onPolygonCreateCallback != null) {
                onPolygonCreateCallback.onPolygonCreated(this, this.delegate.newPolygons(polygonOptionsArr));
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Polygon[] addDotPointPolygons(PolygonOptions... polygonOptionsArr) {
        try {
            checkPolygonOptions(polygonOptionsArr);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (PolygonOptions polygonOptions : polygonOptionsArr) {
                if (!this.delegate.containPolygon(polygonOptions.getPolygonId())) {
                    linkedHashSet.add(polygonOptions);
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.delegate.addDotPointPolygons(null, (PolygonOptions[]) linkedHashSet.toArray(new PolygonOptions[linkedHashSet.size()]), polygonOptionsArr);
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.newPolygons(polygonOptionsArr);
    }

    public synchronized void addDotPointPolygons(PolygonOptions[] polygonOptionsArr, OnPolygonCreateCallback onPolygonCreateCallback) {
        try {
            checkPolygonOptions(polygonOptionsArr);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (PolygonOptions polygonOptions : polygonOptionsArr) {
                if (!this.delegate.containPolygon(polygonOptions.getPolygonId())) {
                    linkedHashSet.add(polygonOptions);
                }
            }
            if (!linkedHashSet.isEmpty()) {
                this.delegate.addDotPointPolygons(onPolygonCreateCallback, (PolygonOptions[]) linkedHashSet.toArray(new PolygonOptions[linkedHashSet.size()]), polygonOptionsArr);
            } else if (onPolygonCreateCallback != null) {
                onPolygonCreateCallback.onPolygonCreated(this, this.delegate.newPolygons(polygonOptionsArr));
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized Polygon getPolygon(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolygon(str);
    }

    public synchronized Polygon[] getAllPolygons() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getAllPolygons();
    }

    public synchronized void setColor(int i) {
        try {
            this.delegate.setDimScreenColor(i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setVisible(boolean z) {
        try {
            this.delegate.setVisible(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(Polygon... polygonArr) {
        try {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (polygonArr == null) {
                throw new RuntimeException("DimScreenLayer remove polygon failure. polygons is null.");
            }
            if (polygonArr.length <= 1) {
                this.delegate.removePolygon(true, polygonArr[0].getLayerId(), polygonArr[0].getId());
                this.delegate.remove(polygonArr[0].getId());
            } else {
                int length = polygonArr.length;
                String[] strArr = new String[length];
                String[] strArr2 = new String[length];
                for (int i = 0; i < length; i++) {
                    strArr2[i] = polygonArr[i].getId();
                    strArr[i] = polygonArr[i].getLayerId();
                }
                this.delegate.removePolygons(strArr, strArr2, true);
                for (Polygon polygon : polygonArr) {
                    this.delegate.remove(polygon.getId());
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void setDimScreenCover(DimScreenCover dimScreenCover) {
        try {
            this.delegate.setDimScreenCover(dimScreenCover);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearAll() {
        try {
            this.delegate.removeAllShape(true);
            this.delegate.clearAllPolygon();
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

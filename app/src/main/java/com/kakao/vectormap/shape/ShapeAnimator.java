package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.animation.Interpolation;
import com.kakao.vectormap.internal.IVectorDelegate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ShapeAnimator {
    private final String animatorId;
    private final IVectorDelegate delegate;
    private final int duration;
    private boolean hideShapeAtStop;
    private final Interpolation interpolation;
    private final List<Polygon> polygons = new ArrayList();
    private final int repeatCount;
    private boolean toDimScreen;

    ShapeAnimator(IVectorDelegate iVectorDelegate, String str, boolean z, int i, int i2, boolean z2, Interpolation interpolation) {
        this.delegate = iVectorDelegate;
        this.animatorId = str;
        this.repeatCount = i;
        this.duration = i2;
        this.toDimScreen = z;
        this.hideShapeAtStop = z2;
        this.interpolation = interpolation;
    }

    public synchronized void addPolygons(Polygon... polygonArr) {
        if (polygonArr != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (polygonArr.length != 0) {
                this.polygons.addAll(Arrays.asList(polygonArr));
                return;
            }
        }
        MapLogger.e("ShapeAnimator addPolygons failure. polygons is invalid.");
    }

    public synchronized void addPolygons(List<Polygon> list) {
        if (list != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (!list.isEmpty()) {
                this.polygons.addAll(list);
                return;
            }
        }
        MapLogger.e("ShapeAnimator addPolygons failure. polygons is invalid.");
    }

    public List<Polygon> getPolygons() {
        return new ArrayList(this.polygons);
    }

    public synchronized void start() {
        try {
            this.delegate.startAnimator(this.animatorId, this.toDimScreen, this.polygons);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void stop() {
        try {
            this.delegate.stopAnimator(this.animatorId, this.hideShapeAtStop, this.toDimScreen);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void stop(boolean z) {
        try {
            this.delegate.stopAnimator(this.animatorId, z, this.toDimScreen);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAllPolygon() {
        List<Polygon> list;
        try {
            list = this.polygons;
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (list != null && !list.isEmpty()) {
            String[] strArr = new String[this.polygons.size()];
            String[] strArr2 = new String[this.polygons.size()];
            for (int i = 0; i < this.polygons.size(); i++) {
                strArr[i] = this.polygons.get(i).getLayerId();
                strArr2[i] = this.polygons.get(i).getId();
            }
            this.delegate.removePolygons(strArr, strArr2, this.toDimScreen);
            this.polygons.clear();
            return;
        }
        MapLogger.e("ShapeAnimator removeAllPolygon failure. polygons is invalid.");
    }

    public void setHideShapeAtStop(boolean z) {
        this.hideShapeAtStop = z;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public String getId() {
        return this.animatorId;
    }

    public boolean isHideShapeAtStop() {
        return this.hideShapeAtStop;
    }
}

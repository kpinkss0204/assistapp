package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineOptions {
    public List<DotPoints> dotPoints;
    public List<MapPoints> mapPoints;
    public String polylineId;
    public PolylineStylesSet stylesSet;
    public Object tag;
    public int zOrder = 10001;
    public boolean visible = true;

    PolylineOptions(String str, List<MapPoints> list, List<DotPoints> list2, List<PolylineStyles> list3) {
        this.polylineId = "";
        this.polylineId = MapUtils.getUniqueId(str);
        this.mapPoints = list == null ? new ArrayList() : new ArrayList(list);
        this.dotPoints = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.stylesSet = PolylineStylesSet.from(list3);
    }

    public static PolylineOptions from() {
        return new PolylineOptions("", null, null, new ArrayList());
    }

    public static PolylineOptions from(String str) {
        return new PolylineOptions(str, null, null, new ArrayList());
    }

    public static PolylineOptions from(MapPoints mapPoints, PolylineStylesSet polylineStylesSet) {
        if (mapPoints == null || polylineStylesSet == null) {
            MapLogger.e("PolylineOptions create failure. MapPoints or PolylineStyles is null.");
            return null;
        }
        return new PolylineOptions("", Arrays.asList(mapPoints), null, polylineStylesSet.getStyles());
    }

    public static PolylineOptions from(MapPoints mapPoints, PolylineStyles polylineStyles) {
        if (mapPoints == null || polylineStyles == null) {
            MapLogger.e("PolylineOptions create failure. MapPoints or PolylineStyles is null.");
            return null;
        }
        return new PolylineOptions("", Arrays.asList(mapPoints), null, Arrays.asList(polylineStyles));
    }

    public static PolylineOptions from(MapPoints mapPoints, PolylineStyle... polylineStyleArr) {
        if (mapPoints == null || polylineStyleArr == null) {
            MapLogger.e("PolylineOptions create failure. MapPoints or PolylineStyle is null.");
            return null;
        }
        return new PolylineOptions("", Arrays.asList(mapPoints), null, Arrays.asList(PolylineStyles.from(polylineStyleArr)));
    }

    public static PolylineOptions from(MapPoints mapPoints, float f, int i) {
        if (mapPoints == null) {
            MapLogger.e("PolylineOptions create failure. MapPoints is null.");
            return null;
        }
        return new PolylineOptions("", Arrays.asList(mapPoints), null, Arrays.asList(PolylineStyles.from(f, i)));
    }

    public static PolylineOptions from(DotPoints dotPoints, PolylineStylesSet polylineStylesSet) {
        if (dotPoints == null || polylineStylesSet == null) {
            MapLogger.e("PolylineOptions create failure. DotPoints or PolylineStylesSet is null.");
            return null;
        }
        return new PolylineOptions("", null, Arrays.asList(dotPoints), polylineStylesSet.getStyles());
    }

    public static PolylineOptions from(DotPoints dotPoints, PolylineStyles polylineStyles) {
        if (dotPoints == null || polylineStyles == null) {
            MapLogger.e("PolylineOptions create failure. DotPoints or PolylineStyles is null.");
            return null;
        }
        return new PolylineOptions("", null, Arrays.asList(dotPoints), Arrays.asList(polylineStyles));
    }

    public static PolylineOptions from(DotPoints dotPoints, PolylineStyle... polylineStyleArr) {
        if (dotPoints == null || polylineStyleArr == null) {
            MapLogger.e("PolylineOptions create failure. DotPoints or PolylineStyles is null.");
            return null;
        }
        return new PolylineOptions("", null, Arrays.asList(dotPoints), Arrays.asList(PolylineStyles.from(polylineStyleArr)));
    }

    public static PolylineOptions from(DotPoints dotPoints, float f, int i) {
        if (dotPoints == null) {
            MapLogger.e("PolylineOptions create failure. DotPoints is null.");
            return null;
        }
        return new PolylineOptions("", null, Arrays.asList(dotPoints), Arrays.asList(PolylineStyles.from(f, i)));
    }

    public PolylineOptions setStylesSet(PolylineStylesSet polylineStylesSet) {
        if (polylineStylesSet == null) {
            MapLogger.e("setPolylineStylesSet failure. PolylineStylesSet is null.");
            return this;
        }
        this.stylesSet = PolylineStylesSet.from(polylineStylesSet);
        return this;
    }

    public PolylineOptions setMapPoints(MapPoints... mapPointsArr) {
        if (mapPointsArr == null) {
            return this;
        }
        this.mapPoints.clear();
        this.mapPoints.addAll(Arrays.asList(mapPointsArr));
        return this;
    }

    public PolylineOptions setMapPoints(List<MapPoints> list) {
        if (list == null) {
            return this;
        }
        this.mapPoints.clear();
        this.mapPoints.addAll(list);
        return this;
    }

    public PolylineOptions setDotPoints(DotPoints... dotPointsArr) {
        if (dotPointsArr == null) {
            return this;
        }
        this.dotPoints.clear();
        this.dotPoints.addAll(Arrays.asList(dotPointsArr));
        return this;
    }

    public PolylineOptions setDotPoints(List<DotPoints> list) {
        if (list == null) {
            return this;
        }
        this.dotPoints.clear();
        this.dotPoints.addAll(list);
        return this;
    }

    public PolylineOptions addPolyline(MapPoints mapPoints, PolylineStyles polylineStyles) {
        if (mapPoints == null || polylineStyles == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolylineStyles(polylineStyles);
        return this;
    }

    public PolylineOptions addPolyline(MapPoints mapPoints, PolylineStyle... polylineStyleArr) {
        if (mapPoints == null || polylineStyleArr == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolylineStyles(PolylineStyles.from(polylineStyleArr));
        return this;
    }

    public PolylineOptions addPolyline(MapPoints mapPoints, List<PolylineStyle> list) {
        if (mapPoints == null || list == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolylineStyles((PolylineStyles[]) list.toArray(new PolylineStyles[list.size()]));
        return this;
    }

    public PolylineOptions addPolyline(MapPoints mapPoints, int i, int i2) {
        if (mapPoints == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolylineStyles(PolylineStyles.from(i, i2));
        return this;
    }

    public PolylineOptions addPolyline(DotPoints dotPoints, PolylineStyles polylineStyles) {
        if (this.mapPoints == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolylineStyles(polylineStyles);
        return this;
    }

    public PolylineOptions addPolyline(DotPoints dotPoints, PolylineStyle... polylineStyleArr) {
        if (this.mapPoints == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolylineStyles(PolylineStyles.from(polylineStyleArr));
        return this;
    }

    public PolylineOptions addPolyline(DotPoints dotPoints, List<PolylineStyle> list) {
        if (this.mapPoints == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolylineStyles(PolylineStyles.from(list));
        return this;
    }

    public PolylineOptions addPolyline(DotPoints dotPoints, int i, int i2) {
        if (this.mapPoints == null) {
            MapLogger.e("PolylineOptions.addPolyline failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolylineStyles(PolylineStyles.from(i, i2));
        return this;
    }

    public List<DotPoints> getDotPoints() {
        return this.dotPoints == null ? new ArrayList() : new ArrayList(this.dotPoints);
    }

    public List<MapPoints> getMapPoints() {
        return this.mapPoints == null ? new ArrayList() : new ArrayList(this.mapPoints);
    }

    public PolylineStylesSet getStylesSet() {
        return this.stylesSet;
    }

    public PolylineOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public PolylineOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public PolylineOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public String getPolylineId() {
        return this.polylineId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolylineOptions) {
            return Objects.equals(getPolylineId(), ((PolylineOptions) obj).getPolylineId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getPolylineId());
    }
}

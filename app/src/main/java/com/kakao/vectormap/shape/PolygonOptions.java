package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolygonOptions {
    public List<DotPoints> dotPoints;
    public List<MapPoints> mapPoints;
    public String polygonId;
    public PolygonStylesSet stylesSet;
    public Object tag;
    public int zOrder = 10001;
    public boolean visible = true;

    PolygonOptions(String str, List<MapPoints> list, List<DotPoints> list2, List<PolygonStyles> list3) {
        this.polygonId = "";
        this.polygonId = MapUtils.generateId(str);
        this.mapPoints = list == null ? new ArrayList() : new ArrayList(list);
        this.dotPoints = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.stylesSet = PolygonStylesSet.from(list3);
    }

    public static PolygonOptions from() {
        return new PolygonOptions("", null, null, new ArrayList());
    }

    public static PolygonOptions from(String str) {
        return new PolygonOptions(str, null, null, new ArrayList());
    }

    public static PolygonOptions from(MapPoints mapPoints, PolygonStylesSet polygonStylesSet) {
        if (mapPoints == null || polygonStylesSet == null) {
            MapLogger.e("PolygonOptions create failure. MapPoints or PolygonStylesSet is null.");
            return null;
        }
        return new PolygonOptions("", Arrays.asList(mapPoints), null, polygonStylesSet.getStyles());
    }

    public static PolygonOptions from(MapPoints mapPoints, PolygonStyles polygonStyles) {
        if (mapPoints == null || polygonStyles == null) {
            MapLogger.e("PolygonOptions create failure. MapPoints or PolygonStyles is null.");
            return null;
        }
        return new PolygonOptions("", Arrays.asList(mapPoints), null, Arrays.asList(polygonStyles));
    }

    public static PolygonOptions from(MapPoints mapPoints, PolygonStyle... polygonStyleArr) {
        if (mapPoints == null || polygonStyleArr == null) {
            MapLogger.e("PolygonOptions create failure. MapPoints or PolygonStyles is null.");
            return null;
        }
        return new PolygonOptions("", Arrays.asList(mapPoints), null, Arrays.asList(PolygonStyles.from(polygonStyleArr)));
    }

    public static PolygonOptions from(MapPoints mapPoints, int i) {
        if (mapPoints == null) {
            MapLogger.e("PolygonOptions create failure. MapPoints is null.");
            return null;
        }
        return new PolygonOptions("", Arrays.asList(mapPoints), null, Arrays.asList(PolygonStyles.from(i)));
    }

    public static PolygonOptions from(DotPoints dotPoints, PolygonStylesSet polygonStylesSet) {
        if (dotPoints == null || polygonStylesSet == null) {
            MapLogger.e("PolygonOptions create failure. DotPoints or PolygonStylesSet is null.");
            return null;
        }
        return new PolygonOptions("", null, Arrays.asList(dotPoints), polygonStylesSet.getStyles());
    }

    public static PolygonOptions from(DotPoints dotPoints, PolygonStyles polygonStyles) {
        if (dotPoints == null || polygonStyles == null) {
            MapLogger.e("PolygonOptions create failure. DotPoints or PolygonStyles is null.");
            return null;
        }
        return new PolygonOptions("", null, Arrays.asList(dotPoints), Arrays.asList(polygonStyles));
    }

    public static PolygonOptions from(DotPoints dotPoints, PolygonStyle... polygonStyleArr) {
        if (dotPoints == null || polygonStyleArr == null) {
            MapLogger.e("PolygonOptions create failure. DotPoints or PolygonStyles is null.");
            return null;
        }
        return new PolygonOptions("", null, Arrays.asList(dotPoints), Arrays.asList(PolygonStyles.from(polygonStyleArr)));
    }

    public static PolygonOptions from(DotPoints dotPoints, int i) {
        if (dotPoints == null) {
            MapLogger.e("PolygonOptions create failure. DotPoints is null.");
            return null;
        }
        return new PolygonOptions("", null, Arrays.asList(dotPoints), Arrays.asList(PolygonStyles.from(i)));
    }

    public PolygonOptions setStylesSet(PolygonStylesSet polygonStylesSet) {
        if (polygonStylesSet == null) {
            MapLogger.e("setPolygonStylesSet failure. PolygonStylesSet is null.");
            return this;
        }
        this.stylesSet = PolygonStylesSet.from(polygonStylesSet);
        return this;
    }

    public PolygonOptions setMapPoints(MapPoints... mapPointsArr) {
        if (mapPointsArr == null) {
            return this;
        }
        this.mapPoints.clear();
        this.mapPoints.addAll(Arrays.asList(mapPointsArr));
        return this;
    }

    public PolygonOptions setMapPoints(List<MapPoints> list) {
        if (list == null) {
            return this;
        }
        this.mapPoints.clear();
        this.mapPoints.addAll(list);
        return this;
    }

    public PolygonOptions setDotPoints(DotPoints... dotPointsArr) {
        if (dotPointsArr == null) {
            return this;
        }
        this.dotPoints.clear();
        this.dotPoints.addAll(Arrays.asList(dotPointsArr));
        return this;
    }

    public PolygonOptions setDotPoints(List<DotPoints> list) {
        if (list == null) {
            return this;
        }
        this.dotPoints.clear();
        this.dotPoints.addAll(list);
        return this;
    }

    public PolygonOptions addPolygon(MapPoints mapPoints, PolygonStyles polygonStyles) {
        if (mapPoints == null || polygonStyles == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolygonStyles(polygonStyles);
        return this;
    }

    public PolygonOptions addPolygon(MapPoints mapPoints, PolygonStyle... polygonStyleArr) {
        if (mapPoints == null || polygonStyleArr == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolygonStyles(PolygonStyles.from(polygonStyleArr));
        return this;
    }

    public PolygonOptions addPolygon(MapPoints mapPoints, List<PolygonStyle> list) {
        if (mapPoints == null || list == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolygonStyles((PolygonStyles[]) list.toArray(new PolygonStyles[list.size()]));
        return this;
    }

    public PolygonOptions addPolygon(MapPoints mapPoints, int i) {
        if (mapPoints == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.mapPoints.add(mapPoints);
        this.stylesSet.addPolygonStyles(PolygonStyles.from(i));
        return this;
    }

    public PolygonOptions addPolygon(DotPoints dotPoints, PolygonStyles polygonStyles) {
        if (this.mapPoints == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolygonStyles(polygonStyles);
        return this;
    }

    public PolygonOptions addPolygon(DotPoints dotPoints, PolygonStyle... polygonStyleArr) {
        if (this.mapPoints == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolygonStyles(PolygonStyles.from(polygonStyleArr));
        return this;
    }

    public PolygonOptions addPolygon(DotPoints dotPoints, List<PolygonStyle> list) {
        if (this.mapPoints == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolygonStyles(PolygonStyles.from(list));
        return this;
    }

    public PolygonOptions addPolygon(DotPoints dotPoints, int i) {
        if (this.mapPoints == null) {
            MapLogger.e("PolygonOptions.addPolygon failure. param is null.");
            return this;
        }
        this.dotPoints.add(dotPoints);
        this.stylesSet.addPolygonStyles(PolygonStyles.from(i));
        return this;
    }

    public List<DotPoints> getDotPoints() {
        return this.dotPoints == null ? new ArrayList() : new ArrayList(this.dotPoints);
    }

    public List<MapPoints> getMapPoints() {
        return this.mapPoints == null ? new ArrayList() : new ArrayList(this.mapPoints);
    }

    public PolygonStylesSet getStylesSet() {
        return this.stylesSet;
    }

    public PolygonOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public PolygonOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public PolygonOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public String getPolygonId() {
        return this.polygonId;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolygonOptions) {
            return Objects.equals(getPolygonId(), ((PolygonOptions) obj).getPolygonId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getPolygonId());
    }
}

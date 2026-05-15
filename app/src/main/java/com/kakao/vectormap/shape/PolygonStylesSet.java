package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolygonStylesSet {
    private String styleId;
    private List<PolygonStyles> styles;

    PolygonStylesSet(String str, List<PolygonStyles> list) {
        this.styleId = str;
        this.styles = new ArrayList(list);
    }

    public static PolygonStylesSet from(PolygonStylesSet polygonStylesSet) {
        if (polygonStylesSet == null) {
            MapLogger.e("PolygonStylesSet create failure. PolygonStyles cannot be null.");
            return null;
        }
        return new PolygonStylesSet(polygonStylesSet.getStyleId(), polygonStylesSet.getStyles());
    }

    public static PolygonStylesSet from(String str) {
        return new PolygonStylesSet(str, new ArrayList());
    }

    public static PolygonStylesSet from(PolygonStyles... polygonStylesArr) {
        if (polygonStylesArr == null) {
            MapLogger.e("PolygonStylesSet create failure. PolygonStyles null or empty.");
            return null;
        }
        return new PolygonStylesSet("", Arrays.asList(polygonStylesArr));
    }

    public static PolygonStylesSet from(String str, PolygonStyles... polygonStylesArr) {
        if (polygonStylesArr == null) {
            MapLogger.e("PolygonStylesSet create failure. PolygonStyles null or empty.");
            return null;
        }
        return new PolygonStylesSet(str, Arrays.asList(polygonStylesArr));
    }

    public static PolygonStylesSet from(List<PolygonStyles> list) {
        if (list == null) {
            MapLogger.e("PolygonStylesSet create failure. PolygonStyles null or empty.");
            return null;
        }
        return new PolygonStylesSet("", list);
    }

    public static PolygonStylesSet from(String str, List<PolygonStyles> list) {
        if (list == null) {
            MapLogger.e("PolygonStylesSet create failure. PolygonStyles null or empty.");
            return null;
        }
        return new PolygonStylesSet(str, list);
    }

    public PolygonStylesSet addPolygonStyles(PolygonStyles... polygonStylesArr) {
        if (polygonStylesArr == null) {
            MapLogger.w("PolygonStylesSet.addPolygonStyles return. PolygonStyles null or empty.");
            return this;
        }
        this.styles.addAll(Arrays.asList(polygonStylesArr));
        return this;
    }

    public String getStyleId() {
        String str = this.styleId;
        if (str == null || str.isEmpty()) {
            this.styleId = String.valueOf(hashCode());
        }
        return this.styleId;
    }

    public List<PolygonStyles> getStyles() {
        return new ArrayList(this.styles);
    }

    public int getStylesCount() {
        return this.styles.size();
    }

    public int hashCode() {
        int iHash = Objects.hash(this.styleId);
        Iterator<PolygonStyles> it = this.styles.iterator();
        while (it.hasNext()) {
            iHash = (iHash * 31) + it.next().hashCode();
        }
        return iHash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolygonStylesSet)) {
            return false;
        }
        PolygonStylesSet polygonStylesSet = (PolygonStylesSet) obj;
        return Objects.equals(getStyleId(), polygonStylesSet.getStyleId()) && Objects.equals(getStyles(), polygonStylesSet.getStyles());
    }
}

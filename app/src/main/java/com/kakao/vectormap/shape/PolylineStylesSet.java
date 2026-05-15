package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineStylesSet {
    private PolylineCap polylineCap;
    private String styleId;
    private List<PolylineStyles> styles;

    public PolylineStylesSet(String str, List<PolylineStyles> list) {
        this.styleId = "";
        this.styles = new ArrayList();
        if (list == null || list.isEmpty()) {
            MapLogger.e("PolylineStylesSet create failure. PolylineStyles cannot be null or empty.");
            return;
        }
        this.styleId = str;
        this.polylineCap = PolylineCap.Round;
        this.styles = new ArrayList(list);
    }

    public void checkStyleId() {
        String str = this.styleId;
        if (str == null || str.isEmpty()) {
            this.styleId = String.valueOf(hashCode());
        }
    }

    public static PolylineStylesSet from(PolylineStylesSet polylineStylesSet) {
        if (polylineStylesSet == null) {
            return null;
        }
        return new PolylineStylesSet(polylineStylesSet.getStyleId(), polylineStylesSet.getStyles());
    }

    public static PolylineStylesSet from(String str) {
        return new PolylineStylesSet(str, new ArrayList());
    }

    public static PolylineStylesSet from(PolylineStyles... polylineStylesArr) {
        return new PolylineStylesSet("", Arrays.asList(polylineStylesArr));
    }

    public static PolylineStylesSet from(String str, PolylineStyles... polylineStylesArr) {
        return new PolylineStylesSet(str, Arrays.asList(polylineStylesArr));
    }

    public static PolylineStylesSet from(List<PolylineStyles> list) {
        return new PolylineStylesSet("", list);
    }

    public static PolylineStylesSet from(String str, List<PolylineStyles> list) {
        return new PolylineStylesSet(str, list);
    }

    public PolylineStylesSet addPolylineStyles(PolylineStyles... polylineStylesArr) {
        this.styles.addAll(Arrays.asList(polylineStylesArr));
        return this;
    }

    public PolylineStylesSet setPolylineCap(PolylineCap polylineCap) {
        this.polylineCap = polylineCap;
        return this;
    }

    public PolylineCap getPolylineCap() {
        return this.polylineCap;
    }

    public String getStyleId() {
        return this.styleId;
    }

    public List<PolylineStyles> getStyles() {
        return this.styles;
    }

    public int getStylesCount() {
        return this.styles.size();
    }

    public int hashCode() {
        int iHash = Objects.hash(this.styleId);
        Iterator<PolylineStyles> it = this.styles.iterator();
        while (it.hasNext()) {
            iHash = (iHash * 31) + it.next().hashCode();
        }
        return iHash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolylineStylesSet)) {
            return false;
        }
        PolylineStylesSet polylineStylesSet = (PolylineStylesSet) obj;
        return Objects.equals(getStyleId(), polylineStylesSet.getStyleId()) && getPolylineCap() == polylineStylesSet.getPolylineCap() && Objects.equals(getStyles(), polylineStylesSet.getStyles());
    }
}

package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolygonStyles {
    public PolygonStyle[] styles;

    PolygonStyles(PolygonStyle... polygonStyleArr) {
        if (polygonStyleArr == null) {
            this.styles = new PolygonStyle[0];
        } else {
            this.styles = polygonStyleArr;
        }
    }

    public static PolygonStyles from(PolygonStyle... polygonStyleArr) {
        if (polygonStyleArr == null) {
            MapLogger.e("PolygonStyles create failure. PolygonStyle is null.");
            return null;
        }
        return new PolygonStyles(polygonStyleArr);
    }

    public static PolygonStyles from(List<PolygonStyle> list) {
        if (list == null) {
            MapLogger.e("PolygonStyles create failure. PolygonStyle is null.");
            return null;
        }
        return new PolygonStyles((PolygonStyle[]) list.toArray(new PolygonStyle[list.size()]));
    }

    public static PolygonStyles from(int i) {
        return new PolygonStyles(PolygonStyle.from(i));
    }

    public static PolygonStyles from(int i, float f, int i2) {
        return new PolygonStyles(PolygonStyle.from(i, f, i2));
    }

    public static PolygonStyles from(int i, int i2, float f, int i3) {
        return new PolygonStyles(PolygonStyle.from(i, i2, f, i3));
    }

    public PolygonStyle[] getStyles() {
        return this.styles;
    }

    public int hashCode() {
        PolygonStyle[] polygonStyleArr = this.styles;
        int iHashCode = 31;
        if (polygonStyleArr != null && polygonStyleArr.length > 0) {
            for (PolygonStyle polygonStyle : polygonStyleArr) {
                iHashCode = (iHashCode * 31) + polygonStyle.hashCode();
            }
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolygonStyles) {
            return Objects.deepEquals(getStyles(), ((PolygonStyles) obj).getStyles());
        }
        return false;
    }
}

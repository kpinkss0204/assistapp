package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineStyles {
    public PolylineStyle[] styles;

    PolylineStyles(PolylineStyle... polylineStyleArr) {
        if (polylineStyleArr == null) {
            this.styles = new PolylineStyle[0];
        } else {
            this.styles = polylineStyleArr;
        }
    }

    public static PolylineStyles from(PolylineStyle... polylineStyleArr) {
        if (polylineStyleArr == null) {
            MapLogger.e("PolylineStyles create failure. PolylineStyle is null.");
            return null;
        }
        return new PolylineStyles(polylineStyleArr);
    }

    public static PolylineStyles from(List<PolylineStyle> list) {
        if (list == null) {
            MapLogger.e("PolylineStyles create failure. PolylineStyle is null.");
            return null;
        }
        return new PolylineStyles((PolylineStyle[]) list.toArray(new PolylineStyle[list.size()]));
    }

    public static PolylineStyles from(float f, int i) {
        return new PolylineStyles(PolylineStyle.from(f, i));
    }

    public PolylineStyle[] getStyles() {
        return this.styles;
    }

    public int hashCode() {
        PolylineStyle[] polylineStyleArr = this.styles;
        int iHashCode = 31;
        if (polylineStyleArr != null && polylineStyleArr.length > 0) {
            for (PolylineStyle polylineStyle : polylineStyleArr) {
                iHashCode = (iHashCode * 31) + polylineStyle.hashCode();
            }
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolylineStyles) {
            return Objects.deepEquals(getStyles(), ((PolylineStyles) obj).getStyles());
        }
        return false;
    }
}

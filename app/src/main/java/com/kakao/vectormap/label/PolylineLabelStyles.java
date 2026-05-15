package com.kakao.vectormap.label;

import com.kakao.vectormap.internal.IMapResourceManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineLabelStyles {
    public String styleId;
    public PolylineLabelStyle[] styles;

    PolylineLabelStyles(String str, PolylineLabelStyle[] polylineLabelStyleArr) {
        this.styleId = "";
        this.styles = polylineLabelStyleArr;
        this.styleId = str == null ? "" : str;
    }

    void checkStyles(IMapResourceManager iMapResourceManager) {
        String str = this.styleId;
        if (str == null || str.isEmpty()) {
            this.styleId = String.valueOf(hashCode());
        }
        int i = 0;
        while (true) {
            PolylineLabelStyle[] polylineLabelStyleArr = this.styles;
            if (i >= polylineLabelStyleArr.length) {
                return;
            }
            PolylineLabelStyle polylineLabelStyle = polylineLabelStyleArr[i];
            i++;
        }
    }

    public static PolylineLabelStyles from(PolylineLabelStyle... polylineLabelStyleArr) {
        return new PolylineLabelStyles("", polylineLabelStyleArr);
    }

    public static PolylineLabelStyles from(String str, PolylineLabelStyle... polylineLabelStyleArr) {
        return new PolylineLabelStyles(str, polylineLabelStyleArr);
    }

    public static PolylineLabelStyles from(Collection<PolylineLabelStyle> collection) {
        return new PolylineLabelStyles("", (PolylineLabelStyle[]) collection.toArray(new PolylineLabelStyle[collection.size()]));
    }

    public static PolylineLabelStyles from(String str, Collection<PolylineLabelStyle> collection) {
        return new PolylineLabelStyles(str, (PolylineLabelStyle[]) collection.toArray(new PolylineLabelStyle[collection.size()]));
    }

    public String getStyleId() {
        return this.styleId;
    }

    public PolylineLabelStyle[] getStyles() {
        return this.styles;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolylineLabelStyles)) {
            return false;
        }
        PolylineLabelStyles polylineLabelStyles = (PolylineLabelStyles) obj;
        return Objects.equals(this.styleId, polylineLabelStyles.styleId) && Arrays.deepEquals(this.styles, polylineLabelStyles.styles);
    }

    public int hashCode() {
        int iHash = Objects.hash(this.styleId);
        PolylineLabelStyle[] polylineLabelStyleArr = this.styles;
        if (polylineLabelStyleArr != null && polylineLabelStyleArr.length > 0) {
            for (PolylineLabelStyle polylineLabelStyle : polylineLabelStyleArr) {
                iHash = (iHash * 31) + polylineLabelStyle.hashCode();
            }
        }
        return iHash;
    }
}

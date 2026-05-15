package com.kakao.vectormap.label;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LabelStyles {
    public String styleId;
    public LabelStyle[] styles;

    LabelStyles(String str, LabelStyle... labelStyleArr) {
        this.styles = labelStyleArr;
        this.styleId = str;
        invalidate();
    }

    public void invalidate() {
        String str = this.styleId;
        this.styleId = (str == null || str.isEmpty()) ? String.valueOf(hashCode()) : this.styleId;
    }

    public static LabelStyles from(LabelStyle... labelStyleArr) {
        return new LabelStyles("", labelStyleArr);
    }

    public static LabelStyles from(List<LabelStyle> list) {
        return new LabelStyles("", (LabelStyle[]) list.toArray(new LabelStyle[list.size()]));
    }

    public static LabelStyles from(String str, List<LabelStyle> list) {
        return new LabelStyles(str, (LabelStyle[]) list.toArray(new LabelStyle[list.size()]));
    }

    public static LabelStyles from(String str, LabelStyle... labelStyleArr) {
        return new LabelStyles(str, labelStyleArr);
    }

    public String getStyleId() {
        return this.styleId;
    }

    public LabelStyle[] getStyles() {
        return this.styles;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LabelStyles)) {
            return false;
        }
        LabelStyles labelStyles = (LabelStyles) obj;
        if (Objects.equals(this.styleId, labelStyles.styleId) && this.styles == null) {
            return labelStyles.styles == null;
        }
        return Arrays.deepEquals(this.styles, labelStyles.styles);
    }

    public int hashCode() {
        int iHash = Objects.hash(this.styleId);
        LabelStyle[] labelStyleArr = this.styles;
        if (labelStyleArr != null && labelStyleArr.length > 0) {
            for (LabelStyle labelStyle : labelStyleArr) {
                iHash = (iHash * 31) + labelStyle.hashCode();
            }
        }
        return iHash;
    }
}

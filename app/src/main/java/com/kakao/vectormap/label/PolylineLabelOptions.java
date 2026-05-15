package com.kakao.vectormap.label;

import androidx.core.view.ViewCompat;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.internal.ILabelOptions;
import com.kakao.vectormap.utils.MapUtils;
import java.util.Collection;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineLabelOptions extends ILabelOptions {
    private LatLng[] pos;
    private PolylineLabelStyles styles;
    private Object tag;

    PolylineLabelOptions(String str, String str2, LatLng[] latLngArr) {
        this.labelId = MapUtils.getUniqueId(str);
        this.texts = new String[1];
        this.texts[0] = str2;
        this.styles = PolylineLabelStyles.from(PolylineLabelStyle.from(24, ViewCompat.MEASURED_STATE_MASK));
        this.pos = latLngArr;
    }

    public static PolylineLabelOptions from(String str, LatLng... latLngArr) {
        return new PolylineLabelOptions("", str, latLngArr);
    }

    public static PolylineLabelOptions from(String str, String str2, LatLng... latLngArr) {
        return new PolylineLabelOptions(str, str2, latLngArr);
    }

    public static PolylineLabelOptions from(String str, Collection<LatLng> collection) {
        return new PolylineLabelOptions("", str, (LatLng[]) collection.toArray(new LatLng[collection.size()]));
    }

    public static PolylineLabelOptions from(String str, String str2, Collection<LatLng> collection) {
        return new PolylineLabelOptions(str, str2, (LatLng[]) collection.toArray(new LatLng[collection.size()]));
    }

    public PolylineLabelOptions setStyles(PolylineLabelStyles polylineLabelStyles) {
        this.styles = polylineLabelStyles;
        return this;
    }

    public PolylineLabelOptions setStyles(PolylineLabelStyle polylineLabelStyle) {
        this.styles = PolylineLabelStyles.from(polylineLabelStyle);
        return this;
    }

    public PolylineLabelOptions setStyles(int i, int i2) {
        this.styles = PolylineLabelStyles.from(PolylineLabelStyle.from(i, i2));
        return this;
    }

    public PolylineLabelOptions setStyles(int i, int i2, int i3, int i4) {
        this.styles = PolylineLabelStyles.from(PolylineLabelStyle.from(i, i2, i3, i4));
        return this;
    }

    public PolylineLabelOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public PolylineLabelOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public LatLng[] getPoints() {
        return this.pos;
    }

    public String getLabelId() {
        return this.labelId;
    }

    public long getRank() {
        return this.rank;
    }

    public String getText() {
        if (this.texts == null || this.texts.length == 0) {
            return null;
        }
        return this.texts[0];
    }

    public String[] getTexts() {
        return this.texts;
    }

    public PolylineLabelStyles getStyles() {
        return this.styles;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PolylineLabelOptions) {
            return Objects.equals(getLabelId(), ((PolylineLabelOptions) obj).getLabelId());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getLabelId());
    }
}

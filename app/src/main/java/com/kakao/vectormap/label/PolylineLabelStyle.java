package com.kakao.vectormap.label;

import android.graphics.Bitmap;
import com.kakao.vectormap.internal.ILabelStyle;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class PolylineLabelStyle extends ILabelStyle {
    private Bitmap iconBitmap;
    private int iconResId = 0;

    PolylineLabelStyle(LabelTextStyle labelTextStyle) {
        this.textStyles = new LabelTextStyle[1];
        this.textStyles[0] = labelTextStyle;
    }

    public static PolylineLabelStyle from(int i, int i2) {
        return new PolylineLabelStyle(new LabelTextStyle(i, i2));
    }

    public static PolylineLabelStyle from(int i, int i2, int i3, int i4) {
        return new PolylineLabelStyle(new LabelTextStyle(i, i2, i3, i4));
    }

    public PolylineLabelStyle setZoomLevel(int i) {
        this.zoomLevel = i;
        return this;
    }

    public PolylineLabelStyle setApplyDpScale(boolean z) {
        this.applyDpScale = z;
        return this;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public boolean isApplyDpScale() {
        return this.applyDpScale;
    }

    public Bitmap getIconBitmap() {
        return this.iconBitmap;
    }

    public int getIconResId() {
        return this.iconResId;
    }

    public LabelTextStyle getTextStyle() {
        if (this.textStyles == null || this.textStyles.length == 0) {
            return null;
        }
        return this.textStyles[0];
    }

    public boolean equals(Object obj) {
        Bitmap bitmap;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolylineLabelStyle)) {
            return false;
        }
        PolylineLabelStyle polylineLabelStyle = (PolylineLabelStyle) obj;
        return getZoomLevel() == polylineLabelStyle.getZoomLevel() && getIconResId() == polylineLabelStyle.getIconResId() && isApplyDpScale() == polylineLabelStyle.isApplyDpScale() && ((bitmap = this.iconBitmap) != null ? bitmap.sameAs(polylineLabelStyle.iconBitmap) : polylineLabelStyle.iconBitmap == null) && Objects.deepEquals(getTextStyle(), polylineLabelStyle.getTextStyle());
    }

    public int hashCode() {
        int iHash = Objects.hash(Integer.valueOf(getZoomLevel()), Integer.valueOf(getIconResId()), getIconBitmap(), Boolean.valueOf(isApplyDpScale()));
        return (this.textStyles == null || this.textStyles.length <= 0 || this.textStyles[0] == null) ? iHash : this.textStyles[0].hashCode() * 31;
    }
}

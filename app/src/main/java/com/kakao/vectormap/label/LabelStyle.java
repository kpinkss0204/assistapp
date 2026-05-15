package com.kakao.vectormap.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.core.view.ViewCompat;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.R;
import com.kakao.vectormap.internal.ILabelStyle;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LabelStyle extends ILabelStyle {
    public BadgeOptions[] badges;
    public int gravity;
    public final LabelIconStyle iconStyle;
    public LabelTransition iconTransition;
    public float padding;
    public LabelTransition textTransition;

    LabelStyle(LabelTextStyle... labelTextStyleArr) {
        this.gravity = 8;
        this.padding = 0.0f;
        if (labelTextStyleArr != null && labelTextStyleArr.length > 0) {
            this.textStyles = labelTextStyleArr;
        }
        this.iconStyle = new LabelIconStyle(0, null, 0.5f, 1.0f);
        this.iconTransition = LabelTransition.from(Transition.Alpha, Transition.Alpha);
        this.textTransition = LabelTransition.from(Transition.Alpha, Transition.Alpha);
        this.badges = new BadgeOptions[0];
    }

    LabelStyle(int i, int i2, Bitmap bitmap, float f, boolean z, float f2, float f3, int i3, LabelTransition labelTransition, LabelTransition labelTransition2, LabelTextStyle labelTextStyle) {
        this.gravity = 8;
        this.padding = 0.0f;
        this.zoomLevel = i;
        this.iconStyle = new LabelIconStyle(i2, bitmap, f2, f3);
        this.iconTransition = LabelTransition.from(labelTransition);
        this.badges = new BadgeOptions[0];
        this.padding = f;
        this.applyDpScale = z;
        this.gravity = i3;
        this.textTransition = LabelTransition.from(labelTransition2);
        if (labelTextStyle != null) {
            this.textStyles = new LabelTextStyle[1];
            this.textStyles[0] = labelTextStyle;
        }
    }

    public static LabelStyle from(int i) {
        return new LabelStyle(0, i, null, 0.0f, true, 0.5f, 1.0f, 8, LabelTransition.from(Transition.Alpha, Transition.Alpha), LabelTransition.from(Transition.Alpha, Transition.Alpha), null);
    }

    public static LabelStyle from(Bitmap bitmap) {
        return new LabelStyle(0, 0, bitmap, 0.0f, true, 0.5f, 1.0f, 8, LabelTransition.from(Transition.Alpha, Transition.Alpha), LabelTransition.from(Transition.Alpha, Transition.Alpha), null);
    }

    public static LabelStyle from(LabelTextStyle... labelTextStyleArr) {
        return new LabelStyle(labelTextStyleArr);
    }

    public static LabelStyle from(Context context, int i) {
        if (i != 0) {
            PointF pointF = new PointF(0.5f, 1.0f);
            int resourceId = context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getResourceId(R.styleable.LabelIconAttr_mapIconImage, 0);
            pointF.x = context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getFloat(R.styleable.LabelIconAttr_mapIconAnchorX, pointF.x);
            pointF.y = context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getFloat(R.styleable.LabelIconAttr_mapIconAnchorY, pointF.y);
            float f = context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getFloat(R.styleable.LabelIconAttr_mapPadding, 0.0f);
            boolean z = context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getBoolean(R.styleable.LabelIconAttr_mapApplyDpScale, true);
            LabelTransition labelTransitionFrom = LabelTransition.from(Transition.getEnum(context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getInteger(R.styleable.LabelIconAttr_mapIconEnterTransition, Transition.Alpha.getValue())), Transition.getEnum(context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getInteger(R.styleable.LabelIconAttr_mapIconExitTransition, Transition.Alpha.getValue())));
            labelTransitionFrom.enableTransitionWhenChange(context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getBoolean(R.styleable.LabelIconAttr_mapIconEnableEnterTransition, true), context.obtainStyledAttributes(i, R.styleable.LabelIconAttr).getBoolean(R.styleable.LabelIconAttr_mapIconEnableExitTransition, true));
            LabelTransition labelTransitionFrom2 = LabelTransition.from(Transition.getEnum(context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInteger(R.styleable.LabelTextAttr_mapTextEnterTransition, Transition.Alpha.getValue())), Transition.getEnum(context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInteger(R.styleable.LabelTextAttr_mapTextExitTransition, Transition.Alpha.getValue())));
            labelTransitionFrom2.enableTransitionWhenChange(context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getBoolean(R.styleable.LabelTextAttr_mapTextEnterTransition, true), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getBoolean(R.styleable.LabelTextAttr_mapTextExitTransition, true));
            int integer = context.obtainStyledAttributes(i, R.styleable.MapAttr).getInteger(R.styleable.MapAttr_mapZoomLevel, 0);
            int integer2 = context.obtainStyledAttributes(i, R.styleable.MapAttr).getInteger(R.styleable.MapAttr_mapTextGravity, 8);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.LabelTextAttr);
            return new LabelStyle(integer, resourceId, null, f, z, pointF.x, pointF.y, integer2, labelTransitionFrom, labelTransitionFrom2, (typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelTextAttr_mapTextSize) || typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelTextAttr_mapTextColor) || typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelTextAttr_mapTextStrokeColor) || typedArrayObtainStyledAttributes.hasValue(R.styleable.LabelTextAttr_mapTextStrokeWidth)) ? new LabelTextStyle(typedArrayObtainStyledAttributes.getString(R.styleable.LabelTextAttr_mapTextFont), typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelTextAttr_mapTextSize, 24), typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelTextAttr_mapTextColor, ViewCompat.MEASURED_STATE_MASK), typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelTextAttr_mapTextStrokeWidth, 0), typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelTextAttr_mapTextStrokeColor, 0), typedArrayObtainStyledAttributes.getInteger(R.styleable.LabelTextAttr_mapTextCharacterSpace, 0), typedArrayObtainStyledAttributes.getFloat(R.styleable.LabelTextAttr_mapTextLineSpace, 1.0f), typedArrayObtainStyledAttributes.getFloat(R.styleable.LabelTextAttr_mapTextAspectRatio, 1.0f)) : null);
        }
        MapLogger.e("LabelStyle initialize failure. styleResId is 0");
        return null;
    }

    public LabelStyle setBadges(BadgeOptions... badgeOptionsArr) {
        this.badges = badgeOptionsArr;
        return this;
    }

    public LabelStyle setPadding(float f) {
        this.padding = f;
        return this;
    }

    public LabelStyle setApplyDpScale(boolean z) {
        this.applyDpScale = z;
        return this;
    }

    public LabelStyle setAnchorPoint(PointF pointF) {
        this.iconStyle.anchorX = pointF.x;
        this.iconStyle.anchorY = pointF.y;
        return this;
    }

    public LabelStyle setAnchorPoint(float f, float f2) {
        this.iconStyle.anchorX = f;
        this.iconStyle.anchorY = f2;
        return this;
    }

    public LabelStyle setZoomLevel(int i) {
        this.zoomLevel = i;
        return this;
    }

    public LabelStyle setTextStyles(LabelTextStyle... labelTextStyleArr) {
        this.textStyles = labelTextStyleArr;
        return this;
    }

    public LabelStyle setTextStyles(int i, int i2) {
        this.textStyles = new LabelTextStyle[]{LabelTextStyle.from(i, i2)};
        return this;
    }

    public LabelStyle setTextStyles(int i, int i2, int i3, int i4) {
        this.textStyles = new LabelTextStyle[]{LabelTextStyle.from(i, i2, i3, i4)};
        return this;
    }

    public LabelStyle setIconTransition(LabelTransition labelTransition) {
        this.iconTransition = LabelTransition.from(labelTransition);
        return this;
    }

    public LabelStyle setTextTransition(LabelTransition labelTransition) {
        this.textTransition = LabelTransition.from(labelTransition);
        return this;
    }

    public BadgeOptions[] getBadges() {
        return this.badges;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public float getPadding() {
        return this.padding;
    }

    public PointF getIconAnchor() {
        return new PointF(this.iconStyle.anchorX, this.iconStyle.anchorY);
    }

    public LabelTransition getIconTransition() {
        return this.iconTransition;
    }

    public LabelTransition getTextTransition() {
        return this.textTransition;
    }

    public LabelTextStyle[] getTextStyles() {
        return this.textStyles;
    }

    public int getTextStyleCount() {
        if (this.textStyles == null) {
            return 0;
        }
        return this.textStyles.length;
    }

    public boolean isApplyDpScale() {
        return this.applyDpScale;
    }

    public LabelStyle setTextGravity(int i) {
        this.gravity = i;
        return this;
    }

    public int getTextGravity() {
        return this.gravity;
    }

    public int getIconResId() {
        return this.iconStyle.resId;
    }

    public Bitmap getIconBitmap() {
        return this.iconStyle.bitmap;
    }

    public PointF getAnchorPoint() {
        return new PointF(this.iconStyle.anchorX, this.iconStyle.anchorY);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LabelStyle)) {
            return false;
        }
        LabelStyle labelStyle = (LabelStyle) obj;
        if (this.iconStyle.resId == labelStyle.iconStyle.resId && Float.compare(this.padding, labelStyle.padding) == 0 && this.applyDpScale == labelStyle.applyDpScale && Float.compare(labelStyle.iconStyle.anchorX, this.iconStyle.anchorX) == 0 && Float.compare(labelStyle.iconStyle.anchorY, this.iconStyle.anchorY) == 0 && Objects.equals(this.iconTransition, labelStyle.iconTransition) && Objects.equals(this.textTransition, labelStyle.textTransition) && this.zoomLevel == labelStyle.zoomLevel && this.gravity == labelStyle.gravity && (this.iconStyle.bitmap != null ? this.iconStyle.bitmap.sameAs(labelStyle.iconStyle.bitmap) : labelStyle.iconStyle.bitmap == null) && (this.textStyles != null ? Arrays.deepEquals(this.textStyles, labelStyle.textStyles) : labelStyle.textStyles == null)) {
            BadgeOptions[] badgeOptionsArr = this.badges;
            BadgeOptions[] badgeOptionsArr2 = labelStyle.badges;
            if (badgeOptionsArr != null ? Arrays.deepEquals(badgeOptionsArr, badgeOptionsArr2) : badgeOptionsArr2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHash = Objects.hash(Integer.valueOf(this.iconStyle.resId), this.iconStyle.bitmap, Float.valueOf(this.padding), Boolean.valueOf(this.applyDpScale), Float.valueOf(this.iconStyle.anchorX), Float.valueOf(this.iconStyle.anchorY), this.iconTransition, this.textTransition, Integer.valueOf(this.zoomLevel), Integer.valueOf(this.gravity));
        if (this.textStyles != null && this.textStyles.length > 0) {
            for (LabelTextStyle labelTextStyle : this.textStyles) {
                iHash = (iHash * 31) + labelTextStyle.hashCode();
            }
        }
        BadgeOptions[] badgeOptionsArr = this.badges;
        if (badgeOptionsArr != null && badgeOptionsArr.length > 0) {
            for (BadgeOptions badgeOptions : badgeOptionsArr) {
                iHash = (iHash * 31) + badgeOptions.hashCode();
            }
        }
        return iHash;
    }
}

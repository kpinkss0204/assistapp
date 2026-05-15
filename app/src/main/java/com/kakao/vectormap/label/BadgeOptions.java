package com.kakao.vectormap.label;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.R;
import com.kakao.vectormap.utils.MapUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class BadgeOptions {
    public Bitmap bitmap;
    public String id;
    public int resId;
    public Object tag;
    public String assetId = "";
    public float offsetX = 0.5f;
    public float offsetY = 0.0f;
    public int zOrder = 0;

    BadgeOptions(String str, int i, Bitmap bitmap) {
        this.id = "";
        this.id = MapUtils.getUniqueId(str);
        this.resId = i;
        this.bitmap = bitmap;
    }

    BadgeOptions(String str, int i, float f, float f2, int i2) {
        this.id = "";
        this.id = MapUtils.getUniqueId(str);
        this.resId = i;
    }

    public static BadgeOptions from(int i) {
        return new BadgeOptions("", i, null);
    }

    public static BadgeOptions from(String str, int i) {
        return new BadgeOptions(str, i, null);
    }

    public static BadgeOptions from(Bitmap bitmap) {
        return new BadgeOptions("", 0, bitmap);
    }

    public static BadgeOptions from(String str, Bitmap bitmap) {
        return new BadgeOptions(str, 0, bitmap);
    }

    public static BadgeOptions from(Context context, int i) {
        if (i != 0) {
            int resourceId = context.obtainStyledAttributes(i, R.styleable.LabelBadgeAttr).getResourceId(R.styleable.LabelBadgeAttr_mapBadgeImage, 0);
            context.obtainStyledAttributes(i, R.styleable.LabelBadgeAttr).getFloat(R.styleable.LabelBadgeAttr_mapBadgeOffsetX, 0.5f);
            context.obtainStyledAttributes(i, R.styleable.LabelBadgeAttr).getFloat(R.styleable.LabelBadgeAttr_mapBadgeOffsetY, 0.5f);
            context.obtainStyledAttributes(i, R.styleable.LabelBadgeAttr).getInteger(R.styleable.LabelBadgeAttr_mapBadgeZOrder, 0);
            return new BadgeOptions("", resourceId, null);
        }
        MapLogger.e("BadgeOptions create failed. badgeStyle is 0.");
        return new BadgeOptions("", 0, null);
    }

    public int getResId() {
        return this.resId;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public BadgeOptions setOffset(float f, float f2) {
        this.offsetX = f;
        this.offsetY = f2;
        return this;
    }

    public PointF getOffset() {
        return new PointF(this.offsetX, this.offsetY);
    }

    public BadgeOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public BadgeOptions setId(String str) {
        this.id = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public BadgeOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BadgeOptions)) {
            return false;
        }
        BadgeOptions badgeOptions = (BadgeOptions) obj;
        if (getResId() == badgeOptions.getResId() && Float.compare(this.offsetX, badgeOptions.offsetX) == 0 && Float.compare(this.offsetY, badgeOptions.offsetY) == 0 && this.zOrder == badgeOptions.zOrder && Objects.equals(getId(), badgeOptions.getId())) {
            Bitmap bitmap = this.bitmap;
            Bitmap bitmap2 = badgeOptions.bitmap;
            if (bitmap != null ? bitmap.sameAs(bitmap2) : bitmap2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.id, Integer.valueOf(this.resId), this.bitmap, Float.valueOf(this.offsetX), Float.valueOf(this.offsetY), Integer.valueOf(this.zOrder));
    }
}

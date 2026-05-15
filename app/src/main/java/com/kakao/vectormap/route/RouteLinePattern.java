package com.kakao.vectormap.route;

import android.content.Context;
import android.graphics.Bitmap;
import com.kakao.vectormap.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLinePattern {
    public float distance;
    public String patternAssetId;
    public Bitmap patternBitmap;
    public int patternResId;
    public boolean pinEnd;
    public boolean pinStart;
    public String symbolAssetId;
    public Bitmap symbolBitmap;
    public int symbolResId;

    RouteLinePattern(int i, int i2, float f) {
        this.patternBitmap = null;
        this.symbolBitmap = null;
        this.pinStart = false;
        this.pinEnd = false;
        this.patternAssetId = "";
        this.symbolAssetId = "";
        this.patternResId = i;
        this.symbolResId = i2;
        this.distance = f;
    }

    RouteLinePattern(int i, int i2, float f, boolean z, boolean z2) {
        this.patternBitmap = null;
        this.symbolBitmap = null;
        this.patternAssetId = "";
        this.symbolAssetId = "";
        this.patternResId = i;
        this.symbolResId = i2;
        this.distance = f;
        this.pinStart = z;
        this.pinEnd = z2;
    }

    RouteLinePattern(Bitmap bitmap, Bitmap bitmap2, float f) {
        this.patternResId = 0;
        this.symbolResId = 0;
        this.pinStart = false;
        this.pinEnd = false;
        this.patternAssetId = "";
        this.symbolAssetId = "";
        this.patternBitmap = bitmap;
        this.symbolBitmap = bitmap2;
        this.distance = f;
    }

    public static RouteLinePattern from(int i, float f) {
        return from(i, 0, f);
    }

    public static RouteLinePattern from(int i, int i2, float f) {
        return new RouteLinePattern(i, i2, f);
    }

    public static RouteLinePattern from(Bitmap bitmap, float f) {
        return from(bitmap, (Bitmap) null, f);
    }

    public static RouteLinePattern from(Bitmap bitmap, Bitmap bitmap2, float f) {
        return new RouteLinePattern(bitmap, bitmap2, f);
    }

    public static RouteLinePattern from(Context context, int i) {
        return new RouteLinePattern(context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getResourceId(R.styleable.RouteLineAttr_mapPatternImage, 0), context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getResourceId(R.styleable.RouteLineAttr_mapSymbolImage, 0), context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getFloat(R.styleable.RouteLineAttr_mapDistance, 10.0f), context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getBoolean(R.styleable.RouteLineAttr_mapPinStart, false), context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getBoolean(R.styleable.RouteLineAttr_mapPinEnd, false));
    }

    public RouteLinePattern setPinEnd(boolean z) {
        this.pinEnd = z;
        return this;
    }

    public RouteLinePattern setPinStart(boolean z) {
        this.pinStart = z;
        return this;
    }

    public boolean isPinEnd() {
        return this.pinEnd;
    }

    public boolean isPinStart() {
        return this.pinStart;
    }

    public float getDistance() {
        return this.distance;
    }

    public boolean equals(Object obj) {
        Bitmap bitmap;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RouteLinePattern)) {
            return false;
        }
        RouteLinePattern routeLinePattern = (RouteLinePattern) obj;
        if (this.patternResId == routeLinePattern.patternResId && this.symbolResId == routeLinePattern.symbolResId && Float.compare(routeLinePattern.getDistance(), getDistance()) == 0 && isPinStart() == routeLinePattern.isPinStart() && isPinEnd() == routeLinePattern.isPinEnd() && ((bitmap = this.patternBitmap) != null ? bitmap.sameAs(routeLinePattern.patternBitmap) : routeLinePattern.patternBitmap == null)) {
            Bitmap bitmap2 = this.symbolBitmap;
            Bitmap bitmap3 = routeLinePattern.symbolBitmap;
            if (bitmap2 != null ? bitmap2.sameAs(bitmap3) : bitmap3 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.patternResId), Integer.valueOf(this.symbolResId), this.patternBitmap, this.symbolBitmap, Float.valueOf(getDistance()), Boolean.valueOf(isPinStart()), Boolean.valueOf(isPinEnd()));
    }
}

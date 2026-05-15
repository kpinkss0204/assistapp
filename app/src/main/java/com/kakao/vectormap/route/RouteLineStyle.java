package com.kakao.vectormap.route;

import android.content.Context;
import com.kakao.vectormap.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineStyle {
    public int lineColor;
    public float lineWidth;
    public RouteLinePattern pattern;
    public int patternIndex;
    public int strokeColor;
    public float strokeWidth;
    public int zoomLevel;

    RouteLineStyle(RouteLinePattern routeLinePattern) {
        this.zoomLevel = 0;
        this.lineWidth = 1.0f;
        this.lineColor = -1;
        this.strokeWidth = 0.0f;
        this.strokeColor = 0;
        this.patternIndex = -1;
        this.pattern = routeLinePattern;
    }

    RouteLineStyle(float f, int i, float f2, int i2, RouteLinePattern routeLinePattern, int i3) {
        this.patternIndex = -1;
        this.lineWidth = f;
        this.lineColor = i;
        this.strokeWidth = f2;
        this.strokeColor = i2;
        this.pattern = routeLinePattern;
        this.zoomLevel = i3;
    }

    public static RouteLineStyle from(RouteLinePattern routeLinePattern) {
        return new RouteLineStyle(routeLinePattern);
    }

    public static RouteLineStyle from(float f, int i) {
        return from(f, i, 0.0f, 0, null);
    }

    public static RouteLineStyle from(float f, int i, RouteLinePattern routeLinePattern) {
        return from(f, i, 0.0f, 0, routeLinePattern);
    }

    public static RouteLineStyle from(float f, int i, float f2, int i2) {
        return new RouteLineStyle(f, i, f2, i2, null, 0);
    }

    public static RouteLineStyle from(float f, int i, float f2, int i2, RouteLinePattern routeLinePattern) {
        return new RouteLineStyle(f, i, f2, i2, routeLinePattern, 0);
    }

    public static RouteLineStyle from(Context context, int i) {
        RouteLinePattern routeLinePatternFrom;
        int integer = context.obtainStyledAttributes(i, R.styleable.MapAttr).getInteger(R.styleable.MapAttr_mapZoomLevel, 0);
        float f = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getFloat(R.styleable.RouteLineAttr_mapLineWidth, 1.0f);
        int integer2 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getInteger(R.styleable.RouteLineAttr_mapLineColor, -1);
        float f2 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getFloat(R.styleable.RouteLineAttr_mapStrokeWidth, 0.0f);
        int integer3 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getInteger(R.styleable.RouteLineAttr_mapStrokeColor, 0);
        int resourceId = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getResourceId(R.styleable.RouteLineAttr_mapPatternImage, 0);
        int resourceId2 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getResourceId(R.styleable.RouteLineAttr_mapSymbolImage, 0);
        float f3 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getFloat(R.styleable.RouteLineAttr_mapDistance, 10.0f);
        boolean z = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getBoolean(R.styleable.RouteLineAttr_mapPinStart, false);
        boolean z2 = context.obtainStyledAttributes(i, R.styleable.RouteLineAttr).getBoolean(R.styleable.RouteLineAttr_mapPinEnd, false);
        if (resourceId != 0) {
            routeLinePatternFrom = RouteLinePattern.from(resourceId, resourceId2, f3);
            routeLinePatternFrom.setPinEnd(z2);
            routeLinePatternFrom.setPinStart(z);
        } else {
            routeLinePatternFrom = null;
        }
        return new RouteLineStyle(f, integer2, f2, integer3, routeLinePatternFrom, integer);
    }

    public RouteLineStyle setZoomLevel(int i) {
        this.zoomLevel = i;
        return this;
    }

    public RouteLineStyle setPattern(RouteLinePattern routeLinePattern) {
        this.pattern = routeLinePattern;
        return this;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public int getLineColor() {
        return this.lineColor;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public RouteLinePattern getPattern() {
        return this.pattern;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RouteLineStyle)) {
            return false;
        }
        RouteLineStyle routeLineStyle = (RouteLineStyle) obj;
        return getZoomLevel() == routeLineStyle.getZoomLevel() && Float.compare(routeLineStyle.getLineWidth(), getLineWidth()) == 0 && getLineColor() == routeLineStyle.getLineColor() && Float.compare(routeLineStyle.getStrokeWidth(), getStrokeWidth()) == 0 && getStrokeColor() == routeLineStyle.getStrokeColor() && Objects.equals(getPattern(), routeLineStyle.getPattern());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getZoomLevel()), Float.valueOf(getLineWidth()), Integer.valueOf(getLineColor()), Float.valueOf(getStrokeWidth()), Integer.valueOf(getStrokeColor()), getPattern());
    }
}

package com.kakao.vectormap.route;

import com.kakao.vectormap.utils.MapUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineOptions {
    private String lineId;
    private RouteLineSegment[] segments;
    private RouteLineStylesSet stylesSet;
    private Object tag;
    private int zOrder = 10000;
    private boolean visible = true;

    RouteLineOptions(String str, RouteLineSegment[] routeLineSegmentArr) {
        this.lineId = "";
        this.segments = routeLineSegmentArr;
        this.lineId = MapUtils.getUniqueId(str);
        this.stylesSet = toStylesSet(routeLineSegmentArr);
    }

    protected static RouteLineStylesSet toStylesSet(RouteLineSegment[] routeLineSegmentArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (RouteLineSegment routeLineSegment : routeLineSegmentArr) {
            linkedHashSet.add(routeLineSegment.getStyles());
            linkedHashSet2.addAll(routeLineSegment.getStyles().getPatterns());
        }
        return RouteLineStylesSet.from(new ArrayList(linkedHashSet), new ArrayList(linkedHashSet2));
    }

    public static RouteLineOptions from(RouteLineSegment... routeLineSegmentArr) {
        return from("", routeLineSegmentArr);
    }

    public static RouteLineOptions from(String str, RouteLineSegment... routeLineSegmentArr) {
        return new RouteLineOptions(str, routeLineSegmentArr);
    }

    public static RouteLineOptions from(List<RouteLineSegment> list) {
        return from("", (RouteLineSegment[]) list.toArray(new RouteLineSegment[list.size()]));
    }

    public static RouteLineOptions from(String str, List<RouteLineSegment> list) {
        return new RouteLineOptions(str, (RouteLineSegment[]) list.toArray(new RouteLineSegment[list.size()]));
    }

    public RouteLineOptions setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    public RouteLineOptions setZOrder(int i) {
        this.zOrder = i;
        return this;
    }

    public RouteLineOptions setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public int getZOrder() {
        return this.zOrder;
    }

    public RouteLineSegment[] getSegments() {
        return this.segments;
    }

    public String getLineId() {
        return this.lineId;
    }

    public RouteLineOptions setStylesSet(RouteLineStylesSet routeLineStylesSet) {
        this.stylesSet = routeLineStylesSet;
        return this;
    }

    public RouteLineStylesSet getStylesSet() {
        return this.stylesSet;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RouteLineOptions) {
            return Objects.equals(this.lineId, ((RouteLineOptions) obj).lineId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.lineId);
    }
}

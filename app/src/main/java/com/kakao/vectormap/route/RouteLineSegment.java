package com.kakao.vectormap.route;

import com.kakao.vectormap.CurveType;
import com.kakao.vectormap.LatLng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineSegment {
    public int curveType;
    public double[] lats;
    public double[] lngs;
    private List<LatLng> points;
    public int styleIndex;
    private RouteLineStyles styles;
    private Object tag;

    RouteLineSegment() {
        this.styleIndex = 0;
        this.curveType = CurveType.None.getValue();
        this.points = new ArrayList();
        this.styles = null;
    }

    RouteLineSegment(List<LatLng> list) {
        this.styleIndex = 0;
        this.curveType = CurveType.None.getValue();
        this.points = new ArrayList(list);
        this.styles = null;
    }

    RouteLineSegment(RouteLineStyles routeLineStyles) {
        this.styleIndex = 0;
        this.curveType = CurveType.None.getValue();
        this.points = new ArrayList();
        this.styles = routeLineStyles;
    }

    RouteLineSegment(List<LatLng> list, RouteLineStyles routeLineStyles) {
        this.styleIndex = 0;
        this.curveType = CurveType.None.getValue();
        this.points = new ArrayList(list);
        this.styles = routeLineStyles;
    }

    public static RouteLineSegment from() {
        return new RouteLineSegment();
    }

    public static RouteLineSegment from(RouteLineStyles routeLineStyles) {
        return new RouteLineSegment(routeLineStyles);
    }

    public static RouteLineSegment from(RouteLineStyle... routeLineStyleArr) {
        return new RouteLineSegment(RouteLineStyles.from(routeLineStyleArr));
    }

    public static RouteLineSegment from(LatLng[] latLngArr) {
        return new RouteLineSegment((List<LatLng>) Arrays.asList(latLngArr));
    }

    public static RouteLineSegment from(List<LatLng> list) {
        return new RouteLineSegment(list);
    }

    public static RouteLineSegment from(LatLng[] latLngArr, RouteLineStyles routeLineStyles) {
        return new RouteLineSegment(Arrays.asList(latLngArr), routeLineStyles);
    }

    public static RouteLineSegment from(LatLng[] latLngArr, RouteLineStyle... routeLineStyleArr) {
        return new RouteLineSegment(Arrays.asList(latLngArr), RouteLineStyles.from(routeLineStyleArr));
    }

    public static RouteLineSegment from(List<LatLng> list, RouteLineStyles routeLineStyles) {
        return new RouteLineSegment(list, routeLineStyles);
    }

    public static RouteLineSegment from(List<LatLng> list, RouteLineStyle... routeLineStyleArr) {
        return new RouteLineSegment(list, RouteLineStyles.from(routeLineStyleArr));
    }

    public RouteLineSegment setCurveType(CurveType curveType) {
        this.curveType = curveType.getValue();
        return this;
    }

    public CurveType getCurveType() {
        return CurveType.getEnum(this.curveType);
    }

    public RouteLineSegment setTag(Objects objects) {
        this.tag = objects;
        return this;
    }

    public Object getTag() {
        return this.tag;
    }

    public RouteLineStyles getStyles() {
        return this.styles;
    }

    public List<LatLng> getPoints() {
        return new ArrayList(this.points);
    }

    public RouteLineSegment setPoints(List<LatLng> list) {
        this.points.clear();
        this.points.addAll(list);
        return this;
    }

    public RouteLineSegment setPoints(LatLng... latLngArr) {
        this.points.clear();
        this.points.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public RouteLineSegment addPoints(List<LatLng> list) {
        this.points.addAll(list);
        return this;
    }

    public RouteLineSegment addPoints(LatLng... latLngArr) {
        this.points.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public RouteLineSegment clearPoints() {
        this.points.clear();
        return this;
    }

    public RouteLineSegment setStyles(RouteLineStyle... routeLineStyleArr) {
        this.styles = RouteLineStyles.from(routeLineStyleArr);
        return this;
    }

    public RouteLineSegment setStyles(RouteLineStyles routeLineStyles) {
        this.styles = routeLineStyles;
        return this;
    }
}

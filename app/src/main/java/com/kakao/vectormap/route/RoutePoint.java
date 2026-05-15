package com.kakao.vectormap.route;

import com.kakao.vectormap.LatLng;

/* JADX INFO: loaded from: classes4.dex */
public class RoutePoint {
    private final double lat;
    private final double lng;
    private final float orientation;

    RoutePoint(double d, double d2, float f) {
        this.lat = d;
        this.lng = d2;
        this.orientation = f;
    }

    public static RoutePoint from(double d, double d2, float f) {
        return new RoutePoint(d, d2, f);
    }

    public float getOrientation() {
        return this.orientation;
    }

    public LatLng getPoint() {
        return LatLng.from(this.lat, this.lng);
    }

    public String toString() {
        return "RoutePoint{lat=" + this.lat + ", lng=" + this.lng + ", orientation=" + this.orientation + '}';
    }
}

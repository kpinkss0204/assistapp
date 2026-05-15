package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public final class Coordinate {
    private MapCoordType coordType;
    private double x;
    private double y;

    public Coordinate(double d, double d2) {
        this(d, d2, MapCoordType.Undefined);
    }

    public Coordinate(double d, double d2, MapCoordType mapCoordType) {
        this.x = d;
        this.y = d2;
        this.coordType = mapCoordType;
    }

    public Coordinate(double d, double d2, int i) {
        this.x = d;
        this.y = d2;
        this.coordType = MapCoordType.getEnum(i);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public MapCoordType getCoordType() {
        return this.coordType;
    }

    public void setCoordType(MapCoordType mapCoordType) {
        this.coordType = mapCoordType;
    }

    public String toString() {
        return "Coordinate{x=" + this.x + ", y=" + this.y + ", coordType=" + this.coordType + '}';
    }
}

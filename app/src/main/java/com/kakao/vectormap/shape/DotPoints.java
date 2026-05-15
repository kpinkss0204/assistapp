package com.kakao.vectormap.shape;

import android.graphics.PointF;
import com.kakao.vectormap.LatLng;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class DotPoints {
    private LatLng basePosition;
    private PointVertex points;

    DotPoints(LatLng latLng, PointVertex pointVertex) {
        this.basePosition = latLng;
        this.points = pointVertex;
    }

    public static DotPoints from(LatLng latLng) {
        return new DotPoints(latLng, null);
    }

    public static DotPoints fromCircle(LatLng latLng, float f) {
        return new DotPoints(latLng, PointVertex.fromCircle(f));
    }

    public static DotPoints fromCircle(LatLng latLng, float f, boolean z) {
        return new DotPoints(latLng, PointVertex.fromCircle(f, z));
    }

    public static DotPoints fromRectangle(LatLng latLng, float f, float f2) {
        return new DotPoints(latLng, PointVertex.fromRectangle(f, f2));
    }

    public static DotPoints fromRectangle(LatLng latLng, float f, float f2, boolean z) {
        return new DotPoints(latLng, PointVertex.fromRectangle(f, f2, z));
    }

    public static DotPoints fromPoints(LatLng latLng, PointF... pointFArr) {
        return new DotPoints(latLng, PointVertex.fromPoint(pointFArr));
    }

    public static DotPoints fromPoints(LatLng latLng, PointF[] pointFArr, boolean z) {
        return new DotPoints(latLng, PointVertex.fromPoint(pointFArr, z));
    }

    public static DotPoints fromPoints(LatLng latLng, Collection<PointF> collection) {
        return new DotPoints(latLng, PointVertex.fromPoint((PointF[]) collection.toArray(new PointF[collection.size()])));
    }

    public static DotPoints fromPoints(LatLng latLng, Collection<PointF> collection, boolean z) {
        return new DotPoints(latLng, PointVertex.fromPoint(collection, z));
    }

    public DotPoints setHoleCircle(float f) {
        this.points.setHoles(PointVertex.fromCircle(f));
        return this;
    }

    public DotPoints setHoleRectangle(float f, float f2) {
        this.points.setHoles(PointVertex.fromRectangle(f, f2));
        return this;
    }

    public DotPoints setHolePoints(PointF... pointFArr) {
        this.points.setHoles(PointVertex.fromPoint(pointFArr));
        return this;
    }

    public DotPoints setHolePoints(Collection<PointF> collection) {
        this.points.setHoles(PointVertex.fromPoint(collection));
        return this;
    }

    public DotPoints setHolePoints(PointVertex... pointVertexArr) {
        this.points.setHoles(pointVertexArr);
        return this;
    }

    public LatLng getBasePosition() {
        return this.basePosition;
    }

    public PointVertex[] getHolePoints() {
        return this.points.getHoles();
    }

    public PointVertex getPoints() {
        return this.points;
    }
}

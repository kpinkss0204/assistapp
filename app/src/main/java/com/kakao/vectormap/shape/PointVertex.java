package com.kakao.vectormap.shape;

import android.graphics.PointF;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class PointVertex {
    public boolean clockwise;
    public float height;
    public PointVertex[] holes;
    public float radius;
    public int shapeType;
    public int vertexCount;
    public float width;
    public float[] xArray;
    public float[] yArray;

    PointVertex(int i, boolean z, float f, float f2) {
        this.radius = 1.0f;
        this.vertexCount = 90;
        this.shapeType = i;
        this.clockwise = z;
        this.width = f;
        this.height = f2;
    }

    PointVertex(int i, boolean z, float f, int i2) {
        this.width = 1.0f;
        this.height = 1.0f;
        this.shapeType = i;
        this.clockwise = z;
        this.radius = f;
        this.vertexCount = i2;
    }

    PointVertex(int i, boolean z, PointF[] pointFArr) {
        this.radius = 1.0f;
        this.vertexCount = 90;
        this.width = 1.0f;
        this.height = 1.0f;
        this.shapeType = i;
        this.clockwise = z;
        int length = pointFArr.length;
        this.xArray = new float[length];
        this.yArray = new float[length];
        for (int i2 = 0; i2 < pointFArr.length; i2++) {
            this.xArray[i2] = pointFArr[i2].x;
            this.yArray[i2] = pointFArr[i2].y;
        }
    }

    public static PointVertex fromCircle(float f) {
        return new PointVertex(0, true, f, 90);
    }

    public static PointVertex fromCircle(float f, boolean z) {
        return new PointVertex(0, z, f, 90);
    }

    public static PointVertex fromCircle(float f, boolean z, int i) {
        return new PointVertex(0, z, f, i);
    }

    public static PointVertex fromRectangle(float f, float f2) {
        return new PointVertex(1, true, f, f2);
    }

    public static PointVertex fromRectangle(float f, float f2, boolean z) {
        return new PointVertex(1, z, f, f2);
    }

    public static PointVertex fromPoint(PointF... pointFArr) {
        return new PointVertex(2, true, pointFArr);
    }

    public static PointVertex fromPoint(PointF[] pointFArr, boolean z) {
        return new PointVertex(2, z, pointFArr);
    }

    public static PointVertex fromPoint(Collection<PointF> collection) {
        return new PointVertex(2, true, (PointF[]) collection.toArray(new PointF[collection.size()]));
    }

    public static PointVertex fromPoint(Collection<PointF> collection, boolean z) {
        return new PointVertex(2, z, (PointF[]) collection.toArray(new PointF[collection.size()]));
    }

    public PointVertex setHoles(PointVertex... pointVertexArr) {
        this.holes = pointVertexArr;
        return this;
    }

    public PointVertex setHoles(Collection<PointVertex> collection) {
        this.holes = (PointVertex[]) collection.toArray(new PointVertex[collection.size()]);
        return this;
    }

    public PointVertex setClockwise(boolean z) {
        this.clockwise = z;
        return this;
    }

    public PointVertex setVertexCount(int i) {
        this.vertexCount = i;
        return this;
    }

    public PointVertex[] getHoles() {
        return this.holes;
    }

    public int getShapeType() {
        return this.shapeType;
    }

    public boolean isClockwise() {
        return this.clockwise;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getRadius() {
        return this.radius;
    }

    public int getVertexCount() {
        return this.vertexCount;
    }
}

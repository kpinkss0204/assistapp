package com.kakao.vectormap.label;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PathOptions {
    private float baseRadian;
    private float cornerRadius;
    private float jumpThreshold;
    public double[] latArray;
    public double[] lngArray;
    private int millis;
    private List<LatLng> paths;
    private float simplifyWeight;

    PathOptions() {
        this.millis = 0;
        this.cornerRadius = 40.0f;
        this.jumpThreshold = 200.0f;
        this.baseRadian = 3.14f;
        this.simplifyWeight = 1.0f;
        this.paths = new ArrayList();
    }

    PathOptions(List<LatLng> list) {
        this.millis = 0;
        this.cornerRadius = 40.0f;
        this.jumpThreshold = 200.0f;
        this.baseRadian = 3.14f;
        this.simplifyWeight = 1.0f;
        ArrayList arrayList = new ArrayList();
        this.paths = arrayList;
        arrayList.addAll(list);
    }

    public static PathOptions fromPath() {
        return new PathOptions();
    }

    public static PathOptions fromPath(LatLng... latLngArr) {
        return new PathOptions(Arrays.asList(latLngArr));
    }

    public static PathOptions fromPath(List<LatLng> list) {
        return new PathOptions(list);
    }

    public PathOptions setPath(LatLng... latLngArr) {
        this.paths.clear();
        this.paths.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PathOptions setPath(List<LatLng> list) {
        this.paths.clear();
        this.paths.addAll(list);
        return this;
    }

    public PathOptions addPath(LatLng... latLngArr) {
        this.paths.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PathOptions addPath(List<LatLng> list) {
        this.paths.addAll(list);
        return this;
    }

    public List<LatLng> getPaths() {
        return this.paths == null ? new ArrayList() : new ArrayList(this.paths);
    }

    public PathOptions setCornerRadius(float f) {
        this.cornerRadius = f;
        return this;
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public PathOptions setBaseRadian(float f) {
        this.baseRadian = f;
        return this;
    }

    public float getBaseRadian() {
        return this.baseRadian;
    }

    public PathOptions setJumpThreshold(float f) {
        this.jumpThreshold = f;
        return this;
    }

    public float getJumpThreshold() {
        return this.jumpThreshold;
    }

    public PathOptions setDuration(int i) {
        this.millis = i;
        return this;
    }

    public int getDuration() {
        return this.millis;
    }

    public PathOptions setSimplifyWeight(float f) {
        this.simplifyWeight = f;
        return this;
    }

    public float getSimplifyWeight() {
        return this.simplifyWeight;
    }

    public void toArray() {
        List<LatLng> list = this.paths;
        if (list == null || list.isEmpty()) {
            MapLogger.w("PathOptions Path is empty.");
            return;
        }
        int size = this.paths.size();
        this.latArray = new double[size];
        this.lngArray = new double[size];
        int i = 0;
        for (LatLng latLng : this.paths) {
            this.latArray[i] = latLng.latitude;
            this.lngArray[i] = latLng.longitude;
            i++;
        }
    }
}

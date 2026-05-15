package com.kakao.vectormap.shape;

import com.kakao.vectormap.LatLng;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class LatLngVertex {
    public LatLngVertex[] holes;
    public double[] lats;
    public double[] lngs;

    LatLngVertex(LatLng... latLngArr) {
        int length = latLngArr.length;
        this.lats = new double[length];
        this.lngs = new double[length];
        for (int i = 0; i < length; i++) {
            this.lats[i] = latLngArr[i].latitude;
            this.lngs[i] = latLngArr[i].longitude;
        }
    }

    public static LatLngVertex from(LatLng... latLngArr) {
        return new LatLngVertex(latLngArr);
    }

    public static LatLngVertex from(Collection<LatLng> collection) {
        return new LatLngVertex((LatLng[]) collection.toArray(new LatLng[collection.size()]));
    }

    public LatLngVertex setHoles(LatLngVertex... latLngVertexArr) {
        this.holes = latLngVertexArr;
        return this;
    }

    public LatLngVertex setHoles(Collection<LatLngVertex> collection) {
        this.holes = (LatLngVertex[]) collection.toArray(new LatLngVertex[collection.size()]);
        return this;
    }

    public LatLngVertex[] getHoles() {
        return this.holes;
    }
}

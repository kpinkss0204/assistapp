package com.kakao.vectormap.shape;

import com.kakao.vectormap.LatLng;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class MapPoints {
    LatLngVertex points;

    MapPoints(LatLngVertex latLngVertex) {
        this.points = latLngVertex;
    }

    public static MapPoints fromLatLng(LatLng... latLngArr) {
        return new MapPoints(LatLngVertex.from(latLngArr));
    }

    public static MapPoints fromLatLng(Collection<LatLng> collection) {
        return new MapPoints(LatLngVertex.from(collection));
    }

    public MapPoints setHolePoints(LatLng... latLngArr) {
        this.points.setHoles(LatLngVertex.from(latLngArr));
        return this;
    }

    public MapPoints setHolePoints(Collection<LatLng> collection) {
        this.points.setHoles(LatLngVertex.from(collection));
        return this;
    }

    public MapPoints setHolePoints(LatLngVertex... latLngVertexArr) {
        this.points.setHoles(latLngVertexArr);
        return this;
    }

    public LatLngVertex getPoints() {
        return this.points;
    }

    public LatLngVertex[] getHolePoints() {
        return this.points.getHoles();
    }
}

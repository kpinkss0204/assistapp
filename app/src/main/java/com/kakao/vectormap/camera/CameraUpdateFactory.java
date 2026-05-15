package com.kakao.vectormap.camera;

import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.LatLngBounds;

/* JADX INFO: loaded from: classes4.dex */
public class CameraUpdateFactory {
    public static final int FitMapPoints = 8;
    public static final int NewCameraAngle = 2;
    public static final int NewCameraPos = 1;
    public static final int NewCenterPoint = 0;
    public static final int Rotate = 6;
    public static final int Tilt = 7;
    public static final int ZoomIn = 4;
    public static final int ZoomOut = 5;
    public static final int ZoomTo = 3;

    public static CameraUpdate newCenterPosition(LatLng latLng) {
        return new CameraUpdate(latLng, 0);
    }

    public static CameraUpdate newCenterPosition(LatLng latLng, int i) {
        return new CameraUpdate(latLng, i, 0);
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate(cameraPosition, 1);
    }

    public static CameraUpdate rotateTo(double d) {
        return new CameraUpdate(d, 6);
    }

    public static CameraUpdate tiltTo(double d) {
        return new CameraUpdate(d, 7);
    }

    public static CameraUpdate zoomIn() {
        return new CameraUpdate(4);
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate(5);
    }

    public static CameraUpdate zoomTo(int i) {
        return new CameraUpdate(i, 3);
    }

    public static CameraUpdate fitMapPoints(LatLng[] latLngArr) {
        return fitMapPoints(latLngArr, 0);
    }

    public static CameraUpdate fitMapPoints(LatLngBounds latLngBounds) {
        return fitMapPoints(new LatLng[]{latLngBounds.getSouthwest(), latLngBounds.getNortheast(), latLngBounds.getSouthwest(), latLngBounds.getNortheast()}, 0);
    }

    public static CameraUpdate fitMapPoints(LatLng[] latLngArr, int i) {
        return fitMapPoints(latLngArr, i, -1);
    }

    public static CameraUpdate fitMapPoints(LatLngBounds latLngBounds, int i) {
        return fitMapPoints(new LatLng[]{latLngBounds.getSouthwest(), latLngBounds.getNortheast(), latLngBounds.getSouthwest(), latLngBounds.getNortheast()}, i);
    }

    public static CameraUpdate fitMapPoints(LatLng[] latLngArr, int i, int i2) {
        if (latLngArr == null) {
            return null;
        }
        if (latLngArr.length == 1) {
            return new CameraUpdate(latLngArr[0], 0);
        }
        if (latLngArr.length >= 2) {
            return new CameraUpdate(latLngArr, i, i2, 8);
        }
        return null;
    }
}

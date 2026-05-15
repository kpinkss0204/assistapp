package com.kakao.vectormap.camera;

import com.kakao.vectormap.LatLng;

/* JADX INFO: loaded from: classes4.dex */
public class CameraUpdate {
    private LatLng[] fitPoints;
    private double height;
    private int padding;
    private LatLng position;
    private double rotationAngle;
    private double tiltAngle;
    private int type;
    private int zoomLevel;

    CameraUpdate(LatLng[] latLngArr, int i, int i2, int i3) {
        this.fitPoints = latLngArr;
        this.type = i3;
        this.position = null;
        this.padding = i;
        this.zoomLevel = i2;
        this.rotationAngle = -1.0d;
        this.tiltAngle = -1.0d;
        this.height = -1.0d;
    }

    CameraUpdate(LatLng latLng, int i) {
        this.fitPoints = null;
        this.position = latLng;
        this.padding = -1;
        this.zoomLevel = -1;
        this.type = i;
        this.rotationAngle = -1.0d;
        this.tiltAngle = -1.0d;
        this.height = -1.0d;
    }

    CameraUpdate(LatLng latLng, int i, int i2) {
        this.fitPoints = null;
        this.position = latLng;
        this.zoomLevel = i;
        this.type = i2;
        this.padding = -1;
        this.tiltAngle = -1.0d;
        this.rotationAngle = -1.0d;
        this.height = -1.0d;
    }

    CameraUpdate(CameraPosition cameraPosition, int i) {
        this.position = null;
        this.zoomLevel = -1;
        this.tiltAngle = -1.0d;
        this.rotationAngle = -1.0d;
        this.height = -1.0d;
        this.type = -1;
        this.fitPoints = null;
        this.padding = -1;
        this.position = cameraPosition.getPosition();
        this.zoomLevel = cameraPosition.getZoomLevel();
        this.rotationAngle = cameraPosition.getRotationAngle();
        this.tiltAngle = cameraPosition.getTiltAngle();
        this.height = cameraPosition.getHeight();
        this.type = i;
    }

    CameraUpdate(double d, int i) {
        this.position = null;
        this.zoomLevel = -1;
        this.tiltAngle = -1.0d;
        this.rotationAngle = -1.0d;
        this.height = -1.0d;
        this.type = -1;
        this.fitPoints = null;
        this.padding = -1;
        if (i == 7) {
            this.position = null;
            this.zoomLevel = -1;
            this.tiltAngle = d;
            this.rotationAngle = -1.0d;
            this.height = -1.0d;
            this.type = i;
            return;
        }
        if (i == 6) {
            this.position = null;
            this.zoomLevel = -1;
            this.tiltAngle = -1.0d;
            this.rotationAngle = d;
            this.height = -1.0d;
            this.type = i;
            return;
        }
        this.position = null;
        this.zoomLevel = -1;
        this.tiltAngle = -1.0d;
        this.rotationAngle = -1.0d;
        this.height = -1.0d;
        this.type = i;
    }

    CameraUpdate(int i) {
        this.fitPoints = null;
        this.position = null;
        this.padding = -1;
        this.zoomLevel = -1;
        this.tiltAngle = -1.0d;
        this.rotationAngle = -1.0d;
        this.height = -1.0d;
        this.type = i;
    }

    CameraUpdate(int i, int i2) {
        this.fitPoints = null;
        this.position = null;
        this.zoomLevel = i;
        this.padding = -1;
        this.rotationAngle = -1.0d;
        this.tiltAngle = -1.0d;
        this.height = -1.0d;
        this.type = i2;
    }

    public LatLng getPosition() {
        return this.position;
    }

    public double getHeight() {
        return this.height;
    }

    public double getRotationAngle() {
        return this.rotationAngle;
    }

    public double getTiltAngle() {
        return this.tiltAngle;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public LatLng[] getFitPoints() {
        return this.fitPoints;
    }

    public int getPadding() {
        return this.padding;
    }

    public int getType() {
        return this.type;
    }
}

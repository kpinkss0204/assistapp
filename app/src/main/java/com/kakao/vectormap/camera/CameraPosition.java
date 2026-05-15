package com.kakao.vectormap.camera;

import androidx.camera.video.AudioStats;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;

/* JADX INFO: loaded from: classes4.dex */
public class CameraPosition {
    private final double height;
    private final LatLng position;
    private final double rotationAngle;
    private final double tiltAngle;
    private final int zoomLevel;

    public static class Builder {
        public double height;
        public LatLng position;
        public double rotationAngle;
        public double tiltAngle;
        public int zoomLevel;

        public Builder() {
            this.zoomLevel = 10;
            this.tiltAngle = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.rotationAngle = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.height = -1.0d;
        }

        public Builder(CameraPosition cameraPosition) {
            this.zoomLevel = 10;
            this.tiltAngle = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.rotationAngle = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.height = -1.0d;
            if (cameraPosition == null) {
                MapLogger.e("CameraPosition.Builder CameraPosition parameter is null.");
                return;
            }
            this.position = LatLng.from(cameraPosition.getPosition());
            this.zoomLevel = cameraPosition.getZoomLevel();
            this.rotationAngle = cameraPosition.getRotationAngle();
            this.tiltAngle = cameraPosition.getTiltAngle();
            this.height = cameraPosition.getHeight();
        }

        public Builder setPosition(LatLng latLng) {
            this.position = latLng;
            return this;
        }

        public LatLng getPosition() {
            return this.position;
        }

        public Builder setZoomLevel(int i) {
            this.zoomLevel = i;
            return this;
        }

        public int getZoomLevel() {
            return this.zoomLevel;
        }

        public Builder setRotationAngle(double d) {
            this.rotationAngle = d;
            return this;
        }

        public double getRotationAngle() {
            return this.rotationAngle;
        }

        public Builder setTiltAngle(double d) {
            this.tiltAngle = d;
            return this;
        }

        public double getTiltAngle() {
            return this.tiltAngle;
        }

        public Builder setHeight(double d) {
            this.height = d;
            return this;
        }

        public double getHeight() {
            return this.height;
        }
    }

    CameraPosition(double d, double d2, int i, double d3, double d4, double d5) {
        this.position = LatLng.from(d, d2);
        this.zoomLevel = i;
        this.tiltAngle = d3;
        this.rotationAngle = d4;
        this.height = d5;
    }

    public static CameraPosition from(Builder builder) {
        if (builder == null) {
            MapLogger.e("CameraPosition.Builder parameter is null.");
            return null;
        }
        if (builder.getPosition() == null) {
            MapLogger.e("CameraPosition.Builder Position parameter is null.");
            return null;
        }
        return new CameraPosition(builder.getPosition().getLatitude(), builder.getPosition().getLongitude(), builder.getZoomLevel(), builder.getTiltAngle(), builder.getRotationAngle(), builder.getHeight());
    }

    public static CameraPosition from(double d, double d2, int i, double d3, double d4, double d5) {
        return new CameraPosition(d, d2, i, d3, d4, d5);
    }

    public LatLng getPosition() {
        return this.position;
    }

    public int getZoomLevel() {
        return this.zoomLevel;
    }

    public double getTiltAngle() {
        return this.tiltAngle;
    }

    public double getRotationAngle() {
        return this.rotationAngle;
    }

    public double getHeight() {
        return this.height;
    }

    public String toString() {
        return "CameraPosition{position=" + this.position + ", zoomLevel=" + this.zoomLevel + ", tiltAngle=" + this.tiltAngle + ", rotationAngle=" + this.rotationAngle + '}';
    }
}

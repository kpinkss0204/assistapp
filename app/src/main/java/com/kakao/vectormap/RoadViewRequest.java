package com.kakao.vectormap;

import android.graphics.Bitmap;
import androidx.camera.video.AudioStats;
import com.google.logging.type.LogSeverity;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RoadViewRequest {
    public int defaultSearchRange;
    public int extendedSearchRange;
    public double lookAtPan;
    public LatLng lookAtPosition;
    public double lookAtTilt;
    public int lookAtType;
    public List<Marker> markers;
    public LatLng panoramaCoord;
    public String panoramaId;

    public RoadViewRequest(LatLng latLng) {
        this.panoramaId = "";
        this.lookAtType = 0;
        this.lookAtPosition = null;
        this.lookAtPan = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.lookAtTilt = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.defaultSearchRange = 100;
        this.extendedSearchRange = LogSeverity.WARNING_VALUE;
        this.markers = new ArrayList();
        this.panoramaCoord = latLng;
    }

    public RoadViewRequest(String str) {
        this.panoramaId = "";
        this.lookAtType = 0;
        this.lookAtPosition = null;
        this.lookAtPan = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.lookAtTilt = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.defaultSearchRange = 100;
        this.extendedSearchRange = LogSeverity.WARNING_VALUE;
        this.markers = new ArrayList();
        this.panoramaId = str;
    }

    public RoadViewRequest setPanoramaId(String str) {
        this.panoramaId = str;
        return this;
    }

    public RoadViewRequest setPanoramaCoord(LatLng latLng) {
        this.panoramaCoord = latLng;
        return this;
    }

    public RoadViewRequest setSearchRange(int i, int i2) {
        this.defaultSearchRange = i;
        this.extendedSearchRange = i2;
        return this;
    }

    public int getDefaultSearchRange() {
        return this.defaultSearchRange;
    }

    public int getExtendedSearchRange() {
        return this.extendedSearchRange;
    }

    public LatLng getPanoramaCoord() {
        return this.panoramaCoord;
    }

    public String getPanoramaId() {
        return this.panoramaId;
    }

    public RoadViewRequest setLookAtType(int i) {
        this.lookAtType = i;
        return this;
    }

    public RoadViewRequest setLookAtPosition(LatLng latLng) {
        this.lookAtPosition = latLng;
        this.lookAtType = 1;
        return this;
    }

    public RoadViewRequest setLookAtPosition(double d, double d2) {
        this.lookAtPan = d;
        this.lookAtTilt = d2;
        this.lookAtType = 2;
        return this;
    }

    public int getLookAtType() {
        return this.lookAtType;
    }

    public LatLng getLookAtLatLng() {
        return this.lookAtPosition;
    }

    public double getLookAtTilt() {
        return this.lookAtTilt;
    }

    public double getLookAtPan() {
        return this.lookAtPan;
    }

    public void clearLookAtPosition() {
        this.lookAtType = 0;
        this.lookAtPan = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.lookAtTilt = AudioStats.AUDIO_AMPLITUDE_NONE;
        this.lookAtPosition = null;
    }

    public RoadViewRequest addMarker(LatLng latLng) {
        this.markers.add(new Marker(0, null, latLng));
        return this;
    }

    public RoadViewRequest addMarker(int i, LatLng latLng) {
        this.markers.add(new Marker(i, null, latLng));
        return this;
    }

    public RoadViewRequest addMarker(Bitmap bitmap, LatLng latLng) {
        this.markers.add(new Marker(0, bitmap, latLng));
        return this;
    }

    public RoadViewRequest addMarker(double d, double d2) {
        this.markers.add(new Marker(0, (Bitmap) null, d, d2));
        return this;
    }

    public RoadViewRequest addMarker(int i, double d, double d2) {
        this.markers.add(new Marker(i, (Bitmap) null, d, d2));
        return this;
    }

    public RoadViewRequest addMarker(Bitmap bitmap, double d, double d2) {
        this.markers.add(new Marker(0, bitmap, d, d2));
        return this;
    }

    public RoadViewRequest addMarker(LatLng latLng, double d) {
        this.markers.add(new Marker(0, (Bitmap) null, latLng, d));
        return this;
    }

    public RoadViewRequest addMarker(int i, LatLng latLng, double d) {
        this.markers.add(new Marker(i, (Bitmap) null, latLng, d));
        return this;
    }

    public RoadViewRequest addMarker(Bitmap bitmap, LatLng latLng, double d) {
        this.markers.add(new Marker(0, bitmap, latLng, d));
        return this;
    }

    public List<Marker> getMarkers() {
        return this.markers;
    }

    public RoadViewRequest clearMarker() {
        this.markers.clear();
        return this;
    }

    public static class Marker {
        public String assetId;
        public final Bitmap bitmap;
        public final double pan;
        public final LatLng position;
        public final int resourceId;
        public final double tilt;
        public final int type;

        public Marker(int i, Bitmap bitmap, LatLng latLng) throws RuntimeException {
            this.position = latLng;
            this.pan = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.tilt = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.type = 1;
            this.bitmap = bitmap;
            this.resourceId = i;
        }

        public Marker(int i, Bitmap bitmap, double d, double d2) {
            this.position = null;
            this.pan = d;
            this.tilt = d2;
            this.type = 0;
            this.bitmap = bitmap;
            this.resourceId = i;
        }

        public Marker(int i, Bitmap bitmap, LatLng latLng, double d) {
            this.position = latLng;
            this.pan = d;
            this.tilt = AudioStats.AUDIO_AMPLITUDE_NONE;
            this.type = 0;
            this.bitmap = bitmap;
            this.resourceId = i;
        }

        public double getPan() {
            return this.pan;
        }

        public double getTilt() {
            return this.tilt;
        }

        public LatLng getPosition() {
            return this.position;
        }

        public double getLatitude() {
            LatLng latLng = this.position;
            return latLng == null ? AudioStats.AUDIO_AMPLITUDE_NONE : latLng.getLatitude();
        }

        public double getLongitude() {
            LatLng latLng = this.position;
            return latLng == null ? AudioStats.AUDIO_AMPLITUDE_NONE : latLng.getLongitude();
        }

        public int getResourceId() {
            return this.resourceId;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        int getType() {
            return this.type;
        }
    }
}

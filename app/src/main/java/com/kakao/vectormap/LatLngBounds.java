package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class LatLngBounds {
    public final LatLng northeast;
    public final LatLng southwest;

    public static class Builder {
        private LatLng northeast;
        private LatLng southwest;

        public Builder include(LatLng latLng) {
            LatLng latLng2 = this.northeast;
            if (latLng2 == null && this.southwest == null) {
                this.northeast = latLng;
                this.southwest = latLng;
                return this;
            }
            this.northeast = LatLng.from(Math.max(latLng2.latitude, latLng.latitude), Math.max(this.northeast.longitude, latLng.longitude));
            this.southwest = LatLng.from(Math.min(this.southwest.latitude, latLng.latitude), Math.min(this.southwest.longitude, latLng.longitude));
            return this;
        }

        public LatLngBounds build() {
            return new LatLngBounds(this.northeast, this.southwest);
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this.northeast = latLng;
        this.southwest = latLng2;
    }

    public LatLng getNortheast() {
        return this.northeast;
    }

    public LatLng getSouthwest() {
        return this.southwest;
    }

    public String toString() {
        return "LatLngBounds{northeast=" + this.northeast + ", southwest=" + this.southwest + '}';
    }
}

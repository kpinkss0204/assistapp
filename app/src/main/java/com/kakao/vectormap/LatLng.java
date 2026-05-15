package com.kakao.vectormap;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LatLng implements Parcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: com.kakao.vectormap.LatLng.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng createFromParcel(Parcel parcel) {
            return new LatLng(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLng[] newArray(int i) {
            return new LatLng[i];
        }
    };
    public final double latitude;
    public final double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LatLng(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public static LatLng from(double d, double d2) {
        return new LatLng(d, d2);
    }

    public static LatLng from(LatLng latLng) {
        if (latLng != null) {
            return from(latLng.getLatitude(), latLng.longitude);
        }
        return null;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    protected LatLng(Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.compare(getLatitude(), latLng.getLatitude()) == 0 && Double.compare(getLongitude(), latLng.getLongitude()) == 0;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(getLatitude()), Double.valueOf(getLongitude()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    public String toString() {
        return "LatLng{latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
    }
}

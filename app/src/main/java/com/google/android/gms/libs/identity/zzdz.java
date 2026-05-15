package com.google.android.gms.libs.identity;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzw;
import com.google.android.gms.location.zzz;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdz> CREATOR = new zzea();
    private final int zza;
    private final IBinder zzb;
    private final IBinder zzc;
    private final PendingIntent zzd;
    private final String zze;

    zzdz(int i, IBinder iBinder, IBinder iBinder2, PendingIntent pendingIntent, String str) {
        this.zza = i;
        this.zzb = iBinder;
        this.zzc = iBinder2;
        this.zzd = pendingIntent;
        this.zze = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.IBinder, com.google.android.gms.location.zzz] */
    public static zzdz zza(IInterface iInterface, zzz zzzVar, String str) {
        if (iInterface == null) {
            iInterface = null;
        }
        return new zzdz(1, iInterface, zzzVar, null, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.IBinder, com.google.android.gms.location.zzw] */
    public static zzdz zzb(IInterface iInterface, zzw zzwVar, String str) {
        if (iInterface == null) {
            iInterface = null;
        }
        return new zzdz(2, iInterface, zzwVar, null, str);
    }

    public static zzdz zzc(PendingIntent pendingIntent) {
        return new zzdz(3, null, null, pendingIntent, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzdz zzd(zzz zzzVar) {
        return new zzdz(4, null, zzzVar, null, null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}

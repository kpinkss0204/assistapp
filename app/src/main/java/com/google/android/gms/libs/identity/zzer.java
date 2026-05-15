package com.google.android.gms.libs.identity;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzer extends zzes {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzes zzc;

    zzer(zzes zzesVar, int i, int i2) {
        this.zzc = zzesVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzem.zzc(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.libs.identity.zzes, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.libs.identity.zzep
    @CheckForNull
    final Object[] zzb() {
        return this.zzc.zzb();
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final int zzd() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.libs.identity.zzes
    /* JADX INFO: renamed from: zzh */
    public final zzes subList(int i, int i2) {
        zzem.zze(i, i2, this.zzb);
        int i3 = this.zza;
        return this.zzc.subList(i + i3, i2 + i3);
    }
}

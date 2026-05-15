package com.google.android.gms.libs.identity;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzet extends zzes {
    static final zzes zza = new zzet(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzet(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzem.zzc(i, this.zzc, FirebaseAnalytics.Param.INDEX);
        return Objects.requireNonNull(this.zzb[i]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final Object[] zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.libs.identity.zzep
    final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.libs.identity.zzes, com.google.android.gms.libs.identity.zzep
    final int zzg(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }
}

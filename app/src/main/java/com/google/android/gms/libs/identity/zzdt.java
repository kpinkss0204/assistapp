package com.google.android.gms.libs.identity;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzdt extends zzy {
    private final zzdm zza;

    zzdt(zzdm zzdmVar) {
        this.zza = zzdmVar;
    }

    final zzdt zzc(ListenerHolder listenerHolder) {
        this.zza.zzb(listenerHolder);
        return this;
    }

    @Override // com.google.android.gms.location.zzz
    public final void zzd(Location location) {
        this.zza.zza().notifyListener(new zzdr(this, location));
    }

    @Override // com.google.android.gms.location.zzz
    public final void zze() {
        this.zza.zza().notifyListener(new zzds(this));
    }

    final void zzf() {
        this.zza.zza().clear();
    }

    final /* synthetic */ zzdm zzg() {
        return this.zza;
    }
}

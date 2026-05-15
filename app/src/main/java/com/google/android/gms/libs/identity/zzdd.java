package com.google.android.gms.libs.identity;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzdd implements zzdm {
    final /* synthetic */ ListenerHolder zza;
    final /* synthetic */ TaskCompletionSource zzb;

    zzdd(zzdu zzduVar, ListenerHolder listenerHolder, TaskCompletionSource taskCompletionSource) {
        this.zza = listenerHolder;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.android.gms.libs.identity.zzdm
    public final ListenerHolder zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.libs.identity.zzdm
    public final void zzb(ListenerHolder listenerHolder) {
        throw new IllegalStateException();
    }

    @Override // com.google.android.gms.libs.identity.zzdm
    public final void zzc() {
        this.zzb.trySetResult(null);
    }
}

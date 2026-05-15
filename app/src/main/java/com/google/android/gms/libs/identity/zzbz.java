package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class zzbz implements zzbg {
    static final /* synthetic */ zzbz zza = new zzbz();

    private /* synthetic */ zzbz() {
    }

    @Override // com.google.android.gms.libs.identity.zzbg
    public final /* synthetic */ void zza(zzdu zzduVar, ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzduVar.zzw(listenerKey, z, taskCompletionSource);
    }
}

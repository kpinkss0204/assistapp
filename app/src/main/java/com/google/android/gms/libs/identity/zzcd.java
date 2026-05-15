package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class zzcd implements zzbg {
    static final /* synthetic */ zzcd zza = new zzcd();

    private /* synthetic */ zzcd() {
    }

    @Override // com.google.android.gms.libs.identity.zzbg
    public final /* synthetic */ void zza(zzdu zzduVar, ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzduVar.zzv(listenerKey, z, taskCompletionSource);
    }
}

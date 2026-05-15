package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class zzcx implements RemoteCall {
    static final /* synthetic */ zzcx zza = new zzcx();

    private /* synthetic */ zzcx() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ((zzv) ((zzdu) obj).getService()).zzE(new zzdh((TaskCompletionSource) obj2));
    }
}

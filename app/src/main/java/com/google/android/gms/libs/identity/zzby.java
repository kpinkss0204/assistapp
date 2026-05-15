package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class zzby implements RemoteCall {
    static final /* synthetic */ zzby zza = new zzby();

    private /* synthetic */ zzby() {
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ((zzdu) obj).zzq(new LastLocationRequest.Builder().build(), (TaskCompletionSource) obj2);
    }
}

package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzck extends zzcl {
    final /* synthetic */ List zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzck(zzcm zzcmVar, GoogleApiClient googleApiClient, List list) {
        super(googleApiClient);
        this.zza = list;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzdu) anyClient).zzF(zzeh.zza(this.zza), zzcm.zza(this));
    }
}

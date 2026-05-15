package com.google.android.gms.libs.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzcs extends zzct {
    final /* synthetic */ LocationSettingsRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcs(zzcu zzcuVar, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        this.zza = locationSettingsRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        LocationSettingsRequest locationSettingsRequest = this.zza;
        Preconditions.checkArgument(locationSettingsRequest != null, "locationSettingsRequest can't be null");
        ((zzv) ((zzdu) anyClient).getService()).zzD(locationSettingsRequest, new zzda(this), null);
    }
}

package com.google.android.gms.libs.identity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzco extends GoogleApi implements GeofencingClient {
    public static final /* synthetic */ int zza = 0;

    public zzco(Activity activity) {
        super(activity, (Api<Api.ApiOptions.NoOptions>) zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.location.GeofencingClient
    public final Task<Void> addGeofences(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.location.zzcr
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzco.zza;
                ((zzdu) obj).zzE(geofencingRequest, pendingIntent, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(2424).build());
    }

    @Override // com.google.android.gms.location.GeofencingClient
    public final Task<Void> removeGeofences(final PendingIntent pendingIntent) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.location.zzcp
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzco.zza;
                ((zzdu) obj).zzF(zzeh.zzb(pendingIntent), (TaskCompletionSource) obj2);
            }
        }).setMethodKey(2425).build());
    }

    public zzco(Context context) {
        super(context, (Api<Api.ApiOptions.NoOptions>) zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.location.GeofencingClient
    public final Task<Void> removeGeofences(final List<String> list) {
        return doWrite(TaskApiCall.builder().run(new RemoteCall() { // from class: com.google.android.gms.internal.location.zzcq
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzco.zza;
                ((zzdu) obj).zzF(zzeh.zza(list), (TaskCompletionSource) obj2);
            }
        }).setMethodKey(2425).build());
    }
}

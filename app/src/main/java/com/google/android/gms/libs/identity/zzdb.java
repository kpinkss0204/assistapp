package com.google.android.gms.libs.identity;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzdb extends zzs {
    final /* synthetic */ TaskCompletionSource zza;

    zzdb(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.libs.identity.zzt
    public final void zzb(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }

    @Override // com.google.android.gms.libs.identity.zzt
    public final void zzc(int i, String[] strArr) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }

    @Override // com.google.android.gms.libs.identity.zzt
    public final void zzd(int i, PendingIntent pendingIntent) {
        TaskUtil.setResultOrApiException(new Status(GeofenceStatusCodes.zza(i)), this.zza);
    }
}
